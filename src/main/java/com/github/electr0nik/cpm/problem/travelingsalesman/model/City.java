package com.github.electr0nik.cpm.problem.travelingsalesman.model;

/**
 * city is immutable after creation
 *
 * @since 1.0.0-SNAPSHOT
 */
public class City {

  private final String name;

  /**
   * usually a float point number ...
   * we are using Long for simplification
   *
   * @since 1.0.0-SNAPSHOT
   */
  private final Long latitude;
  private final Long longitude;

  /**
   * use primitive, since i don't want to check for null!
   *
   * @param name
   * @param latitude
   * @param longitude
   * @since 1.0.0-SNAPSHOT
   */
  public City(final String name, final long latitude, final long longitude) {
    if (latitude <= 0 && longitude <= 0) {
      throw new UnsupportedOperationException("Negative latitude and longitude values not supported yet!");
    }
    this.name = name;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public String getName() {
    return name;
  }

  public Long getLatitude() {
    return latitude;
  }

  public Long getLongitude() {
    return longitude;
  }

  @Override
  public String toString() {
    return " | " + name + ", x=" + latitude + ", y=" + longitude;
  }
}
