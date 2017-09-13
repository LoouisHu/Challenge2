package com.example.noblenotebooklouis.challenge2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.noblenotebooklouis.challenge2.Utils.getActiveBeacons;

/**
 * Created by Noble Notebook Louis on 13-Sep-17.
 */

public class BoundedBoxAlgorithm {


    /**
     * Get the final position of the running application
     * @return
     */
    public Position getNodePosition(List<Beacon> beacons) {
        List<Beacon> activeBeacons = Utils.getActiveBeacons(beacons);
        List<Beacon> nearbyBeacons = Utils.getNearbyBeacons(activeBeacons);
        for (Beacon b : nearbyBeacons) {

        }

        return null;
    }


    /**
     * Checks whether two cubes have collision
     * @return True if the cubes intersect, false otherwise
     * @param c1 c2 Two given 2-D cubes
     */
    private boolean isCollission(CubeBeacon c1, CubeBeacon c2) {
        boolean result = false;

        if (c1.getTopLeft().getX() + c1.getWidth() >= c2.getTopLeft().getX()
                && c1.getTopLeft().getX() <= c2.getTopLeft().getX() + c2.getWidth()
                && c1.getTopLeft().getY() + c1.getHeight() >=  c2.getTopLeft().getY()
                && c1.getTopLeft().getY() <= c2.getTopLeft().getY() + c2.getHeight()) {
            result = true;
        }

        return result;
    }


    /**
     *  Draw cube, that is potentially the unknown node's position.
     *  @param c1, c2, c3 the three nearby beacons.
     *  @return
     */
    private CubePosition drawCube(CubeBeacon c1, CubeBeacon c2, CubeBeacon c3) {
        if (isCollission(c1, c2) && isCollission(c2, c3) && isCollission(c1, c3)) {
            int x1, x2, y1, y2;
            List<Integer> xs = new ArrayList<Integer>();
            xs.add(c1.getBeacon().getPos().getX());
            xs.add(c2.getBeacon().getPos().getX());
            xs.add(c2.getBeacon().getPos().getX());

            List<Integer> ys = new ArrayList<Integer>();
            ys.add(c1.getBeacon().getPos().getY());
            ys.add(c2.getBeacon().getPos().getY());
            ys.add(c3.getBeacon().getPos().getY());

            y1 = Collections.max(ys) - c1.getRadius();
            y2 = Collections.min(ys) + c1.getRadius();
            x1 = Collections.max(xs) - c1.getRadius();
            x2 = Collections.max(xs) + c1.getRadius();

            return new CubePosition(x1, x2, y1, y2);

        }

        return null;
    }


}
