package com.example.noblenotebooklouis.challenge2;

/**
 * Created by Noble Notebook Louis on 13-Sep-17.
 */

public class CubePosition implements Cube {

    private int x1, x2, y1, y2;

    /**
     * CubePosition class that draws the intersecting CubeBeacons.
     * @param x1 The corners of the cube
     * @param x2
     * @param y1
     * @param y2
     */

    public CubePosition(int x1, int x2, int y1, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;

    }

    @Override
    public int getWidth() {
        return x2 - x1;
    }

    @Override
    public int getHeight() {
        return y2 - y1;
    }

    /**
     * Determines the middle of the cube. An approximate location where the
     * user stands.
     * @return
     */
    public Position getPosition() {
        return new Position(x1 + ((x2 - x1)/2), y1 + ((y2 - y1)/ 2) );
    }
}
