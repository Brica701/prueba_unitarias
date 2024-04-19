package org.iesvdm.tddjava.ship;

import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

@Test
public class PlanetSpec {

    private Planet planet;
    private final Point max = new Point(50, 50);
    private List<Point> obstacles;

    @BeforeMethod
    public void beforeTest() {
        obstacles = new ArrayList<Point>();
        obstacles.add(new Point(12, 13));
        obstacles.add(new Point(16, 32));
        planet = new Planet(max, obstacles);
    }

    public void whenInstantiatedThenMaxIsSet() {
        Assert.assertEquals(planet.getMax(), max, "El punto máximo no se estableció correctamente en la instancia de Planet.");
    }

    public void whenInstantiatedThenObstaclesAreSet() {
        Assert.assertEquals(planet.getObstacles(), obstacles, "Los obstáculos no se establecieron correctamente en la instancia de Planet.");
    }

}
