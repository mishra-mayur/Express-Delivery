package com.codiecon.ExpressDelivery.CourierManagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = Trip.TRIP)
public class Trip  implements Serializable {
  private static final long serialVersionUID = 1L;
  public static final String TRIP = "trip";
  private static final String ID = "ID";

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid2")
  @Column(name = ID)
  private String id;
  @Column(name = "courier_id")
  private String courierId;
  @Column(name = "productPrice")
  private double productPrice;
  @Column(name = "booking_id")
  private String bookingId;

}
