package com.codiecon.ExpressDelivery.CourierManagement.service.api;

import com.codiecon.ExpressDelivery.CourierManagement.entity.Merchant;

public interface MerchantService {

  Merchant getMerchantByEmail(String email);

  void deleteMerchant(String email);

  void save(Merchant merchant);

}
