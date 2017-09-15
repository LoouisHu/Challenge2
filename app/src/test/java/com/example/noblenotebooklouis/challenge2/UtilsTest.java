package com.example.noblenotebooklouis.challenge2;

import org.junit.Test;


import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Noble Notebook Louis on 14-Sep-17.
 */

public class UtilsTest {

    @Test
    public void test() {
        List<Beacon> beacons = new Database().getAnchors();
        beacons.get(0);

    }
}
