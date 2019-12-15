package com.codiecon.ExpressDelivery.CourierManagement.service.impl;

import com.codiecon.ExpressDelivery.CourierManagement.entity.BookingResponse;
import com.codiecon.ExpressDelivery.CourierManagement.entity.Trip;
import com.codiecon.ExpressDelivery.CourierManagement.repository.TripRepository;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TripServiceImpl implements TripService {

  @Autowired
  private TripRepository tripRepository;

  @Override
  public boolean acceptBooking(BookingResponse response, double price) {
    Trip trip = new Trip("",response.getCourierId(),price,response.getBookingRequestId());
    Trip trip1 = tripRepository.save(trip);
    return !Objects.isNull(trip1);
  }
}
