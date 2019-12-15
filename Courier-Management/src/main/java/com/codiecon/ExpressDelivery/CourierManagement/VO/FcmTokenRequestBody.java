package com.codiecon.ExpressDelivery.CourierManagement.VO;

import java.io.Serializable;

public class FcmTokenRequestBody implements Serializable {
  private static final long serialVersionUID = 1L;
  private String to;
  private Integer priority;
  private FcmTokenDataRequestBody data;

  public FcmTokenRequestBody() {}

  public FcmTokenRequestBody(String to, Integer priority, FcmTokenDataRequestBody data) {
    this.to = to;
    this.priority = priority;
    this.data = data;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public Integer getPriority() {
    return priority;
  }

  public void setPriority(Integer priority) {
    this.priority = priority;
  }

  public FcmTokenDataRequestBody getData() {
    return data;
  }

  public void setData(FcmTokenDataRequestBody data) {
    this.data = data;
  }

}
