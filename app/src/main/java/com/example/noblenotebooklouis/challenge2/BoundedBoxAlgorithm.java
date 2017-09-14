package com.example.noblenotebooklouis.challenge2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by Noble Notebook Louis on 13-Sep-17.
 * Bounded Box Algorithm.
 *
 * From three nearby active anchors, draw a cube around them and look for intersections.
 * If found, draw new rectangle, get the center of the new rectangle.
 * The center of the rectangle is the supposed position of the user.
 */

public class BoundedBoxAlgorithm {


    /**
     * Get the final position of the running application.
     * @param beacons A list of all the active beacons with an updated
     *                RSSI.
     * @return
     */
    public static Position getNodePosition(List<Beacon> beacons) {
        List<Beacon> nearbyBeacons = Utils.getNearbyBeacons(beacons);

        Cube cb1 = new Cube(nearbyBeacons.get(0));
        Cube cb2 = new Cube(nearbyBeacons.get(1));
        Cube cb3 = new Cube(nearbyBeacons.get(2));

        return drawCube(cb1, cb2, cb3).getPosition();
    }


    /**
     * Checks whether two cubes have collision.
     * @return True if the cubes intersect, false otherwise.
     * @param c1 c2 Two given 2-D cubes
     */
    public static boolean isCollision(Cube c1, Cube c2) {
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
    public static RectanglePosition drawCube(Cube c1, Cube c2, Cube c3) {
        if (isCollision(c1, c2) && isCollision(c2, c3) && isCollision(c1, c3)) {
            int x1, x2, y1, y2;
            List<Integer> xs = new ArrayList<Integer>();
            xs.add(c1.getBeacon().getPos().getX());
            xs.add(c2.getBeacon().getPos().getX());
            xs.add(c3.getBeacon().getPos().getX());

            List<Integer> ys = new ArrayList<Integer>();
            ys.add(c1.getBeacon().getPos().getY());
            ys.add(c2.getBeacon().getPos().getY());
            ys.add(c3.getBeacon().getPos().getY());

            y1 = Collections.max(ys) - c1.getRadius();
            y2 = Collections.min(ys) + c1.getRadius();
            x1 = Collections.max(xs) - c1.getRadius();
            x2 = Collections.min(xs) + c1.getRadius();

            return new RectanglePosition(x1, x2, y1, y2);

        }

        return null;
    }


}
