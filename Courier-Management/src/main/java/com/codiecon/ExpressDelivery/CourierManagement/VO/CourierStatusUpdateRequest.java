package com.codiecon.ExpressDelivery.CourierManagement.VO;

import com.codiecon.ExpressDelivery.CourierManagement.Enum.CourierStatus;
import lombok.Data;

@Data
public class CourierStatusUpdateRequest {

  private String email;

  private CourierStatus status;
}
