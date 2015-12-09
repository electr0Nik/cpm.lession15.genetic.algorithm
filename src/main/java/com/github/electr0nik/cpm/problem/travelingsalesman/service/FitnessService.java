package com.github.electr0nik.cpm.problem.travelingsalesman.service;


import com.github.electr0nik.cpm.problem.travelingsalesman.model.Tour;

import java.util.List;

public interface FitnessService {
  Tour getFittest(final List<Tour> populatedTourList);
}
