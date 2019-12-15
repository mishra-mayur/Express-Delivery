package com.codiecon.ExpressDelivery.CourierManagement.repository;

import com.codiecon.ExpressDelivery.CourierManagement.Enum.BookingStatus;
import com.codiecon.ExpressDelivery.CourierManagement.entity.BookingRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRequestRepository extends JpaRepository<BookingRequest, String> {


  List<BookingRequest> findAllByCustomerId(String customerId);
  BookingRequest findByBookingRequestId(String bookingRequestId);

  @Modifying
  @Query("UPDATE BookingRequest B SET B.status = :status where B.bookingRequestId = :bookingRequestId")
  void updateStatus(@Param("bookingRequestId") String bookingRequestId,@Param("status") BookingStatus status);
}
