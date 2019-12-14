package com.codiecon.ExpressDelivery.CourierManagement.repository;

import com.codiecon.ExpressDelivery.CourierManagement.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, String> {

  Merchant findByEmail(String email);
  void deleteByEmail(String email);
  void deleteAllByEmail(String email);
  @Modifying
  @Query(value = "DELETE FROM Merchant M WHERE M.email = :email")
  void deleteMerchantByEmail(@Param("email") String email);
}

