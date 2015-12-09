package com.github.electr0nik.cpm.problem.travelingsalesman.service;

import com.github.electr0nik.cpm.problem.travelingsalesman.model.City;
import com.github.electr0nik.cpm.problem.travelingsalesman.service.impl.DistanceServiceImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class DistanceServiceTest {

  private DistanceService service;

  @Rule
  public ExpectedException thrown = ExpectedException.none();


  @Before
  public void setUp() {
    service = new DistanceServiceImpl();
  }

  @Test
  public void testGetPythagorasDistance() throws Exception {
    assertTrue(true);
  }

  @Test
  public void testExpectedIllegalStateExceptionForBothNull() {
    thrown.expect(IllegalStateException.class);
    thrown.expectMessage("Must not be null!");
    assertThat(service.getPythagorasDistance(null, null), null);
  }

  @Test
  public void testExpectedIllegalStateExceptionForCityNull() {
    thrown.expect(IllegalStateException.class);
    thrown.expectMessage("Must not be null!");
    assertThat(service.getPythagorasDistance(new City("a", 100l, 200l), null), null);
  }

  @Test
  public void testExpectedIllegalStateExceptionForCompareCityNull() {
    thrown.expect(IllegalStateException.class);
    thrown.expectMessage("Must not be null!");
    assertThat(service.getPythagorasDistance(null, new City("a", 100l, 200l)), null);
  }
}