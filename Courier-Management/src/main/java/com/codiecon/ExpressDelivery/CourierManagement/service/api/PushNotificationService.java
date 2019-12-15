package com.codiecon.ExpressDelivery.CourierManagement.service.api;

import java.util.List;

public interface PushNotificationService {
  void sendPushNotification(List<String> deviceTokenList);

  void sendNotification(String fcmToken, String notificationTitle, String notificationBody,
      String notificationType);
}
