package com.codiecon.ExpressDelivery.CourierManagement.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailSenderServiceImpl implements MailSenderService {

  @Autowired
  private JavaMailSender sender;

  @Override
  public String sendMail(String emailId,String otp) {
    MimeMessage message = sender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message);

    try {
      helper.setTo(emailId);
      helper.setText("                                Greetings : Thanks for registering with us !!!" +
          "                                =======================================================" +
          "\n" +               "Your OTP for Registering is " +   otp    +             "\n" +
          "                                ======================================================="

      );
      helper.setSubject("Mail From ED");
    } catch (MessagingException e) {
      e.printStackTrace();
      return "Error while sending mail ..";
    }
    sender.send(message);
    return "Mail Sent Success!";
  }
}
