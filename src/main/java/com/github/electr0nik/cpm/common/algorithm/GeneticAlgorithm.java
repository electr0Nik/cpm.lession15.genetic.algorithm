package com.github.electr0nik.cpm.common.algorithm;


import com.github.electr0nik.cpm.problem.travelingsalesman.algorithm.Population;
import com.github.electr0nik.cpm.problem.travelingsalesman.model.City;
import com.github.electr0nik.cpm.problem.travelingsalesman.model.Tour;
import com.github.electr0nik.cpm.problem.travelingsalesman.service.FitnessService;
import com.github.electr0nik.cpm.problem.travelingsalesman.service.impl.FitnessServiceImpl;

import java.util.List;

public class GeneticAlgorithm {
  private final Integer tournamentSize;

  private final Double mutationRate;

  private final Boolean isFittest;

  // @Inject
  private final FitnessService fitnessService;

  public GeneticAlgorithm(final Integer tournamentSize, final Double mutationRate, final Boolean isFittest) {
    this.tournamentSize = tournamentSize;
    this.mutationRate = mutationRate;
    this.isFittest = isFittest;
    this.fitnessService = new FitnessServiceImpl();
  }

  /**
   * evolves population over generation, with given problem
   * <p>
   * crossover population by looping over new population and create individuals from current population
   *
   * @param population
   * @param cityList
   * @return
   * @since 1.0.0-SNAPSHOT
   */
  public Population evolvePopulation(final Population population, final List<City> cityList) {
    final Population newPopulation = new Population(population.getPopulatedProblemList().size(), false, cityList);

    // keep our fittest individual if isFittest is enabled
    final int offset;
    if (isFittest) {
      newPopulation.getPopulatedProblemList().set(0, fitnessService.getFittest(population.getPopulatedProblemList()));
      offset = 1;
    } else {
      offset = 0;
    }

    this.createNewPopulation(offset, population, newPopulation, cityList);

    /**
     * mutate each element of the population and add _new_ genetic material
     */
    newPopulation.getPopulatedProblemList().forEach(it -> mutate(it));
    return newPopulation;
  }

  private void createNewPopulation(final int offset, final Population population, final Population newPopulation, final List<City> cityList) {
    for (int i = offset; i < newPopulation.getPopulatedProblemList().size(); i++) {
      // Select parents, by getting the fittest of a population
      final Tour father = tournamentSelection(population, cityList);
      final Tour mother = tournamentSelection(population, cityList);

      // Crossover parents
      final Tour child = this.crossover(father, mother, cityList);
      // Add child to new population
      newPopulation.getPopulatedProblemList().set(i, child);
    }
  }

  /**
   * select fittest candidates for crossover
   * <p>
   * create a tournament population
   * get a random candidate problem and add it to tournament for each problem in tournament-size
   * and return the fittest tour
   *
   * @param population
   * @param cityList
   * @return
   * @since 1.0.0-SNAPSHOT
   */
  private Tour tournamentSelection(final Population population, final List<City> cityList) {
    final Population tournament = new Population(tournamentSize, false, cityList);
    for (int i = 0; i < tournamentSize; i++) {
      final int randomId = (int) (Math.random() * population.getPopulatedProblemList().size());
      tournament.getPopulatedProblemList().set(i, population.getPopulatedProblemList().get(randomId));
    }
    return fitnessService.getFittest(tournament.getPopulatedProblemList());
  }

  /**
   * Applies crossover to a set of parents and creates offspring
   *
   * @param father
   * @param mother
   * @param cityList
   * @return
   * @since 1.0.0-SNAPSHOT
   */
  private Tour crossover(final Tour father, final Tour mother, final List<City> cityList) {
    /**
     * initialize child with same cityList size as parents, even though it would be empty!
     */
    final Tour child = new Tour(cityList.size());

    /**
     * Get start and end sub tour positions for parent1's tour,
     * by get number of cities multiplied with a random number
     */
    final int start = (int) (Math.random() * father.getCityList().size());
    final int end = (int) (Math.random() * mother.getCityList().size());

    /**
     * apply father to child
     */
    for (int i = 0; i < child.getCityList().size(); i++) {
      if (start < end && (i > start && i < end)) {
        child.setCity(i, father.getCityList().get(i));
      } else if (start > end && !(i < start && i > end)) {
        child.setCity(i, father.getCityList().get(i));
      }
    }

    /**
     * if child do not contain
     */
    for (int i = 0; i < mother.getCityList().size(); i++) {
      if (!child.getCityList().contains(mother.getCityList().get(i))) {
        for (int j = 0; j < child.getCityList().size(); j++) {
          if (child.getCityList().get(j) == null) {
            child.setCity(j, mother.getCityList().get(i));
            break;
          }
        }
      }
    }
    return child;
  }

  /**
   * mutates our problem
   * <p>
   * randomly swap problems
   *
   * @param problem
   * @since 1.0.0-SNAPSHOT
   */
  private void mutate(final Tour problem) {
    /**
     * avoiding forEach
     * since object-ref can cause npe / index-out-of-bounds
     */
    for (int tourPos1 = 0; tourPos1 < problem.getCityList().size(); tourPos1++) {
      if (Math.random() < mutationRate) {
        final City city1 = problem.getCityList().get(tourPos1);

        final int tourPos2 = (int) (problem.getCityList().size() * Math.random());
        final City city2 = problem.getCityList().get(tourPos2);

        // _mutate_ / swap cities
        problem.setCity(tourPos2, city1);
        problem.setCity(tourPos1, city2);
      }
    }
  }
}
