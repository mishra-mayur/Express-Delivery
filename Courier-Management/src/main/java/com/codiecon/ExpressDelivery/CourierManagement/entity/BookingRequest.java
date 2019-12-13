package com.codiecon.ExpressDelivery.CourierManagement.entity;

import com.codiecon.ExpressDelivery.CourierManagement.Enum.BookingStatus;
import com.codiecon.ExpressDelivery.CourierManagement.VO.GeoLocation;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class BookingRequest implements Serializable {

  private static final long serialVersionUID = -1802727754839117602L;

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid2")
  private String id;

  private String merchantId;

  private String customerId;

  private String totalWeight;

  private GeoLocation pickupLocation;

  private GeoLocation deliveryLocation;

  private BookingStatus status;
}
