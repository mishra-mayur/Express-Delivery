package com.codiecon.ExpressDelivery.CourierManagement.service.impl;

import com.codiecon.ExpressDelivery.CourierManagement.entity.FCMToken;
import com.codiecon.ExpressDelivery.CourierManagement.repository.FcmTokenRepository;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.FcmTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

  @Override
  public String getFcmToken(String email) {
    return fcmTokenRepository.findFCMTokenByEmail(email);
  }

  @Override
  public String getFcmTokenByEmailAndToken(String email, String token) {
    FCMToken fcmToken = fcmTokenRepository.findByEmailAndFcmToken(email, token);
    if(Objects.nonNull(fcmToken)){
      return fcmToken.getFcmToken();
    }
    return null;
  }

  @Override
  public List<String> getFcmTokenList(List<String> courierIdList) {
     List<FCMToken> list = fcmTokenRepository.findAllFCMTokenById(courierIdList);
     List<String> listFcmTokens = list.stream().map(FCMToken::getFcmToken).collect(Collectors.toList());
     return listFcmTokens;
  }
}
