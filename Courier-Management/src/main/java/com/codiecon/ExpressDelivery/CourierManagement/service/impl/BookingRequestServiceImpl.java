package com.codiecon.ExpressDelivery.CourierManagement.service.impl;

import com.codiecon.ExpressDelivery.CourierManagement.Enum.BookingStatus;
import com.codiecon.ExpressDelivery.CourierManagement.Enum.CourierStatus;
import com.codiecon.ExpressDelivery.CourierManagement.VO.BookingVo;
import com.codiecon.ExpressDelivery.CourierManagement.entity.BookingRequest;
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

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingRequestServiceImpl implements BookingRequestService {

  @Value("${courier.fetch.min.distance:100}")
  private int courierFetchMinDistance;

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
  public void bookTrip(BookingRequest bookingRequest) {
    try {
      bookingRequest.setStatus(BookingStatus.PENDING);
      BookingVo bookingVo = getBookingVoFromBookingRequest(bookingRequest);
      saveBookingRequest(bookingRequest);

      boolean isCourierFetched = false;
      int i =1;
      while (!isCourierFetched) {

        List<LiveCourier> liveCouriers = liveCourierService
            .findLiveCouriersNearBy(CourierStatus.ACTIVE, courierFetchMinDistance*i,
                bookingRequest.getPickupLocation(), bookingRequest.getLocationName());
        List<String> liveCourierIds =
            liveCouriers.stream().map(LiveCourier::getCourierId).collect(Collectors.toList());
        List<String> liveCourierFcmTokens = fcmTokenService.getFcmTokenList(liveCourierIds);

  //      if (!CollectionUtils.isEmpty(liveCourierFcmTokens)) {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(bookingVo);

        for (String token : liveCourierFcmTokens) {
          pushNotificationService
              .sendNotification(token, "BookingRequest", requestBody, "BookingRequest");
        }
        i++;
        isCourierFetched=true;//todo logic for is courier fetched
      }
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }


  }

  @Override
  public double getBookingPriceById(String bookingRequestId) {
    Optional<BookingRequest> bookingRequest = bookingRequestRepository.findById(bookingRequestId);
    if (!Objects.isNull(bookingRequest.get()))
      return bookingRequest.get().getProductPrice();
    return 0;
  }

  private BookingVo getBookingVoFromBookingRequest(BookingRequest bookingRequest){
    BookingVo bookingVo = new BookingVo();
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
