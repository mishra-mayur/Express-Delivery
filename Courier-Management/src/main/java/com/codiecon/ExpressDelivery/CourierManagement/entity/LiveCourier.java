package com.codiecon.ExpressDelivery.CourierManagement.entity;

import com.codiecon.ExpressDelivery.CourierManagement.Enum.TripStatus;
import com.codiecon.ExpressDelivery.CourierManagement.VO.GeoLocation;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = LiveCourier.LIVE_COURIER)
public class LiveCourier implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String LIVE_COURIER = "live_courier";
  private static final String ID = "ID";

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid2")
  @Column(name = ID)
  private String id;
  private String courierId;
  private GeoLocation courierLocation;
  private TripStatus status;

}
