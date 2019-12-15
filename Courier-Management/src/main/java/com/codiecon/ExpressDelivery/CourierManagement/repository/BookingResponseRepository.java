package com.codiecon.ExpressDelivery.CourierManagement.repository;

import com.codiecon.ExpressDelivery.CourierManagement.Enum.BookingStatus;
import com.codiecon.ExpressDelivery.CourierManagement.entity.BookingResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingResponseRepository extends JpaRepository<BookingResponse, String> {

  @Query("SELECT B.status FROM BookingResponse B where B.bookingRequestId = :bookingRequestId")
  BookingStatus findStatusByBookingId(@Param("bookingRequestId") String bookingRequestId);

  @Modifying
  @Query("UPDATE BookingResponse B SET B.status = :status WHERE B.bookingRequestId = :bookingRequestId")
  void updateStatus(@Param("bookingRequestId") String bookingRequestId, @Param("status") BookingStatus status);
}
