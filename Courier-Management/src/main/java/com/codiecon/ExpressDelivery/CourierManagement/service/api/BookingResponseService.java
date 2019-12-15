package com.codiecon.ExpressDelivery.CourierManagement.service.api;

import com.codiecon.ExpressDelivery.CourierManagement.Enum.BookingStatus;
import com.codiecon.ExpressDelivery.CourierManagement.entity.BookingResponse;

public interface BookingResponseService {
  boolean acceptBooking(BookingResponse response);

  BookingStatus getStatus(String bookingRequestId);

  void updateStatus(String bookingRequestId, BookingStatus status);
}
