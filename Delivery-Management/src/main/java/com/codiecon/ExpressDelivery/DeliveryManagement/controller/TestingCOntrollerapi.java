package com.codiecon.ExpressDelivery.DeliveryManagement.controller;

import com.codiecon.ExpressDelivery.DeliveryManagement.entity.TestEntity;
import com.codiecon.ExpressDelivery.DeliveryManagement.service.api.TestCLassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping(value = "ETD_COMPARE")
public class TestingCOntrollerapi {

  @Autowired
  private TestCLassService testCLassService;




  @RequestMapping(method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
  public TestEntity compareEtds(
      @RequestParam String testData) throws Exception {
    TestEntity testEntity = new TestEntity();
    testEntity.setTestData(testCLassService.getData(testData));
    return testEntity;
  }
}
