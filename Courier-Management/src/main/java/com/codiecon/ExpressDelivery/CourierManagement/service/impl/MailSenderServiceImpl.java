package com.codiecon.ExpressDelivery.CourierManagement.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
public class MailSenderServiceImpl implements MailSenderService {

  @Autowired
  private JavaMailSender sender;

  @Value("${otp.verify.base.url}")
  private String otpBaseUrl;

  @Override
  public String sendMail(String emailId,String otp) {
    MimeMessage message = sender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message);

    try {
      helper.setTo(emailId);
      helper.setText("Greetings : Thanks for registering with us !!!" +
          "\n=======================================================" +
          "\n" + "Your OTP for Registering is " +   otp    +
          "\n" + "please click on following url for verifying otp " + createOtpUrl(emailId,otp) +
          "\n======================================================="

      );
      helper.setSubject("Mail From ED");
    } catch (MessagingException e) {
      e.printStackTrace();
      return "Error while sending mail ..";
    }
    sender.send(message);
    return "Mail Sent Success!";
  }


  private String createOtpUrl(String emailId, String otp) {
    Map<String, String> requestParameters = new HashMap<>();
    requestParameters.put("email", emailId);
    requestParameters.put("otp", otp);
    MultiValueMap<String, String> queryParams = getQueryParams(requestParameters);
    UriComponents uriComponents =
        UriComponentsBuilder.fromHttpUrl(otpBaseUrl).queryParams(queryParams).build();
    return uriComponents.toUriString();
  }

  private MultiValueMap<String, String> getQueryParams(Map<String, String> requestParameters) {
    MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
    requestParameters.forEach((key, value) -> {
      queryParams.add(key, value);
    });
    return queryParams;
  }
}
