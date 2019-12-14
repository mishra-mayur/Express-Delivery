package com.codiecon.ExpressDelivery.CourierManagement.Enum;

public enum CourierStatus {
  OTP_NOT_VERIFIED("OPT of courier is not verified "),
  ACTIVE("Active, courier can take bookings"),
  INACTIVE("Inactive, courier is offline can not Serve now"),
  BUSY("Courier is serving for some other booking"),
  ED_NOT_VERIFIED("Vehicle of the courier is not verified by Express Delivery");


  private String status;

  CourierStatus(String status) {
    this.status = status;
  }

  public String getStatus() {
    return status;
  }
}
