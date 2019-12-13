package com.codiecon.ExpressDelivery.CourierManagement.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = Trip.TRIP)
public class Trip  implements Serializable {
  private static final long serialVersionUID = 1L;
  public static final String TRIP = "trip";
  private static final String ID = "ID";

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid2")
  @Column(name = ID)
  private long id;
  @Column(name = "courier_id")
  private long courierId;
  @Column(name = "weight")
  private double weight;
  @Column(name = "shipment_id")
  private long shipmentId;
}