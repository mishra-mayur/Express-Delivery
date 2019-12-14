package com.codiecon.ExpressDelivery.CourierManagement.service.impl;

import com.codiecon.ExpressDelivery.CourierManagement.Enum.TripStatus;
import com.codiecon.ExpressDelivery.CourierManagement.entity.BookingRequest;
import com.codiecon.ExpressDelivery.CourierManagement.entity.LiveCourier;
import com.codiecon.ExpressDelivery.CourierManagement.repository.BookingRequestRepository;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.BookingRequestService;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.DistanceService;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.LiveCourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingRequestServiceImpl implements BookingRequestService {

  @Value("${courier.fetch.min.distance:100}")
  private int courierFetchMinDistance;

  @Autowired
  private DistanceService distanceService;

  @Autowired
  private BookingRequestRepository bookingRequestRepository;

  @Autowired
  private LiveCourierService liveCourierService;

  @Override
  public void saveBookingRequest(BookingRequest bookingRequest) {
    bookingRequestRepository.save(bookingRequest);
  }

  @Override
  public List<BookingRequest> getBookingRequestByCusotmerId(String customerId) {
    return bookingRequestRepository.findAllByCustomerId(customerId);
  }

  @Override
  public void bookTrip(BookingRequest bookingRequest) {
    saveBookingRequest(bookingRequest);

    List<LiveCourier> liveCouriers =
        liveCourierService.findLiveCouriersByTripStatus(TripStatus.ACTIVE);
    boolean isCourierFetched = false;
    while (!isCourierFetched) {


//      for (liveCouriers)
//      double distanceService.distance();


    }


  }


}
