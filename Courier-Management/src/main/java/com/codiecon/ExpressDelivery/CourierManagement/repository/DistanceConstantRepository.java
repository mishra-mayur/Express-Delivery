package com.codiecon.ExpressDelivery.CourierManagement.repository;

import com.codiecon.ExpressDelivery.CourierManagement.entity.DistanceConstant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistanceConstantRepository extends JpaRepository<DistanceConstant, String> {

  DistanceConstant findByLocation(String location);


}
