package com.codiecon.ExpressDelivery.CourierManagement.repository;

import com.codiecon.ExpressDelivery.CourierManagement.entity.BookingRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRequestRepository extends JpaRepository<BookingRequest, String> {


  List<BookingRequest> findAllByCustomerId(String customerId);
  BookingRequest findByBookingRequestId(String bookingRequestId);

}
