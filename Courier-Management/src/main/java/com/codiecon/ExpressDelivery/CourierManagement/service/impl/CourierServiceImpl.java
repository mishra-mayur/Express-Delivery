package com.codiecon.ExpressDelivery.CourierManagement.service.impl;

import com.codiecon.ExpressDelivery.CourierManagement.Enum.CourierStatus;
import com.codiecon.ExpressDelivery.CourierManagement.VO.SignInVo;
import com.codiecon.ExpressDelivery.CourierManagement.entity.Courier;
import com.codiecon.ExpressDelivery.CourierManagement.repository.CourierRepository;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.CourierService;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.FcmTokenService;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.LiveCourierService;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.MailSenderService;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.PasswordEncryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Random;

@Service
public class CourierServiceImpl implements CourierService {

  private CourierRepository courierRepository;
  private PasswordEncryption passwordEncryption;
  private MailSenderService mailSenderService;
  private FcmTokenService fcmTokenService;
  private LiveCourierService liveCourierService;
  Random random = new Random();

  @Autowired
  public CourierServiceImpl(CourierRepository courierRepository,
      PasswordEncryptionImpl passwordEncryption, MailSenderService mailSenderService,
      FcmTokenService fcmTokenService, LiveCourierService liveCourierService) {
    this.courierRepository = courierRepository;
    this.passwordEncryption = passwordEncryption;
    this.mailSenderService = mailSenderService;
    this.fcmTokenService = fcmTokenService;
    this.liveCourierService = liveCourierService;
  }

  @Override
  public boolean signUp(Courier courier) {
    try {
      String encryptedPassword = passwordEncryption.encrypt(courier.getPassword());
      if (encryptedPassword == null) {
        return false;
      }
      courier.setPassword(encryptedPassword);
      courier.setStatus(CourierStatus.OTP_NOT_VERIFIED);
      String otp = String.format("%04d", random.nextInt(10000));
      courier.setOtp(otp);
      courierRepository.save(courier);
      mailSenderService.sendMail(courier.getEmail(), otp);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  @Transactional
  public void deleteCourier(String email) {
    courierRepository.deleteAllByEmail(email);
  }

  @Override
  @Transactional
  public boolean updateCourierStatus(String email, CourierStatus status) {
    boolean updated = liveCourierService.updateStatus(email, CourierStatus.INACTIVE);
    boolean updatedDone = courierRepository
        .updateCourierStatus(email, CourierStatus.INACTIVE)==1;
    return updated && updatedDone;
  }

  @Override
  public Courier getCourierByEmail(String email) {
    return courierRepository.getCourierByEmail(email);
  }

  @Override
  @Transactional
  public boolean verifyOtp(String email, String otp) {
    Courier courier = courierRepository.getCourierByEmail(email);
    if (Objects.nonNull(courier)) {
      String courierOtp = courier.getOtp();
      if (courierOtp.equals(otp)) {
        courierRepository.updateCourierStatus(email, CourierStatus.INACTIVE);
        liveCourierService.updateStatus(email, CourierStatus.INACTIVE);
        return true;
      }
    }
    return false;
  }

  @Override
  @Transactional
  public boolean signIn(SignInVo signInVo) {
    Courier courier = courierRepository.getCourierByEmail(signInVo.getEmail());
    if (Objects.nonNull(courier)) {
      String encryptedPassword = passwordEncryption.encrypt(signInVo.getPassword());
      if (encryptedPassword.equals(courier.getPassword())) {
        String fcmToken =
            fcmTokenService.getFcmTokenByEmailAndToken(signInVo.getEmail(), signInVo.getFcmToken());
        liveCourierService.updateStatus(signInVo.getEmail(), CourierStatus.ACTIVE);
        courierRepository.updateCourierStatus(signInVo.getEmail(), CourierStatus.ACTIVE);
        if (Objects.isNull(fcmToken)) {
          fcmTokenService.save(signInVo.getEmail(), signInVo.getFcmToken());
        }
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean signOut(String courierId, String fcmToken) {
    boolean updated = false;
    boolean updatedDone = false;
    try {
      fcmTokenService.deleteToken(courierId, fcmToken);
      updated = liveCourierService.updateStatus(courierId, CourierStatus.INACTIVE);
      updatedDone = courierRepository
          .updateCourierStatus(courierId, CourierStatus.INACTIVE)==1;
    } catch (Exception e) {
      return false;
    }
    return updated && updatedDone;
  }
}
