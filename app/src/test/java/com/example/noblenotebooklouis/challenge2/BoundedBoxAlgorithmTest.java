package com.example.noblenotebooklouis.challenge2;


import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class BoundedBoxAlgorithmTest {


    @Test
    public void testBoundedBoxAlgorithm() throws Exception {
        Beacon b1 = new Beacon("1", new Position(20, 30));
        Beacon b2 = new Beacon("2", new Position(100, 50));
        Beacon b3 = new Beacon("3", new Position(50, 120));
        Beacon b4 = new Beacon("4", new Position(110, 120));

        Cube c1 = new Cube(b1);
        Cube c2 = new Cube(b2);
        Cube c3 = new Cube(b3);
        Cube c4 = new Cube(b4);

        List<Cube> cubes = new ArrayList<>();
        cubes.add(c1);
        cubes.add(c2);
        cubes.add(c3);
        cubes.add(c4);

        assertTrue(BoundedBoxAlgorithm.isCollision(c1, c2));
        assertTrue(BoundedBoxAlgorithm.isCollision(c1, c3));
        assertTrue(BoundedBoxAlgorithm.isCollision(c2, c3));

        RectanglePosition rp = BoundedBoxAlgorithm.drawCube(cubes);
        assertEquals(10, rp.getWidth());
        assertEquals(10, rp.getHeight());
        assertEquals(65, rp.getPosition().getX());

    }
}