package com.example.noblenotebooklouis.challenge2;

/**
 * Created by Noble Notebook Louis on 13-Sep-17.
 */

public class Position {
    private int x, y;


    /**
     * Simple Position class to save the coordinates.
     * @param x
     * @param y
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
