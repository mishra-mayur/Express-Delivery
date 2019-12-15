package com.codiecon.ExpressDelivery.CourierManagement.service.api;

import com.codiecon.ExpressDelivery.CourierManagement.VO.BookingVo;
import com.codiecon.ExpressDelivery.CourierManagement.entity.BookingRequest;
import com.codiecon.ExpressDelivery.CourierManagement.entity.BookingResponse;

public interface BookingAsyncApiService {
  void bookCouriers(BookingRequest bookingRequest, BookingVo bookingVo);

  void sendBookedCourierNotification(BookingResponse bookingResponse);
}
