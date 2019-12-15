package com.codiecon.ExpressDelivery.CourierManagement.service.impl;

import com.codiecon.ExpressDelivery.CourierManagement.VO.GeoLocation;
import com.codiecon.ExpressDelivery.CourierManagement.entity.DistanceConstant;
import com.codiecon.ExpressDelivery.CourierManagement.Enum.CourierStatus;
import com.codiecon.ExpressDelivery.CourierManagement.entity.LiveCourier;
import com.codiecon.ExpressDelivery.CourierManagement.repository.LiveCourierRepository;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.DistanceService;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.LiveCourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class LiveCourierServiceImpl implements LiveCourierService {

  @Autowired
  private LiveCourierRepository liveCourierRepository;

  @Autowired
  private DistanceService distanceService;

  @Override
  public List<LiveCourier> findLiveCouriersByTripStatus(CourierStatus courierStatus) {
    return liveCourierRepository.findAllByStatus(courierStatus);
  }

  @Override
  public List<LiveCourier> findLiveCouriersNearBy(CourierStatus courierStatus, double distance,
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

    return liveCourierRepository.findAllNearbyCouries(courierStatus, latMax, latMin, lonMax, lonMin);
  }

  @Override
  public void save(LiveCourier liveCourier) {
    liveCourierRepository.save(liveCourier);
  }

  @Override
  @Transactional
  public void updateLiveLocation(String courierId, double latitude, double longitude) {
    liveCourierRepository.updateLiveLocation(courierId,latitude,longitude);
  }

  @Override
  public LiveCourier findLiveCourierByCourierId(String courierId) {
    return liveCourierRepository.findByCourierId(courierId);
  }

  @Override
  @Transactional
  public void updateStatus(String email, CourierStatus status) {
    liveCourierRepository.updateCourierStatus(email, status);
  }
}
