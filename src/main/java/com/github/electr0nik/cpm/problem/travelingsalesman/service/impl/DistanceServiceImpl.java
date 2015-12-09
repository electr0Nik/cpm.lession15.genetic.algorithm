package com.github.electr0nik.cpm.problem.travelingsalesman.service.impl;

import com.github.electr0nik.cpm.problem.travelingsalesman.model.City;
import com.github.electr0nik.cpm.problem.travelingsalesman.model.Tour;
import com.github.electr0nik.cpm.problem.travelingsalesman.service.DistanceService;

public class DistanceServiceImpl implements DistanceService {

  /**
   * Use Pythagoras to estimate the distance of two city
   * -> c = √(a² * b²)
   * <p>
   * Since this gives you only a straight line, you wouldn't use this for real development
   * In production ready code you usually have call an extra Web-Service like Google-Maps, but for the sake of simplicity...
   * let's go with Pythagoras
   *
   * @param city
   * @param cityToCompare
   * @return
   * @since 1.0.0-SNAPSHOT
   */
  public Double getPythagorasDistance(final City city, final City cityToCompare) {
    if (city == null || cityToCompare == null) {
      throw new IllegalStateException("Must not be null!");
    }
    final Long latitudeDistance = Math.abs(city.getLatitude() - cityToCompare.getLatitude());
    final Long longitudeDistance = Math.abs(city.getLongitude() - cityToCompare.getLongitude());

    return this.getHypotenuse(latitudeDistance, longitudeDistance);
  }

  /**
   * Returns the hypotenuse
   * <p>
   * use primitive since i don't want to check for null
   *
   * @param a
   * @param b
   * @return c
   * @since 1.0.0-SNAPSHOT
   */
  private Double getHypotenuse(final long a, final long b) {
    return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
  }

  /**
   * returns the total cost/distance
   *
   * @return
   * @since 1.0.0-SNAPSHOT
   */
  public Long getTotalDistance(final Long totalTourDistance, final Tour tour) {
    Long returnDistance = totalTourDistance;
    if (totalTourDistance == 0L) {
      final Long[] distance = {0L};
      final int[] index = {0};
      tour.getCityList().forEach(aCity -> {
        index[0]++;
        final City bCity;

        /**
         * compare element from
         * <b>index</b>
         * and
         * <b>index + 1</b>
         *
         * if <b>index + 1</b> is greater than last element, compare with first element
         */
        if (index[0] < tour.getCityList().size()) {
          bCity = tour.getCityList().get(index[0]);
        } else {
          bCity = tour.getCityList().get(0);
        }

        distance[0] += this.getPythagorasDistance(aCity, bCity).longValue();
      });
      returnDistance = distance[0];
    }
    return returnDistance;
  }

}
