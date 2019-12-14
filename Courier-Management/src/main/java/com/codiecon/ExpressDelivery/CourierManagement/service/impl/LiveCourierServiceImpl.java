package com.codiecon.ExpressDelivery.CourierManagement.service.impl;

import com.codiecon.ExpressDelivery.CourierManagement.Enum.TripStatus;
import com.codiecon.ExpressDelivery.CourierManagement.entity.LiveCourier;
import com.codiecon.ExpressDelivery.CourierManagement.repository.LiveCourierRepository;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.LiveCourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LiveCourierServiceImpl implements LiveCourierService {

  @Autowired
  private LiveCourierRepository liveCourierRepository;

  @Override
  public List<LiveCourier> findLiveCouriersByTripStatus(TripStatus tripStatus){
//    return liveCourierRepository.findAllByTripStatus(tripStatus);
    return null;
  }
}
