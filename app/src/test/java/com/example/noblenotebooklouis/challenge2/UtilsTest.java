package com.example.noblenotebooklouis.challenge2;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
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
        try {
            List<Beacon> beacons = Utils.getExcelBeacons();
            beacons.get(0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }
}
