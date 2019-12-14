package com.codiecon.ExpressDelivery.CourierManagement.service.api;

import com.codiecon.ExpressDelivery.CourierManagement.Enum.CourierStatus;
import com.codiecon.ExpressDelivery.CourierManagement.VO.SignInVo;
import com.codiecon.ExpressDelivery.CourierManagement.entity.Courier;

import org.springframework.transaction.annotation.Transactional;

public interface CourierService {
  boolean signUp(Courier courier);

  @Transactional
  void deleteCourier(String email);

  Courier getCourierByEmail(String email);

  @Transactional
  void updateCourierStatus(String email, CourierStatus status);

  boolean verifyOtp(String email, String otp);

  boolean signIn(SignInVo signInVo);

  boolean signOut(String courierId, String fcmToken);
}
