package com.codiecon.ExpressDelivery.DeliveryManagement.controller;

import com.codiecon.ExpressDelivery.DeliveryManagement.entity.TestEntity;
import com.codiecon.ExpressDelivery.DeliveryManagement.service.api.BookingService;
import com.codiecon.ExpressDelivery.DeliveryManagement.service.api.TestCLassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/booking")
public class BookingController {




  @Autowired
  private BookingService bookingService;




//  @RequestMapping(method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
//  public TestEntity compareEtds(
//      @RequestParam String testData) throws Exception {
//    TestEntity testEntity = new TestEntity();
//    testEntity.setTestData(testCLassService.getData(testData));
//    return testEntity;
//  }
}
