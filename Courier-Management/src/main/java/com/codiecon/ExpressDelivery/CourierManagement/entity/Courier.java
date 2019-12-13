package com.codiecon.ExpressDelivery.CourierManagement.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = Courier.COURIER)
public class Courier implements Serializable {
  private static final long serialVersionUID = 1L;
  public static final String COURIER = "courier";
  private static final String ID = "ID";
  private static final String NAME = "NAME";
  private static final String EMAIL = "EMAIL";
  private static final String PHONE_NUMBER = "PHONE_NUMBER";
  private static final String VEHICLE_NUMBER = "VEHICLE_NUMBER";
  private static final String ADDRESS = "ADDRESS";

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid2")
  @Column(name = ID)
  private long id;
  @Column(name = NAME)
  private String name;
  @Column(name = EMAIL)
  private String email;
  @Column(name = PHONE_NUMBER)
  private String phoneNumber;
  @Column(name = VEHICLE_NUMBER)
  private String vehicleNumber;
  @Column(name = ADDRESS)
  private String address;

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Courier{");
    sb.append("id=").append(id);
    sb.append(", name='").append(name).append('\'');
    sb.append(", email='").append(email).append('\'');
    sb.append(", phoneNumber='").append(phoneNumber).append('\'');
    sb.append(", vehicleNumber='").append(vehicleNumber).append('\'');
    sb.append(", Address='").append(address).append('\'');
    sb.append('}');
    return sb.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Courier courier = (Courier) o;
    return id == courier.id && name.equals(courier.name) && email.equals(courier.email)
        && vehicleNumber.equals(courier.vehicleNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, email, vehicleNumber);
  }
}