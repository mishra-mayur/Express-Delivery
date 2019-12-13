package com.codiecon.ExpressDelivery.CourierManagement.Enum;

public enum TripStatus {
  ACTIVE("Active, courier can take bookings"),
  INACTIVE("Inactive, courier is offline can not Serve now"),
  BUSY("Courier is serving for some other booking");

  private String status;

  TripStatus(String status) {
    this.status = status;
  }

  public String getStatus() {
    return status;
  }
}
