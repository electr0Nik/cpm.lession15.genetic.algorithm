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
    cityList.add(new City("b", 180L, 220L));
    cityList.add(new City("c",  80L, 130L));
    cityList.add(new City("d", 140L, 140L));
    cityList.add(new City("e", 260L, 250L));
    cityList.add(new City("f", 180L, 200L));
    cityList.add(new City("g", 380L, 160L));
    cityList.add(new City("h", 140L, 170L));
    cityList.add(new City("i",  30L, 280L));
    cityList.add(new City("j", 120L, 290L));
    cityList.add(new City("k",  50L,  80L));
    cityList.add(new City("l", 140L,  20L));
    cityList.add(new City("m",  10L,  60L));
    cityList.add(new City("n", 180L,  80L));
    cityList.add(new City("o",  30L, 380L));
    cityList.add(new City("p", 140L, 120L));
    cityList.add(new City("q", 340L, 280L));
    cityList.add(new City("r", 180L, 300L));
    cityList.add(new City("s", 280L,  60L));
    cityList.add(new City("t", 140L,  80L));
    cityList.add(new City("u",  80L, 240L));
    cityList.add(new City("v", 180L, 350L));
    cityList.add(new City("w", 120L,  80L));
    cityList.add(new City("x",  30L, 180L));

    final int tournamentSize = 5;
    final double mutationRate = .05;
    final boolean isInit = true;
    final GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(tournamentSize, mutationRate, isInit);

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
