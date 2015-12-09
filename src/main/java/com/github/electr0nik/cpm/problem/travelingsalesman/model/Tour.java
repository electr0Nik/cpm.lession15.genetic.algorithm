package com.github.electr0nik.cpm.problem.travelingsalesman.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tour {

  private Long distance;

  private Double fitness;

  /**
   * List of cities, to visit
   *
   * @since 1.0.0-SNAPSHOT
   */
  private List<City> cityList;


  public Tour() {
    distance = 0L;
    fitness = .0;
    cityList = new ArrayList<>();
  }

  /**
   * initialize cityList with null
   *
   * @param size
   * @since 1.0.0-SNAPSHOT
   */
  public Tour(int size) {
    this();
    for (int i = 0; i < size; i++) {
      this.cityList.add(null);
    }
  }

  public Tour(final List<City> cityList) {
    this();
    this.cityList = cityList;
  }

  public Double getFitness() {
    return fitness;
  }

  public void setFitness(Double fitness) {
    this.fitness = fitness;
  }

  public Long getDistance() {
    return distance;
  }

  public void setDistance(Long distance) {
    this.distance = distance;
  }

  /**
   * we don't want to modify this list
   *
   * @return
   * @since 1.0.0-SNAPSHOT
   */
  public List<City> getCityList() {
    return Collections.unmodifiableList(cityList);
  }

  /**
   * set/override city on specific index and reset fitness and distance
   *
   * @param index
   * @param city
   * @since 1.0.0-SNAPSHOT
   */
  public void setCity(final int index, final City city) {
    this.cityList.set(index, city);
    this.fitness = .0;
    this.distance = 0L;
  }

  @Override
  public String toString() {
    return "distance= " + this.distance + ", fitness=" + this.fitness + "\ncityList=" + this.cityList;
  }
}