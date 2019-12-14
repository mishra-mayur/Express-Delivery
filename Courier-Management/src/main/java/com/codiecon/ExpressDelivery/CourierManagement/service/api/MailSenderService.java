package com.codiecon.ExpressDelivery.CourierManagement.service.api;

public interface MailSenderService {
  String sendMail(String emailId, String otp);
}
