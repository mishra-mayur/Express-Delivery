package com.codiecon.ExpressDelivery.CourierManagement.service.api;

import com.codiecon.ExpressDelivery.CourierManagement.entity.BookingResponse;

public interface BookingResponseService {
  boolean acceptBooking(BookingResponse response);
}
