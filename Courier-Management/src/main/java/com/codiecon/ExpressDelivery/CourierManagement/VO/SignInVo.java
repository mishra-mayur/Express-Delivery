package com.codiecon.ExpressDelivery.CourierManagement.VO;

import lombok.Data;

@Data
public class SignInVo {
  private String email;
  private String password;
  private String fcmToken;
}
