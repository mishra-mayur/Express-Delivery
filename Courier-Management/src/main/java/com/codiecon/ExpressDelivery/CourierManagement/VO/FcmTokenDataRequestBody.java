package com.codiecon.ExpressDelivery.CourierManagement.VO;

import java.io.Serializable;

public class FcmTokenDataRequestBody implements Serializable {
  private static final long serialVersionUID = 1L;
  private String title;
  private String body;
  private String notification_type;

  public FcmTokenDataRequestBody(String title, String body, String notification_type) {
    this.title = title;
    this.body = body;
    this.notification_type = notification_type;
  }

  public FcmTokenDataRequestBody() {
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public String getNotification_type() {
    return notification_type;
  }

  public void setNotification_type(String notification_type) {
    this.notification_type = notification_type;
  }
}
