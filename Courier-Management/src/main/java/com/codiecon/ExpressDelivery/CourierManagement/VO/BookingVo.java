package com.codiecon.ExpressDelivery.CourierManagement.VO;

import lombok.Data;

@Data
public class BookingVo {
  private String customerName;
  private String customerPhone;
  private double deliveryLatitude;
  private double deliveryLongitude;
  private String pickupLocationAddress;
  private String deliveryLocationAddress;
  private double pickupLatitude;
  private double pickupLongitude;
}
