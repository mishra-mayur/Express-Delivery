package com.codiecon.ExpressDelivery.CourierManagement.service.api;

import com.codiecon.ExpressDelivery.CourierManagement.entity.DistanceConstant;

public interface DistanceService {
  double distance(double lat1, double lat2, double lon1, double lon2, double el1, double el2);

  void saveDistanceConst(DistanceConstant distanceConstant);

  DistanceConstant getDistanceConstantFromLocation(String location);
}
