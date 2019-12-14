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
@Table(name = DistanceConstant.DISTANCE_CONSTANT)
public class DistanceConstant implements Serializable {
  public static final String DISTANCE_CONSTANT = "distanceConstant";

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid2")
  private String id;

  private String location;

  private double latitudeConstant;

  private double longitudeConstant;

}
