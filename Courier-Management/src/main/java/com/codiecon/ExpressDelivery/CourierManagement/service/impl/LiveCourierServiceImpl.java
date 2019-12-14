package com.codiecon.ExpressDelivery.CourierManagement.service.impl;

import com.codiecon.ExpressDelivery.CourierManagement.Enum.TripStatus;
import com.codiecon.ExpressDelivery.CourierManagement.VO.GeoLocation;
import com.codiecon.ExpressDelivery.CourierManagement.entity.DistanceConstant;
import com.codiecon.ExpressDelivery.CourierManagement.entity.LiveCourier;
import com.codiecon.ExpressDelivery.CourierManagement.repository.LiveCourierRepository;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.DistanceService;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.LiveCourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LiveCourierServiceImpl implements LiveCourierService {

  @Autowired
  private LiveCourierRepository liveCourierRepository;

  @Autowired
  private DistanceService distanceService;

  @Override
  public List<LiveCourier> findLiveCouriersByTripStatus(TripStatus tripStatus) {
    return liveCourierRepository.findAllByStatus(tripStatus);
  }

  @Override
  public List<LiveCourier> findLiveCouriersNearBy(TripStatus tripStatus, double distance,
      GeoLocation centerLocation, String locaiton) {
    DistanceConstant distanceConstant = distanceService.getDistanceConstantFromLocation(locaiton);
    double latMax =
        centerLocation.getLatitude() + distanceConstant.getLatitudeConstant() * distance / 500;
    double latMin =
        centerLocation.getLatitude() - distanceConstant.getLatitudeConstant() * distance / 500;
    double lonMax =
        centerLocation.getLongitude() + distanceConstant.getLongitudeConstant() * distance / 500;
    double lonMin =
        centerLocation.getLongitude() - distanceConstant.getLongitudeConstant() * distance / 500;

    return liveCourierRepository.findAllNearbyCouries(tripStatus, latMax, latMin, lonMax, lonMin);
  }
}
