package com.codiecon.ExpressDelivery.CourierManagement.controller;

import com.codiecon.ExpressDelivery.CourierManagement.Enum.CourierStatus;
import com.codiecon.ExpressDelivery.CourierManagement.entity.LiveCourier;
import com.codiecon.ExpressDelivery.CourierManagement.entity.Merchant;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.LiveCourierService;
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
@RequestMapping(value = LiveCourierController.LIVE_COURIER)
@Api(value = "LiveCourierController", description = "Live Courier Controller")
public class LiveCourierController {
  public static final String LIVE_COURIER = "LiveCourier";

  @Autowired
  private LiveCourierService liveCourierService;

  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
  public BaseResponse addLiveCourier(@RequestBody LiveCourier liveCourier) {
    log.info("{}", liveCourier.toString());
    liveCourierService.save(liveCourier);
    return new BaseResponse(true, HttpStatus.OK.value());
  }

  @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
  public BaseResponse updateLocation(
      @RequestParam("courierId") String courierId, @RequestParam("latitude") double latitude, @RequestParam("longitude") double longitude) {
    log.info(" {}", courierId);
    liveCourierService.updateLiveLocation(courierId, latitude, longitude);
    return new BaseResponse(true, HttpStatus.OK.value());
  }

  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public BaseSingleResponse<LiveCourier> getLiveLocation(@RequestParam("courierId") String courierId) {
    log.info("fetching live location");
    return new BaseSingleResponse(true, HttpStatus.OK.value(), liveCourierService.findLiveCourierByCourierId(courierId));
  }
}
