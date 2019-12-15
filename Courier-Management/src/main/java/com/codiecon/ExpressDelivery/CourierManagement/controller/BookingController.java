package com.codiecon.ExpressDelivery.CourierManagement.controller;

import com.codiecon.ExpressDelivery.CourierManagement.entity.BookingRequest;
import com.codiecon.ExpressDelivery.CourierManagement.entity.BookingResponse;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.BookingRequestService;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.BookingResponseService;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.TripService;
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

  @Autowired
  private BookingResponseService bookingResponseService;

  @Autowired
  private TripService tripService;


  @RequestMapping(method = {RequestMethod.GET})
  public BaseListResponse<BookingRequest> getBookingRequestsByCustomerId(@RequestParam String customerId)
      throws Exception {
    List<BookingRequest> bookingRequests = bookingService.getBookingRequestByCusotmerId(customerId);
    return new BaseListResponse<BookingRequest>(true, HttpStatus.OK.value(), bookingRequests);
  }


  @RequestMapping(method = {RequestMethod.POST})
  public BaseResponse saveBooking(@RequestBody BookingRequest bookingRequest) throws Exception {
    bookingService.saveBookingRequest(bookingRequest);
    return new BaseResponse(true, HttpStatus.OK.value());
  }

  @RequestMapping(method = {RequestMethod.POST} , value = "/trip")
  public BaseResponse bookTrip(@RequestBody BookingRequest bookingRequest) throws Exception {
    bookingService.bookTrip(bookingRequest);
    return new BaseResponse(true, HttpStatus.OK.value());
  }

  @RequestMapping(method = {RequestMethod.PUT}, value = "/confirmBooking")
  public BaseResponse acceptBooking(@RequestBody BookingResponse response) throws Exception{
    boolean isBooked = bookingResponseService.acceptBooking(response);
    double price = bookingService.getBookingPriceById(response.getBookingRequestId());
    boolean isAdded = tripService.acceptBooking(response, price);
    if (isAdded && isBooked)
      return new BaseResponse(true, HttpStatus.OK.value());
    return new BaseResponse("Error in accepting trip","Error in taking this booking please try again");
  }


}
