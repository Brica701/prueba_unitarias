package org.iesvdm.tddjava.ship;

import org.testng.annotations.*;
import static org.testng.Assert.*;

@Test
public class DirectionSpec {

    public void whenGetFromShortNameNThenReturnDirectionN() {
        assertEquals(Direction.NORTH, Direction.getFromShortName('N'));
    }

    public void whenGetFromShortNameWThenReturnDirectionW() {
        assertEquals(Direction.WEST, Direction.getFromShortName('W'));
    }

    public void whenGetFromShortNameBThenReturnNone() {
        assertEquals(Direction.NONE, Direction.getFromShortName('B'));
    }

    public void givenSWhenLeftThenE() {
        assertEquals(Direction.EAST, Direction.SOUTH.turnLeft());
    }

    public void givenNWhenLeftThenW() {
        assertEquals(Direction.WEST, Direction.NORTH.turnLeft());
    }

    public void givenSWhenRightThenW() {
        assertEquals(Direction.WEST, Direction.SOUTH.turnRight());
    }

    public void givenWWhenRightThenN() {
        assertEquals(Direction.NORTH, Direction.WEST.turnRight());
    }

}
