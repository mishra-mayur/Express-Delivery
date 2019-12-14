package com.codiecon.ExpressDelivery.CourierManagement.entity;

import com.codiecon.ExpressDelivery.CourierManagement.VO.GeoLocation;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table(name = Merchant.MERCHANT,uniqueConstraints={@UniqueConstraint(columnNames = {"email"})})
public class Merchant implements Serializable {
  private static final long serialVersionUID = 1L;
  public static final String MERCHANT = "merchant";
  private static final String ID = "ID";
  private static final String NAME = "NAME";
  private static final String EMAIL = "EMAIL";
  private static final String PHONE_NUMBER = "PHONE_NUMBER";
  private static final String VEHICLE_NUMBER = "VEHICLE_NUMBER";
  private static final String ADDRESS = "ADDRESS";
  private static final String GEO_LOCATION = "GEO_LOCATION";
  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid2")
  @Column(name = ID)
  private String id;
  @Column(name = NAME)
  private String name;
  @Column(name = EMAIL)
  @NotNull
  private String email;
  @Column(name = PHONE_NUMBER)
  private String phoneNumber;
  @Column(name = ADDRESS)
  private String address;
  @Column(name = GEO_LOCATION)
  private GeoLocation merchantLocation;
}
