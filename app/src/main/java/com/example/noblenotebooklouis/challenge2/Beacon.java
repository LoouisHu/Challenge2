package com.example.noblenotebooklouis.challenge2;

/**
 * Created by Noble Notebook Louis on 13-Sep-17.
 */

public class Beacon {

    private Position pos;
    private String address;
    private Integer rssi;

    public Beacon(String address, Position pos) {
        this.address = address;
        this.pos = pos;
    }

    public Position getPos() {
        return pos;
    }

    public String getAddress() {
        return address;
    }

    public void setRssi(Integer rssi) {
        this.rssi = rssi;
    }

    public int getRssi() {
        return rssi;
    }

}
