package com.codiecon.ExpressDelivery.CourierManagement.service.api;


import com.codiecon.ExpressDelivery.CourierManagement.entity.BookingRequest;

import java.util.List;

public interface BookingRequestService {
  void saveBookingRequest(BookingRequest bookingRequest);

  List<BookingRequest> getBookingRequestByCusotmerId(String customerId);
}
