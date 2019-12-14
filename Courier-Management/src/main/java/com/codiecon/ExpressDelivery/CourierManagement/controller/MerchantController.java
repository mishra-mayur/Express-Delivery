package com.codiecon.ExpressDelivery.CourierManagement.controller;

import com.codiecon.ExpressDelivery.CourierManagement.entity.Merchant;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.MerchantService;
import com.codiecon.ExpressDelivery.CourierManagement.util.ApiPath;
import com.gdn.tms.util.rest.model.response.BaseResponse;
import com.gdn.tms.util.rest.model.response.BaseSingleResponse;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = ApiPath.MERCHANT)
@Api(value = "MerchantController", description = "Merchant Controller")
public class MerchantController {

  private static final String EMAIL = "email";
  @Autowired
  private MerchantService merchantService;

  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
  public BaseResponse addMerchant(@RequestBody Merchant merchant) {
    log.info("{}", merchant.toString());
    merchantService.save(merchant);
    return new BaseResponse(true, HttpStatus.OK.value());
  }

  @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
  public BaseResponse updateMerchantDetails(
      @RequestBody Merchant merchant) {
    log.info(" {}", merchant.toString());
    Merchant merchant1 = merchantService.getMerchantByEmail(merchant.getEmail());
    merchantService.deleteMerchant(merchant1.getEmail());
    merchantService.save(merchant);
    return new BaseResponse(true, HttpStatus.OK.value());
  }

  @RequestMapping(method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
  public BaseResponse deleteMerchant(@RequestParam(EMAIL) String email) {
    log.info("request for deleting Merchant{}", email);
    if (merchantService.getMerchantByEmail(email) != null) {
      merchantService.deleteMerchant(email);
    } else {
      return new BaseResponse("Record Not Found","No records with this email are present");
    }
    return new BaseResponse(true, HttpStatus.OK.value());
  }

  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public BaseSingleResponse<Merchant> getMerchant(@RequestParam(EMAIL) String email) {
    log.info("request for fetching Merchant");
    return new BaseSingleResponse(true, HttpStatus.OK.value(), merchantService.getMerchantByEmail(email));
  }
}
