package com.codiecon.ExpressDelivery.CourierManagement.service.impl;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import com.codiecon.ExpressDelivery.CourierManagement.VO.FcmTokenDataRequestBody;
import com.codiecon.ExpressDelivery.CourierManagement.VO.FcmTokenRequestBody;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.PushNotificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PushNotificationServiceImpl implements PushNotificationService {
  public final static String AUTH_KEY_FCM =
      "key=AAAAcer1hec:APA91bF4XVCnUu21S4_33yboweqxhlat0E3ZF0Lnd8vZdeES7WJdUfhCcFCtIIuKyAFIkxhCbGOxQrAasFF2LTOKi-4mh615FsjlaRfpupex-Ki_lkMJRcB9ow1SSx4Q8VVyOlvG_uDN";

  public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

  private static final String AUTHORIZATION = "Authorization";
  private static final String CONTENT_TYPE = "Content-Type";
  private static final String APPLICATION_JSON = "application/json";
  private static final int NOTIFICATION_PRIORITY = 10;

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

  @Override
  public void sendNotification(String fcmToken, String notificationTitle,
      String notificationBody, String notificationType){

    try {
      HttpHeaders headers = new HttpHeaders();
      headers.add(AUTHORIZATION,
          AUTH_KEY_FCM);
      headers.add(CONTENT_TYPE, APPLICATION_JSON);
      FcmTokenDataRequestBody fcmTokenDataRequestBody =
          new FcmTokenDataRequestBody(notificationTitle, notificationBody, notificationType);
      FcmTokenRequestBody request =
          new FcmTokenRequestBody(fcmToken, NOTIFICATION_PRIORITY, fcmTokenDataRequestBody);
      ObjectMapper obj = new ObjectMapper();


      // get Oraganisation object as a json string
      String jsonStr = obj.writeValueAsString(request);

      sendPostRequest(API_URL_FCM,jsonStr,headers );
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

  }

  private ResponseEntity<String> sendPostRequest(String url, String body,
      HttpHeaders httpHeaders) {
    RestTemplate restTemplate = new RestTemplate();
    HttpEntity<String> request = new HttpEntity<String>(body, httpHeaders);
    return restTemplate.postForEntity(url, request, String.class, Collections.emptyMap());
  }
}
