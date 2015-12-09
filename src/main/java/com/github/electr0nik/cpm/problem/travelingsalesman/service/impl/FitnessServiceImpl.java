package com.github.electr0nik.cpm.problem.travelingsalesman.service.impl;

import com.github.electr0nik.cpm.problem.travelingsalesman.model.Tour;
import com.github.electr0nik.cpm.problem.travelingsalesman.service.DistanceService;
import com.github.electr0nik.cpm.problem.travelingsalesman.service.FitnessService;

import java.util.List;


public class FitnessServiceImpl implements FitnessService {

  private final DistanceService distanceService;

  //@Inject
  public FitnessServiceImpl() {
    this.distanceService = new DistanceServiceImpl();
  }

  /**
   * initialize fittest Individual from first element in list and loop through individuals to find fittest
   *
   * @param populatedTourList
   * @return
   * @since 1.0.0-SNAPSHOT
   */
  public Tour getFittest(final List<Tour> populatedTourList) {
    final Tour[] fittestIndividual = {populatedTourList.get(0)};
    populatedTourList.forEach(problem -> {
      final Long distance = distanceService.getTotalDistance(problem.getDistance(), problem);
      problem.setDistance(distance);
      problem.setFitness(this.getFitness(problem.getFitness(), distance));
      if (fittestIndividual[0].getFitness() <= problem.getFitness())
        fittestIndividual[0] = problem;
    });
    return fittestIndividual[0];
  }

  /**
   * Gets the fitness
   *
   * @param fitness
   * @param distance
   * @return
   * @since 1.0.0-SNAPSHOT
   */
  private Double getFitness(final Double fitness, final Long distance) {
    final Double returnFitness;
    if (fitness == .0) {
      returnFitness = 1 / distance.doubleValue();
    } else {
      returnFitness = fitness;
    }
    return returnFitness;
  }

}
