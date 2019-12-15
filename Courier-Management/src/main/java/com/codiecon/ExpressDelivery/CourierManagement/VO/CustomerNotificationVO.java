package com.codiecon.ExpressDelivery.CourierManagement.VO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerNotificationVO {
  private String courierId;
  private String name;
  private String vehicleNo;
  private String phoneNumber;
}
