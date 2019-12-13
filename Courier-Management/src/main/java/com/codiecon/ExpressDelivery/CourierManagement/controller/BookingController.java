package com.codiecon.ExpressDelivery.CourierManagement.controller;

import com.codiecon.ExpressDelivery.CourierManagement.entity.BookingRequest;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.BookingRequestService;
import com.codiecon.ExpressDelivery.CourierManagement.util.ApiPath;
import com.gdn.tms.util.rest.model.response.BaseListResponse;
import com.gdn.tms.util.rest.model.response.BaseResponse;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = ApiPath.BOOKING)
@Api(value = "BookingController", description = "Booking Controller")
public class BookingController {

  @Autowired
  private BookingRequestService bookingService;


  @RequestMapping(method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
  public BaseListResponse<BookingRequest> getBookingRequestsBy(@RequestParam String customerId)
      throws Exception {
    List<BookingRequest> bookingRequests = bookingService.getBookingRequestByCusotmerId(customerId);
    return new BaseListResponse<BookingRequest>(true, HttpStatus.OK.value(), bookingRequests);
  }


  @RequestMapping(method = {RequestMethod.POST})
  public BaseResponse saveBooking(@RequestBody BookingRequest bookingRequest) throws Exception {
    bookingService.saveBookingRequest(bookingRequest);
    return new BaseResponse(true, HttpStatus.OK.value());
  }


}
