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
@Table(name = Customer.CUSTOMERS)
public class Customer implements Serializable {
  private static final long serialVersionUID = 1L;
  public static final String CUSTOMERS = "customers";
  private static final String ID = "ID";
  private static final String NAME = "NAME";
  private static final String EMAIL = "EMAIL";
  private static final String PHONE_NUMBER = "PHONE_NUMBER";
  private static final String ADDRESS = "ADDRESS";

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid2")
  @Column(name = ID)
  private String id;
  @Column(name = NAME)
  private String name;
  @Column(name = EMAIL)
  private String email;
  @Column(name = PHONE_NUMBER)
  private String phoneNumber;
  @Column(name = ADDRESS)
  private String address;
}
