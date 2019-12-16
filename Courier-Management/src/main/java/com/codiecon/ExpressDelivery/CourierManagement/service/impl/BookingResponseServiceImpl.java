package com.codiecon.ExpressDelivery.CourierManagement.service.impl;

import com.codiecon.ExpressDelivery.CourierManagement.Enum.BookingStatus;
import com.codiecon.ExpressDelivery.CourierManagement.entity.BookingResponse;
import com.codiecon.ExpressDelivery.CourierManagement.repository.BookingResponseRepository;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.BookingResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class BookingResponseServiceImpl implements BookingResponseService {

  @Autowired
  private BookingResponseRepository bookingResponseRepository;

  @Override
  @Transactional
  public boolean acceptBooking(BookingResponse response) {
    BookingResponse bookingResponse;
    if (Objects.isNull(
        bookingResponseRepository.findByBookingRequestId(response.getBookingRequestId()))) {
      bookingResponse = bookingResponseRepository.save(response);
      return !Objects.isNull(bookingResponse);
    } else {
      return bookingResponseRepository
          .updateResponse(response.getBookingRequestId(), response.getStatus(),
              response.getBookingCost(), response.getCourierId()) == 1;
    }
  }

  @Override
  public BookingStatus getStatus(String bookingRequestId) {
    return bookingResponseRepository.findStatusByBookingId(bookingRequestId);
  }

  @Override
  @Transactional
  public void updateStatus(String bookingRequestId, BookingStatus status) {
    bookingResponseRepository.updateStatus(bookingRequestId, status);
  }
}
