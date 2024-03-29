package com.codiecon.ExpressDelivery.CourierManagement.controller;

import com.codiecon.ExpressDelivery.CourierManagement.VO.CourierStatusUpdateRequest;
import com.codiecon.ExpressDelivery.CourierManagement.VO.SignInVo;
import com.codiecon.ExpressDelivery.CourierManagement.entity.Courier;
import com.codiecon.ExpressDelivery.CourierManagement.entity.LiveCourier;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.CourierService;
import com.codiecon.ExpressDelivery.CourierManagement.service.api.LiveCourierService;
import com.gdn.tms.util.rest.model.response.BaseResponse;
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
@RequestMapping(value = CourierController.COURIER)
@Api(value = "CourierController", description = "Courier Controller")
public class CourierController {
  public static final String COURIER = "Courier";
  public static final String SIGNUP = "/signup";

  @Autowired
  private CourierService courierService;

  @Autowired
  LiveCourierService liveCourierService;

  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
                  produces = MediaType.APPLICATION_JSON_VALUE, value = SIGNUP)
  public BaseResponse addCourier(@RequestBody Courier courier) {
    log.info("{}", courier.toString());
    boolean status = courierService.signUp(courier);
    LiveCourier liveCourier = new LiveCourier("", courier.getEmail(), courier.getStatus(), 0, 0);
    liveCourierService.save(liveCourier);
    if (status) {
      return new BaseResponse(true, HttpStatus.OK.value());
    } else {
      return new BaseResponse("UNABLE TO SAVE COURIER", "Error in saving courier");
    }
  }

  @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
                  produces = MediaType.APPLICATION_JSON_VALUE)
  public BaseResponse updateStatus(@RequestBody CourierStatusUpdateRequest request) {
    log.info(" {}", request.getEmail());
    if (courierService.updateCourierStatus(request.getEmail(), request.getStatus())) {
      return new BaseResponse(true, HttpStatus.OK.value());
    } else {
      return new BaseResponse("Status not updated","Error in updating status");
    }

  }

  @RequestMapping(method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
  public BaseResponse deleteCourier(@RequestParam("email") String email) {
    log.info("request for deleting Courier{}", email);
    if (courierService.getCourierByEmail(email) != null) {
      courierService.deleteCourier(email);
    } else {
      return new BaseResponse("Record Not Found", "No records with this email are present");
    }
    return new BaseResponse(true, HttpStatus.OK.value());
  }

  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public BaseResponse verifyOtp(@RequestParam("email") String email,
      @RequestParam("otp") String otp) {
    log.info("request for checking otp verification");
    if (courierService.verifyOtp(email, otp)) {
      return new BaseResponse(true, HttpStatus.OK.value());
    } else
      return new BaseResponse("Wrong OTP entered", "You have entered wrong OTP please try again");
  }

  @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
                  value = "/signIn")
  public BaseResponse signIn(@RequestBody SignInVo signInVo) {
    log.info("request for checking otp verification");
    if (courierService.signIn(signInVo)) {
      return new BaseResponse(true, HttpStatus.OK.value());
    } else
      return new BaseResponse("Wrong Credentials entered",
          "You have entered wrong Credentials please check your username and password");
  }

  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, value
      = "/signOut")
  public BaseResponse signOut(@RequestParam("courierId") String courierId,
      @RequestParam("fcmToken") String fcmToken) {
    log.info("request for checking otp verification");
    if (courierService.signOut(courierId, fcmToken)) {
      return new BaseResponse(true, HttpStatus.OK.value());
    } else
      return new BaseResponse("Wrong Credentials entered",
          "You have entered wrong Credentials please check your username and password");
  }


}
