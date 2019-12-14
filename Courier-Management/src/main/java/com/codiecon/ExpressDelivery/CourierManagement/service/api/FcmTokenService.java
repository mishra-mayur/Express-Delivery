package com.codiecon.ExpressDelivery.CourierManagement.service.api;

public interface FcmTokenService {

  void save(String email, String fcmToken);

  void deleteToken(String courierId, String fcmToken);
}
