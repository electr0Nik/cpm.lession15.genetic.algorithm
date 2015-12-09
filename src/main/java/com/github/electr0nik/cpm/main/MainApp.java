package com.github.electr0nik.cpm.main;


import com.github.electr0nik.cpm.common.algorithm.GeneticAlgorithm;
import com.github.electr0nik.cpm.problem.travelingsalesman.algorithm.Population;
import com.github.electr0nik.cpm.problem.travelingsalesman.model.City;
import com.github.electr0nik.cpm.problem.travelingsalesman.service.FitnessService;
import com.github.electr0nik.cpm.problem.travelingsalesman.service.impl.FitnessServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class MainApp {
  private final FitnessService fitnessService;

  public MainApp() {
    this.fitnessService = new FitnessServiceImpl();
  }

  public static void main(String[] args) {
    final MainApp app = new MainApp();

    // generate random tour
    final List<City> cityList = new ArrayList<>();
    cityList.add(new City("a",  60L, 200L));
    cityList.add(new City("b", 180L, 200L));
    cityList.add(new City("c",  80L, 180L));
    cityList.add(new City("d", 140L, 180L));

    final GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(5, 0.015, true);

    // Initialize population
    final Population population = new Population(50, true, cityList);
    System.out.println("Initial: " + app.fitnessService.getFittest(population.getPopulatedProblemList()));

    // evolve population for 50 generations
    Population cripples = geneticAlgorithm.evolvePopulation(population, cityList);
    for (int i = 0; i < 10; i++) {
      cripples = geneticAlgorithm.evolvePopulation(cripples, cityList);
    }
    System.out.println("\nNot enough generations: " + app.fitnessService.getFittest(cripples.getPopulatedProblemList()));

    // Evolve population for 100 generations
    Population evolution = geneticAlgorithm.evolvePopulation(population, cityList);
    for (int i = 0; i < 100; i++) {
      evolution = geneticAlgorithm.evolvePopulation(evolution, cityList);
    }
    System.out.println("\nFinished: " + app.fitnessService.getFittest(evolution.getPopulatedProblemList()));

    // no significant advantage in more generations
    Population max = geneticAlgorithm.evolvePopulation(population, cityList);
    for (int i = 0; i < 1000; i++) {
      max = geneticAlgorithm.evolvePopulation(max, cityList);
    }
    System.out.println("\nMax iterations over population: " + app.fitnessService.getFittest(max.getPopulatedProblemList()));
  }
}
