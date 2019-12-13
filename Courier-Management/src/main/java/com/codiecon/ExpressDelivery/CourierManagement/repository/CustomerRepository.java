package com.codiecon.ExpressDelivery.CourierManagement.repository;

import com.codiecon.ExpressDelivery.CourierManagement.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,String> {}
