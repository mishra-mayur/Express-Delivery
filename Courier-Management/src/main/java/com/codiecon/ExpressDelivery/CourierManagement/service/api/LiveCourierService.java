package com.codiecon.ExpressDelivery.CourierManagement.service.api;

import com.codiecon.ExpressDelivery.CourierManagement.Enum.TripStatus;
import com.codiecon.ExpressDelivery.CourierManagement.VO.GeoLocation;
import com.codiecon.ExpressDelivery.CourierManagement.Enum.CourierStatus;
import com.codiecon.ExpressDelivery.CourierManagement.entity.LiveCourier;

import java.util.List;

public interface LiveCourierService {

  List<LiveCourier> findLiveCouriersNearBy(TripStatus tripStatus, double distance,
      GeoLocation centerLocation, String locaiton);
  List<LiveCourier> findLiveCouriersByTripStatus(CourierStatus courierStatus);
}
