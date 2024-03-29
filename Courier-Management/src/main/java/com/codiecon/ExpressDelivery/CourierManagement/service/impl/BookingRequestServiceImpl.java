package com.codiecon.ExpressDelivery.CourierManagement.service.impl;

import com.codiecon.ExpressDelivery.CourierManagement.Enum.BookingStatus;
import com.codiecon.ExpressDelivery.CourierManagement.VO.BookingVo;
import com.codiecon.ExpressDelivery.CourierManagement.entity.BookingRequest;
import com.codiecon.ExpressDelivery.CourierManagement.entity.BookingResponse;
import com.codiecon.ExpressDelivery.CourierManagement.repository.BookingRequestRepository;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.BookingAsyncApiService;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.BookingRequestService;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.BookingResponseService;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.DistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class BookingRequestServiceImpl implements BookingRequestService {

  @Value("${courier.base.fare:50}")
  private int baseFare;
  @Value("${courier.fare.per.km:30}")
  private int farePerKm;

  @Autowired
  private DistanceService distanceService;

  @Autowired
  private BookingRequestRepository bookingRequestRepository;

  @Autowired
  private BookingAsyncApiService bookingAsyncApiService;

  @Autowired
  private BookingResponseService bookingResponseService;

  @Override
  public void saveBookingRequest(BookingRequest bookingRequest) {
    bookingRequestRepository.save(bookingRequest);
  }

  @Override
  public List<BookingRequest> getBookingRequestByCusotmerId(String customerId) {
    return bookingRequestRepository.findAllByCustomerId(customerId);
  }

  @Override
  public BookingResponse bookTrip(BookingRequest bookingRequest) {
    bookingRequest.setStatus(BookingStatus.PENDING);
    bookingRequest.setBookingRequestId(UUID.randomUUID().toString());
    BookingVo bookingVo = getBookingVoFromBookingRequest(bookingRequest);
    saveBookingRequest(bookingRequest);
    BookingResponse bookingResponse1 = new BookingResponse(bookingRequest.getBookingRequestId(),BookingStatus.PENDING,0.0);
    bookingResponseService.acceptBooking(bookingResponse1);
    BookingStatus bookingStatus =
        bookingResponseService.getStatus(bookingRequest.getBookingRequestId());
    if (!bookingStatus.equals(BookingStatus.COURIER_ASSIGNED) || !bookingStatus.equals((BookingStatus.DONE))) {
      bookingAsyncApiService.bookCouriers(bookingRequest, bookingVo);
    }
    double distance = distanceService.distance(bookingRequest.getPickupLocation().getLatitude(),
        bookingRequest.getPickupLocation().getLongitude(),
        bookingRequest.getDeliveryLocation().getLatitude(),
        bookingRequest.getDeliveryLocation().getLongitude(), 0, 0);
    double bookingCost =
        baseFare + distance * farePerKm + bookingRequest.getProductPrice() * bookingRequest
            .getQuantity();
    BookingResponse bookingResponse = new BookingResponse();
    bookingResponse.setCourierId(null);
    bookingResponse.setStatus(BookingStatus.PENDING);
    bookingResponse.setBookingCost(bookingCost);
    bookingResponse.setBookingRequestId(bookingRequest.getBookingRequestId());
    bookingResponse.setId("id");
    return bookingResponse;

  }

  @Override
  public double getBookingPriceById(String bookingRequestId) {
    BookingRequest bookingRequest =
        bookingRequestRepository.findByBookingRequestId(bookingRequestId);
    if (!Objects.isNull(bookingRequest))
      return bookingRequest.getProductPrice();
    return 0;
  }

  @Override
  @Transactional
  public void updateStatus(String bookingRequestId, BookingStatus status) {
    bookingRequestRepository.updateStatus(bookingRequestId, status);
  }

  @Override
  public BookingRequest getBookingRequestByBookingRequestId(String bookingRequestId) {
    return bookingRequestRepository.findByBookingRequestId(bookingRequestId);
  }

  @Override
  public BookingStatus getStatus(String bookingRequestId) {
    return bookingRequestRepository.findByBookingRequestId(bookingRequestId).getStatus();
  }

  private BookingVo getBookingVoFromBookingRequest(BookingRequest bookingRequest) {
    BookingVo bookingVo = new BookingVo();
    bookingVo.setBookingRequestId(bookingRequest.getBookingRequestId());
    bookingVo.setCustomerName(bookingRequest.getCustomerName());
    bookingVo.setCustomerPhone(bookingRequest.getCustomerName());
    bookingVo.setDeliveryLatitude(bookingRequest.getDeliveryLocation().getLatitude());
    bookingVo.setDeliveryLongitude(bookingRequest.getDeliveryLocation().getLongitude());
    bookingVo.setPickupLocationAddress(bookingRequest.getLocationName());
    bookingVo.setDeliveryLocationAddress(bookingRequest.getLocationName());
    bookingVo.setPickupLatitude(bookingRequest.getPickupLocation().getLatitude());
    bookingVo.setPickupLongitude(bookingRequest.getPickupLocation().getLongitude());
    return bookingVo;
  }


}
