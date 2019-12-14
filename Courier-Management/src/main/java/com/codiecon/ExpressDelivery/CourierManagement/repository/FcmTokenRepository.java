package com.codiecon.ExpressDelivery.CourierManagement.repository;

import com.codiecon.ExpressDelivery.CourierManagement.entity.FCMToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FcmTokenRepository extends JpaRepository<FCMToken, String> {

  @Modifying
  @Query(value = "DELETE FROM FCMToken FT WHERE FT.email = :courierId AND FT.FcmToken = :fcmToken")
  void deleteToken(@Param("courierId") String courierId, @Param("fcmToken") String fcmToken);

  @Query(value = "SELECT FT FROM FCMToken FT where FT.email in (:courierId)")
  List<FCMToken> findAllFCMTokenById(@Param("courierId") List<String> courierId);

  String findFCMTokenByEmail(String email);
}
