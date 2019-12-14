package com.codiecon.ExpressDelivery.CourierManagement.repository;

import com.codiecon.ExpressDelivery.CourierManagement.entity.LiveCourier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LiveCourierRepository extends JpaRepository<LiveCourier, String> {

  List<LiveCourier> findAllByStatus(TripStatus tripStatus);

  @Query(value =
             "SELECT LC FROM LiveCourier LC WHERE LC.status=:status and LC.latitude<:latMax and "
                 + " LC.latitude>:latMin and LC.longitude<:lonMax and LC.longitude>:lonMin")
  List<LiveCourier> findAllNearbyCouries(@Param("status") TripStatus tripStatus,
      @Param("latMax") double latMax, @Param("latMin") double latMin,
      @Param("lonMax") double lonMax, @Param("lonMin") double lonMin);
}
