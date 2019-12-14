package com.codiecon.ExpressDelivery.CourierManagement.service.api;

import java.util.List;

public interface FcmTokenService {

  void save(String email, String fcmToken);

  void deleteToken(String courierId, String fcmToken);

  String getFcmToken(String email);

  List<String> getFcmTokenList(List<String> courierIdList);
}
