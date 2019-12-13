package com.codiecon.ExpressDelivery.CourierManagement.repository;

import com.codiecon.ExpressDelivery.CourierManagement.entity.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierRepository extends JpaRepository<Courier, String> {}
