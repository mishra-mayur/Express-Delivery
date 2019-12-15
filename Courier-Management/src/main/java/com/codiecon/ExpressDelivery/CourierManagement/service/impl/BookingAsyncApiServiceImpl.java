package com.codiecon.ExpressDelivery.CourierManagement.service.impl;

import com.codiecon.ExpressDelivery.CourierManagement.Enum.BookingStatus;
import com.codiecon.ExpressDelivery.CourierManagement.Enum.CourierStatus;
import com.codiecon.ExpressDelivery.CourierManagement.VO.BookingVo;
import com.codiecon.ExpressDelivery.CourierManagement.VO.CustomerNotificationVO;
import com.codiecon.ExpressDelivery.CourierManagement.entity.BookingRequest;
import com.codiecon.ExpressDelivery.CourierManagement.entity.BookingResponse;
import com.codiecon.ExpressDelivery.CourierManagement.entity.Courier;
import com.codiecon.ExpressDelivery.CourierManagement.entity.LiveCourier;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.BookingAsyncApiService;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.BookingRequestService;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.BookingResponseService;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.CourierService;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.FcmTokenService;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.LiveCourierService;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.PushNotificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BookingAsyncApiServiceImpl implements BookingAsyncApiService {

  @Autowired
  private LiveCourierService liveCourierService;

  @Autowired
  private PushNotificationService pushNotificationService;

  @Autowired
  private FcmTokenService fcmTokenService;

  @Autowired
  private BookingResponseService bookingResponseService;

  @Autowired
  private BookingRequestService bookingRequestService;

  @Autowired
  private CourierService courierService;


  @Value("${courier.fetch.min.distance:100}")
  private int courierFetchMinDistance;

  @Value("${courier.fetch.wait.time:10000}")
  private int waitTime;

  @Value("${courier.fetch.try:4}")
  private int courierfetchTry;

  @Override
  @Async("bookCouriers")
  public void bookCouriers(BookingRequest bookingRequest, BookingVo bookingVo) {
    try {
      boolean isCourierFetched = false;
      int i = 1;
      while (!isCourierFetched && i <= courierfetchTry) {
        List<LiveCourier> liveCouriers = new ArrayList<>();
        liveCouriers = liveCourierService
            .findLiveCouriersNearBy(CourierStatus.ACTIVE, courierFetchMinDistance * i,
                bookingRequest.getPickupLocation(), bookingRequest.getLocationName());
        List<String> liveCourierIds =
            liveCouriers.stream().map(LiveCourier::getCourierId).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(liveCourierIds)) {
          List<String> liveCourierFcmTokens = fcmTokenService.getFcmTokenList(liveCourierIds);

          ObjectMapper objectMapper = new ObjectMapper();
          String requestBody = objectMapper.writeValueAsString(bookingVo);

          for (String token : liveCourierFcmTokens) {
            pushNotificationService
                .sendNotification(token, "BookingRequest", requestBody, "BookingRequest");
          }
          BookingStatus bookingStatus =
              bookingResponseService.getStatus(bookingRequest.getBookingRequestId());
          if (bookingStatus.equals(BookingStatus.COURIER_ASSIGNED)) {
            isCourierFetched = true;
          }
        }
        try {
          Thread.sleep(waitTime);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        i++;
      }
      if (!isCourierFetched) {

      }
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }

  @Override
  @Async("courierAssigned")
  public void sendBookedCourierNotification(BookingResponse bookingResponse) {
    try {
      Courier courier = courierService.getCourierByEmail(bookingResponse.getCourierId());
      BookingRequest bookingRequest = bookingRequestService
          .getBookingRequestByBookingRequestId(bookingResponse.getBookingRequestId());
      CustomerNotificationVO customerNotificationVO =
          new CustomerNotificationVO(courier.getEmail(), courier.getName(),
              courier.getVehicleNumber(), courier.getPhoneNumber());
      ObjectMapper objectMapper = new ObjectMapper();
      String requestBody = objectMapper.writeValueAsString(customerNotificationVO);
      pushNotificationService
          .sendNotification(bookingRequest.getFcmToken(), "CourierAssigned", requestBody,
              "BookingRequest");
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }


}
