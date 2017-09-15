package com.example.noblenotebooklouis.challenge2;

/**
 * Created by Noble Notebook Louis on 13-Sep-17.
 */

public class Cube implements Rectangle {

    private Beacon beacon;
    private int radius;

    /**
     * The rectangle around an iBeacon.
     * @param b
     */

    public Cube(Beacon b) {
        this.beacon = b;
        radius = 100;
    }

    public int getWidth() {
        return radius * 2;
    }

    public int getHeight() {
        return getWidth();
    }

    public int getRadius() {
        return radius;
    }


    /**
     * Returns the position of the top left corner of the cube.
     * @return
     */
    public Position getTopLeft() {
        return new Position((beacon.getPos().getX() - getRadius()), beacon.getPos().getY() + getRadius() );
    }

    public Beacon getBeacon() {
        return beacon;
    }
}
