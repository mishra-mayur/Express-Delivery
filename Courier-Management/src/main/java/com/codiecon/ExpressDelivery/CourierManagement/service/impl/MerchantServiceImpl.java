package com.codiecon.ExpressDelivery.CourierManagement.service.impl;

import com.codiecon.ExpressDelivery.CourierManagement.entity.Merchant;
import com.codiecon.ExpressDelivery.CourierManagement.repository.MerchantRepository;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MerchantServiceImpl implements MerchantService {

  @Autowired
  private MerchantRepository merchantRepository;

  @Override
  public Merchant getMerchantByEmail(String email) {
    return merchantRepository.findByEmail(email);
  }

  @Override
  @Transactional
  public void deleteMerchant(String email) {
    merchantRepository.deleteAllByEmail(email);
  }

  @Override
  public void save(Merchant merchant) {
    merchantRepository.save(merchant);
  }
}
