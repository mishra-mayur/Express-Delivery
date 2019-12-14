package com.codiecon.ExpressDelivery.CourierManagement.repository;

import com.codiecon.ExpressDelivery.CourierManagement.entity.LiveCourier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiveCourierRepository extends JpaRepository<LiveCourier, String> {

//  List<LiveCourier> findAllByTripStatus(TripStatus tripStatus);
}
