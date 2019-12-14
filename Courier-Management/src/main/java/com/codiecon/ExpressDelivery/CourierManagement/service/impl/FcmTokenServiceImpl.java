package com.codiecon.ExpressDelivery.CourierManagement.service.impl;

import com.codiecon.ExpressDelivery.CourierManagement.entity.FCMToken;
import com.codiecon.ExpressDelivery.CourierManagement.repository.FcmTokenRepository;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.FcmTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FcmTokenServiceImpl implements FcmTokenService {

  @Autowired
  FcmTokenRepository fcmTokenRepository;

  @Override
  public void save(String email, String fcmToken) {
    FCMToken fcmToken1 = new FCMToken(email,fcmToken);
    fcmTokenRepository.save(fcmToken1);
  }

  @Override
  @Transactional
  public void deleteToken(String courierId, String fcmToken) {
    fcmTokenRepository.deleteToken(courierId, fcmToken);
  }
}
