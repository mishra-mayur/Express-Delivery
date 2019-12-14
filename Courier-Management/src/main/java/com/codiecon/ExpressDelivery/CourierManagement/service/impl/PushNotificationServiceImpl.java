package com.codiecon.ExpressDelivery.CourierManagement.service.impl;
import java.io.IOException;
import java.util.List;

import com.codiecon.ExpressDelivery.CourierManagement.service.api.PushNotificationService;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import org.springframework.stereotype.Service;

@Service
public class PushNotificationServiceImpl implements PushNotificationService {
  public final static String AUTH_KEY_FCM = "your key ";
  public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

  @Override
  public void sendPushNotification(List<String> deviceTokenList) {
    Sender sender = new Sender(AUTH_KEY_FCM);
    Message msg = new Message.Builder().addData("message", "Message body")
        .build();
    try {
      MulticastResult result = sender.send(msg, deviceTokenList, 5);
      for (Result r : result.getResults()) {
        if (r.getMessageId() != null)
          System.out.println("Push Notification Sent Successfully");
        else
          System.out.println("ErrorCode " + r.getErrorCodeName());
      }
    } catch (IOException e) {
      System.out.println("Error " + e.getLocalizedMessage());
    }
  }
}
