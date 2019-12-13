package com.codiecon.ExpressDelivery.DeliveryManagement.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BookingResponse {


  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid2")
  private String id;

  private String bookingRequestId;

  private double bookingCost;

  private String courierId;



}
