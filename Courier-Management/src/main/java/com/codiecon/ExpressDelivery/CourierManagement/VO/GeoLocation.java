package com.codiecon.ExpressDelivery.CourierManagement.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeoLocation implements Serializable {
  private static final long serialVersionUID = 1L;
  private double latitude;
  private double longitude;

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("GeoLocation{");
    sb.append("latitude=").append(latitude);
    sb.append(", longitude=").append(longitude);
    sb.append('}');
    return sb.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    GeoLocation that = (GeoLocation) o;
    return Double.compare(that.latitude, latitude) == 0
        && Double.compare(that.longitude, longitude) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(latitude, longitude);
  }
}