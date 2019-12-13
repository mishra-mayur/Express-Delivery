package com.codiecon.ExpressDelivery.DeliveryManagement.service.impl;

import com.codiecon.ExpressDelivery.DeliveryManagement.entity.TestEntity;
import com.codiecon.ExpressDelivery.DeliveryManagement.repository.TestEntityRepository;
import com.codiecon.ExpressDelivery.DeliveryManagement.service.api.TestCLassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestCLassServiceImpl implements TestCLassService {

  @Autowired
  private TestEntityRepository testEntityRepository;

@Override
  public String getData(String testData) {
    return testEntityRepository.findByTestData(testData).getTestData();
  }
}
