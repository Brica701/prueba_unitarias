package org.iesvdm.tddjava.ship;

import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

@Test
public class LocationSpec {

    private final int x = 12;
    private final int y = 32;
    private final Direction direction = Direction.NORTH;
    private Point max;
    private Location location;
    private List<Point> obstacles;

    @BeforeMethod
    public void beforeTest() {
        max = new Point(50, 50);
        location = new Location(new Point(x, y), direction);
        obstacles = new ArrayList<Point>();
    }

    public void whenInstantiatedThenXIsStored() {
        assertEquals(direction, location.getDirection());
    }

    public void whenInstantiatedThenYIsStored() {

    }

    public void whenInstantiatedThenDirectionIsStored() {

    }

    public void givenDirectionNWhenForwardThenYDecreases() {

        location.setDirection(Direction.NORTH);
        location.forward(max, obstacles);
        assertEquals(y - 1, location.getY());
    }

    public void givenDirectionSWhenForwardThenYIncreases() {

    }

    public void givenDirectionEWhenForwardThenXIncreases() {

    }

    public void givenDirectionWWhenForwardThenXDecreases() {

    }

    public void givenDirectionNWhenBackwardThenYIncreases() {

    }

    public void givenDirectionSWhenBackwardThenYDecreases() {

    }

    public void givenDirectionEWhenBackwardThenXDecreases() {

    }

    public void givenDirectionWWhenBackwardThenXIncreases() {

    }

    public void whenTurnLeftThenDirectionIsSet() {

    }

    public void whenTurnRightThenDirectionIsSet() {

    }

    public void givenSameObjectsWhenEqualsThenTrue() {

    }

    public void givenDifferentObjectWhenEqualsThenFalse() {

    }

    public void givenDifferentXWhenEqualsThenFalse() {

    }

    public void givenDifferentYWhenEqualsThenFalse() {

    }

    public void givenDifferentDirectionWhenEqualsThenFalse() {

    }

    public void givenSameXYDirectionWhenEqualsThenTrue() {

    }

    public void whenCopyThenDifferentObject() {
        }

    public void whenCopyThenEquals() {

    }

    public void givenDirectionEAndXEqualsMaxXWhenForwardThen1() {

    }

    public void givenDirectionWAndXEquals1WhenForwardThenMaxX() {
    }

    public void givenDirectionNAndYEquals1WhenForwardThenMaxY() {
    }

    public void givenDirectionSAndYEqualsMaxYWhenForwardThen1() {

    }

    public void givenObstacleWhenForwardThenReturnFalse() {


    }

    public void givenObstacleWhenBackwardThenReturnFalse() {


    }

}
