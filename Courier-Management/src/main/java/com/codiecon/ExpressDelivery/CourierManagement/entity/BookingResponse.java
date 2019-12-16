package com.codiecon.ExpressDelivery.CourierManagement.entity;

import com.codiecon.ExpressDelivery.CourierManagement.Enum.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid2")
  private String id;

  private String bookingRequestId;

  private BookingStatus status;

  private double bookingCost;

  private String courierId;

  public BookingResponse(String bookingRequestId, BookingStatus status, double bookingCost) {
    this.bookingRequestId = bookingRequestId;
    this.status = status;
    this.bookingCost = bookingCost;
  }
}