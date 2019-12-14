package com.codiecon.ExpressDelivery.CourierManagement.service.api;

import com.codiecon.ExpressDelivery.CourierManagement.VO.GeoLocation;
import com.codiecon.ExpressDelivery.CourierManagement.Enum.CourierStatus;
import com.codiecon.ExpressDelivery.CourierManagement.entity.LiveCourier;

import java.util.List;

public interface LiveCourierService {

  List<LiveCourier> findLiveCouriersByTripStatus(CourierStatus courierStatus);

  List<LiveCourier> findLiveCouriersNearBy(CourierStatus courierStatus, double distance,
      GeoLocation centerLocation, String locaiton);

  void save(LiveCourier liveCourier);

  void updateLiveLocation(String courierId, double latitude, double longitude);

  LiveCourier findLiveCourierByCourierId(String courierId);
}
