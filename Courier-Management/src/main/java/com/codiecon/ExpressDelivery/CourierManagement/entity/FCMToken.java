package com.codiecon.ExpressDelivery.CourierManagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = FCMToken.FCM_TOKEN)
@NoArgsConstructor
@AllArgsConstructor
public class FCMToken {
  public static final String FCM_TOKEN = "fcmToken";
  public static final String ID = "id";

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid2")
  @Column(name = ID)
  private String id;
  @Column(name = "email")
  private String email;
  @Column(name = "fcmToken")
  private String FcmToken;

  public FCMToken(String email, String fcmToken) {
    this.email = email;
    FcmToken = fcmToken;
  }
}
