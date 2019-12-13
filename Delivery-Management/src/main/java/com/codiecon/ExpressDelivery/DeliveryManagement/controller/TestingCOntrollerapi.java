package com.codiecon.ExpressDelivery.DeliveryManagement.controller;

import com.codiecon.ExpressDelivery.DeliveryManagement.service.api.TestCLassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

public class TestingCOntrollerapi {

  @Autowired
  private TestCLassService testCLassService;




  @RequestMapping(method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
  public String compareEtds(@RequestParam String storeId,
      @RequestParam String channelId, @RequestParam String clientId, @RequestParam String requestId,
      @RequestParam(required = false) String username) throws Exception {
    return testCLassService.getData();
  }
}
