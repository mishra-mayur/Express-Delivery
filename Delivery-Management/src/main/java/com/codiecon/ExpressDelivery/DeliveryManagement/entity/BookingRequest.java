package com.codiecon.ExpressDelivery.DeliveryManagement.entity;

import com.codiecon.ExpressDelivery.DeliveryManagement.vo.GeoLocation;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class BookingRequest {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid2")
  private String id;

  private String merchantId;

  private String customerId;

  private String totalWeight;

  private GeoLocation pickupLocation;

  private GeoLocation deliveryLocation;

}
