package com.example.noblenotebooklouis.challenge2;

/**
 * Created by Noble Notebook Louis on 13-Sep-17.
 */

public class Cube {

    private Beacon beacon;
    private int radius;

    public Cube(Beacon b) {
        this.beacon = b;
        radius = 500;
    }


    public int getRadius() {
        return radius;
    }
}
