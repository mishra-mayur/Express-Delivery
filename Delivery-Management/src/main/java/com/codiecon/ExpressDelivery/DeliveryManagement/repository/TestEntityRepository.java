package com.codiecon.ExpressDelivery.DeliveryManagement.repository;

import com.codiecon.ExpressDelivery.DeliveryManagement.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestEntityRepository extends JpaRepository<TestEntity, Long> {

  TestEntity findByTestData(String testData);

}
