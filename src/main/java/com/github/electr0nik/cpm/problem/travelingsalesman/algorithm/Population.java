package com.github.electr0nik.cpm.problem.travelingsalesman.algorithm;

import com.github.electr0nik.cpm.problem.travelingsalesman.model.City;
import com.github.electr0nik.cpm.problem.travelingsalesman.model.Tour;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Population {
  private final List<Tour> populatedProblemList;

  /**
   * populate list with empty elements
   *
   * @param size
   * @since 1.0.0-SNAPSHOT
   */
  public Population(final int size) {
    this.populatedProblemList = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      populatedProblemList.add(null);
    }
  }

  /**
   * initialize population with given size
   * <p>
   * use primitives to avoid NPE
   *
   * @param populationSize
   * @param initialise
   * @param cityList
   */
  public Population(final int populationSize, final boolean initialise, final List<City> cityList) {
    this(populationSize);
    if (initialise) {
      /**
       * no ~.foreach(),
       * because the elements of list are null and reinitialization won't work properly
       */
      for (int i = 0; i < populationSize; i++) {
        // Saves a population
        final List<City> tmpList = new ArrayList<>();
        tmpList.addAll(cityList);
        // Randomly reorder the population
        Collections.shuffle(tmpList);
        populatedProblemList.set(i, new Tour(tmpList));
      }
    }
  }

  public List<Tour> getPopulatedProblemList() {
    return populatedProblemList;
  }
}