package com.codiecon.ExpressDelivery.CourierManagement.service.impl;

import com.codiecon.ExpressDelivery.CourierManagement.Enum.CourierStatus;
import com.codiecon.ExpressDelivery.CourierManagement.entity.Courier;
import com.codiecon.ExpressDelivery.CourierManagement.repository.CourierRepository;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.CourierService;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.PasswordEncryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
public class CourierServiceImpl implements CourierService {

  private CourierRepository courierRepository;
  private PasswordEncryption passwordEncryption;
  Random random = new Random();

  @Autowired
  public CourierServiceImpl(CourierRepository courierRepository,
      PasswordEncryptionImpl passwordEncryption) {
    this.courierRepository = courierRepository;
    this.passwordEncryption = passwordEncryption;
  }

  @Override
  public boolean signUp(Courier courier) {
    String encryptedPassword = passwordEncryption.encrypt(courier.getPassword());
    if (encryptedPassword == null) {
      return false;
    }
    courier.setPassword(encryptedPassword);
    courier.setStatus(CourierStatus.OTP_NOT_VERIFIED);
    String otp = String.format("%04d", random.nextInt(10000));
    //TODO
    /*
    Send OTP verification mail
     */

    courier.setOtp(otp);
    courierRepository.save(courier);
    return true;
  }

  @Override
  @Transactional
  public void deleteCourier(String email) {
    courierRepository.deleteAllByEmail(email);
  }

  @Override
  public void updateCourierStatus(String email, CourierStatus status) {
    courierRepository.updateCourierStatus(email, status);
  }

  @Override
  public Courier getCourierByEmail(String email) {
    return courierRepository.getCourierByEmail(email);
  }

  @Override
  @Transactional
  public boolean verifyOtp(String email, String otp) {
    String courierOtp = courierRepository.getCourierByEmail(email).getOtp();
    if(courierOtp.equals(otp)){
      courierRepository.updateCourierStatus(email,CourierStatus.INACTIVE);
      return true;
    }
    return false;
  }
}
