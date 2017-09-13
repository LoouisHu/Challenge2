package com.example.noblenotebooklouis.challenge2;

import java.util.List;

/**
 * Created by Noble Notebook Louis on 13-Sep-17.
 */

//TODO also need to implement txPower, calibration constants

public class Utils {

    public static double calculateDistance(int rssi, double txPower) {
        return Math.pow(10d, (txPower - (double) rssi) / (10 * 2));
    }


    public static List<Beacon> getBeacons() {
        //TODO
        return null;
    }


}
