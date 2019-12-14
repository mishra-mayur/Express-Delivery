package com.codiecon.ExpressDelivery.CourierManagement.service.api;

import com.codiecon.ExpressDelivery.CourierManagement.Enum.CourierStatus;
import com.codiecon.ExpressDelivery.CourierManagement.entity.LiveCourier;

import java.util.List;

public interface LiveCourierService {
  List<LiveCourier> findLiveCouriersByTripStatus(CourierStatus courierStatus);
}
