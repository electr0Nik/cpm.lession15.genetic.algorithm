package com.github.electr0nik.cpm.problem.travelingsalesman.service;

import com.github.electr0nik.cpm.problem.travelingsalesman.model.City;
import com.github.electr0nik.cpm.problem.travelingsalesman.model.Tour;

public interface DistanceService {
  Double getPythagorasDistance(final City city, final City cityToCompare);

  Long getTotalDistance(final Long totalTourDistance, final Tour problem);
}
