package com.codiecon.ExpressDelivery.CourierManagement.repository;

import com.codiecon.ExpressDelivery.CourierManagement.Enum.CourierStatus;
import com.codiecon.ExpressDelivery.CourierManagement.entity.LiveCourier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LiveCourierRepository extends JpaRepository<LiveCourier, String> {

  List<LiveCourier> findAllByStatus(CourierStatus courierStatus);

  LiveCourier findByCourierId(String courierId);

  @Query(value =
             "SELECT LC FROM LiveCourier LC WHERE LC.status=:status and LC.latitude<:latMax and "
                 + " LC.latitude>:latMin and LC.longitude<:lonMax and LC.longitude>:lonMin")
  List<LiveCourier> findAllNearbyCouries(@Param("status") CourierStatus courierStatus,
      @Param("latMax") double latMax, @Param("latMin") double latMin,
      @Param("lonMax") double lonMax, @Param("lonMin") double lonMin);

  @Modifying
  @Query(value = "UPDATE LiveCourier LC SET LC.latitude = :latitude , LC.longitude = :longitude  "
      + "WHERE LC.courierId = :courierId")
  void updateLiveLocation(@Param("courierId") String courierId, @Param("latitude") double latitude,
      @Param("longitude") double longitude);

  @Modifying
  @Query(value = "UPDATE LiveCourier LC SET LC.status = :status WHERE LC.courierId = :email")
  int updateCourierStatus(@Param("email") String email, @Param("status") CourierStatus status);
}
