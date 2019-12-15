package com.codiecon.ExpressDelivery.CourierManagement.service.impl;

import com.codiecon.ExpressDelivery.CourierManagement.Enum.BookingStatus;
import com.codiecon.ExpressDelivery.CourierManagement.Enum.CourierStatus;
import com.codiecon.ExpressDelivery.CourierManagement.VO.BookingVo;
import com.codiecon.ExpressDelivery.CourierManagement.entity.BookingRequest;
import com.codiecon.ExpressDelivery.CourierManagement.entity.BookingResponse;
import com.codiecon.ExpressDelivery.CourierManagement.entity.LiveCourier;
import com.codiecon.ExpressDelivery.CourierManagement.repository.BookingRequestRepository;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.BookingRequestService;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.DistanceService;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.FcmTokenService;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.LiveCourierService;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.PushNotificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookingRequestServiceImpl implements BookingRequestService {

  @Value("${courier.fetch.min.distance:100}")
  private int courierFetchMinDistance;

  @Value("${courier.base.fare:50}")
  private int baseFare;
  @Value("${courier.fare.per.km:30}")
  private int farePerKm;

  @Autowired
  private DistanceService distanceService;

  @Autowired
  private BookingRequestRepository bookingRequestRepository;

  @Autowired
  private LiveCourierService liveCourierService;

  @Autowired
  private PushNotificationService pushNotificationService;

  @Autowired
  private FcmTokenService fcmTokenService;

  @Override
  public void saveBookingRequest(BookingRequest bookingRequest) {
    bookingRequestRepository.save(bookingRequest);
  }

  @Override
  public List<BookingRequest> getBookingRequestByCusotmerId(String customerId) {
    return bookingRequestRepository.findAllByCustomerId(customerId);
  }

  @Override// todo  async
  public BookingResponse bookTrip(BookingRequest bookingRequest) {
    try {
      bookingRequest.setStatus(BookingStatus.PENDING);
      bookingRequest.setBookingRequestId(UUID.randomUUID().toString());
      BookingVo bookingVo = getBookingVoFromBookingRequest(bookingRequest);
      saveBookingRequest(bookingRequest);

      boolean isCourierFetched = false;
      int i =1;
      while (!isCourierFetched) {
        List<LiveCourier> liveCouriers  =new ArrayList<>();
        liveCouriers = liveCourierService
            .findLiveCouriersNearBy(CourierStatus.ACTIVE, courierFetchMinDistance*i,
                bookingRequest.getPickupLocation(), bookingRequest.getLocationName());
        List<String> liveCourierIds =
            liveCouriers.stream().map(LiveCourier::getCourierId).collect(Collectors.toList());
        if(!CollectionUtils.isEmpty(liveCourierIds)) {
          List<String> liveCourierFcmTokens = fcmTokenService.getFcmTokenList(liveCourierIds);

          //      if (!CollectionUtils.isEmpty(liveCourierFcmTokens)) {
          ObjectMapper objectMapper = new ObjectMapper();
          String requestBody = objectMapper.writeValueAsString(bookingVo);

          for (String token : liveCourierFcmTokens) {
            pushNotificationService
                .sendNotification(token, "BookingRequest", requestBody, "BookingRequest");
          }
          i++;
          isCourierFetched = true;//todo logic for is courier fetched
        }
      }
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }


    //todo make this part non async while making method async
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
    BookingRequest bookingRequest = bookingRequestRepository.findByBookingRequestId(bookingRequestId);
    if (!Objects.isNull(bookingRequest))
      return bookingRequest.getProductPrice();
    return 0;
  }

  private BookingVo getBookingVoFromBookingRequest(BookingRequest bookingRequest){
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
