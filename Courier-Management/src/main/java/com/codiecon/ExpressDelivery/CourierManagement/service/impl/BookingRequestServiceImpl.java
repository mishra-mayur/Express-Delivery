package com.codiecon.ExpressDelivery.CourierManagement.service.impl;

import com.codiecon.ExpressDelivery.CourierManagement.entity.BookingRequest;
import com.codiecon.ExpressDelivery.CourierManagement.repository.BookingRequestRepository;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.BookingRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingRequestServiceImpl implements BookingRequestService {

  @Autowired
  private BookingRequestRepository bookingRequestRepository;

  @Override
  public void saveBookingRequest(BookingRequest bookingRequest){
    bookingRequestRepository.save(bookingRequest);
  }

  @Override
  public List<BookingRequest> getBookingRequestByCusotmerId(String customerId){
    return bookingRequestRepository.findAllByCustomerId(customerId);
  }


}
