package com.codiecon.ExpressDelivery.CourierManagement.repository;

import com.codiecon.ExpressDelivery.CourierManagement.Enum.CourierStatus;
import com.codiecon.ExpressDelivery.CourierManagement.entity.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierRepository extends JpaRepository<Courier, String> {

  int deleteAllByEmail(String email);
  Courier getCourierByEmail(String email);

  @Modifying
  @Query(value = "UPDATE Courier C SET C.status = :status WHERE C.email =:email")
  int updateCourierStatus(@Param("email") String email,@Param("status") CourierStatus status);

}
