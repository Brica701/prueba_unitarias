package org.iesvdm.tddjava.ship;

import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

@Test
public class ShipSpec {

    private Ship ship;
    private Location location;
    private Planet planet;

    @BeforeMethod
    public void beforeTest() {
        Point max = new Point(50, 50);
        location = new Location(new Point(21, 13), Direction.NORTH);
        List<Point> obstacles = new ArrayList<>();
        obstacles.add(new Point(44, 44));
        obstacles.add(new Point(45, 46));
        planet = new Planet(max, obstacles);
//        ship = new Ship(location);
        ship = new Ship(location, planet);
    }

    public void whenInstantiatedThenLocationIsSet() {
//        Location location = new Location(new Point(21, 13), Direction.NORTH);
//        Ship ship = new Ship(location);
        Assert.assertEquals(ship.getLocation(), location, "La ubicación no se estableció correctamente en la instancia de Ship.");
    }

//    public void givenNorthWhenMoveForwardThenYDecreases() {
//        ship.moveForward();
//        assertEquals(ship.getLocation().getPoint().getY(), 12);
//    }
//
//    public void givenEastWhenMoveForwardThenXIncreases() {
//        ship.getLocation().setDirection(Direction.EAST);
//        ship.moveForward();
//        assertEquals(ship.getLocation().getPoint().getX(), 22);
//    }

    public void whenMoveForwardThenForward() {
        Assert.assertTrue(ship.moveForward(), "El movimiento hacia adelante no se realizó correctamente.");
    }

    public void whenMoveBackwardThenBackward() {
        Assert.assertTrue(ship.moveBackward(), "El movimiento hacia atrás no se realizó correctamente.");
    }

    public void whenTurnLeftThenLeft() {

        ship.turnLeft();
        Assert.assertEquals(ship.getLocation().getDirection(), Direction.WEST, "El giro a la izquierda no se realizó correctamente.");
    }

    public void whenTurnRightThenRight() {

        ship.turnRight();
        Assert.assertEquals(ship.getLocation().getDirection(), Direction.EAST, "El giro a la derecha no se realizó correctamente.");
    }

    public void whenReceiveCommandsFThenForward() {
        String commands = "f";
        Assert.assertEquals(ship.receiveCommands(commands), "O", "El comando 'f' no se ejecutó correctamente.");
    }

    public void whenReceiveCommandsBThenBackward() {
        String commands = "b";
        Assert.assertEquals(ship.receiveCommands(commands), "O", "El comando 'b' no se ejecutó correctamente.");
    }

    public void whenReceiveCommandsLThenTurnLeft() {
        String commands = "l";
        Assert.assertEquals(ship.receiveCommands(commands), "O", "El comando 'l' no se ejecutó correctamente.");
    }

    public void whenReceiveCommandsRThenTurnRight() {
        String commands = "r";
        Assert.assertEquals(ship.receiveCommands(commands), "O", "El comando 'r' no se ejecutó correctamente.");
    }

    public void whenReceiveCommandsThenAllAreExecuted() {
        String commands = "flbr";
        Assert.assertEquals(ship.receiveCommands(commands), "OOOO", "No todos los comandos se ejecutaron correctamente.");
    }

    public void whenInstantiatedThenPlanetIsStored() {
//        Point max = new Point(50, 50);
//        Planet planet = new Planet(max);
//        ship = new Ship(location, planet);

    }

    public void givenDirectionEAndXEqualsMaxXWhenReceiveCommandsFThenWrap() {

        ship.getLocation().setDirection(Direction.EAST);
        ship.getLocation().getPoint().setX(50);
        ship.moveForward();
        Assert.assertEquals(ship.getLocation().getPoint().getX(), 0, "El envoltorio no se realizó correctamente cuando X es igual a maxX y la dirección es E.");
    }

    public void givenDirectionEAndXEquals1WhenReceiveCommandsBThenWrap() {

        ship.getLocation().setDirection(Direction.EAST);
        ship.getLocation().getPoint().setX(1);
        ship.moveBackward();
        Assert.assertEquals(ship.getLocation().getPoint().getX(), 50, "El envoltorio no se realizó correctamente cuando X es igual a 1 y la dirección es E.");
    }

    public void whenReceiveCommandsThenStopOnObstacle() {

        String commands = "f";
        ship.getLocation().getPoint().setX(43);
        ship.getLocation().getPoint().setY(44);
        ship.getLocation().setDirection(Direction.EAST);
        Assert.assertEquals(ship.receiveCommands(commands), "X", "El barco no se detuvo correctamente en el obstáculo.");
    }

    public void whenReceiveCommandsThenOForOkAndXForObstacle() {
        String commands = "f";
        Assert.assertEquals(ship.receiveCommands(commands), "O", "El comando 'f' no devolvió 'O' para OK.");
        ship.getLocation().getPoint().setX(43);
        ship.getLocation().getPoint().setY(44);
        ship.getLocation().setDirection(Direction.EAST);
        Assert.assertEquals(ship.receiveCommands(commands), "X", "El comando 'f' no devolvió 'X' para un obstáculo.");
    }
    }

