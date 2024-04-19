package org.iesvdm.tddjava.ship;

import org.testng.Assert;
import org.testng.annotations.*;
import static org.testng.Assert.*;

@Test
public class PointSpec {

    private Point point;
    private final int x = 12;
    private final int y = 21;

    @BeforeMethod
    public void beforeTest() {
        point = new Point(x, y);
    }

    public void whenInstantiatedThenXIsSet() {
        Assert.assertEquals(point.getX(), x, "El valor de x no se estableció correctamente en la instancia de Point.");
    }

    public void whenInstantiatedThenYIsSet() {
        Assert.assertEquals(point.getY(), y, "El valor de y no se estableció correctamente en la instancia de Point.");
    }

}
