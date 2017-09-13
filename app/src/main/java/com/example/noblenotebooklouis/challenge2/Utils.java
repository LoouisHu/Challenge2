package com.example.noblenotebooklouis.challenge2;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Noble Notebook Louis on 13-Sep-17.
 */

//TODO also need to implement txPower, calibration constants

public class Utils {

    public static double calculateDistance(int rssi, double txPower) {
        return Math.pow(10d, (txPower - (double) rssi) / (10 * 2));
    }


    public static List<Beacon> getExcelBeacons() throws IOException, InvalidFormatException {
        List<Beacon> result = new ArrayList<Beacon>();

        OPCPackage pkg = OPCPackage.open(new File("designlab.xlsx"));
        XSSFWorkbook wb = new XSSFWorkbook(pkg);
        XSSFSheet sheet = wb.getSheetAt(0);

        for (Row row : sheet) {
            Cell address = row.getCell(0);
            Cell x = row.getCell(7);
            Cell y = row.getCell(8);
            if (row.getRowNum() == 0) {
                continue;
            }
            result.add(new Beacon(address.getStringCellValue(), new Position((int) x.getNumericCellValue(), (int) y.getNumericCellValue())));
        }

        return result;

    }

    public static List<Beacon> getActiveBeacons(List<Beacon> beacons) {
        List<Beacon> result = new ArrayList<Beacon>();
        for (Beacon b : beacons) {
            if (b.getRssi() != 0) {
                result.add(b);
            }
        }
        return result;
    }

    /**
     *
     * @return Returns the three nearby beacons with the highest RSSI
     */

    public static List<Beacon> getNearbyBeacons(List<Beacon> beacons) {

        Collections.max(beacons, new Comparator<Beacon>() {
            @Override
            public int compare(Beacon beacon, Beacon t1) {
                if (beacon.getRssi() > t1.getRssi()) {
                    return -1;
                }
                if (beacon.getRssi() == t1.getRssi()) {
                    return 0;
                }
                return 1;
            }
        });

        return beacons.subList(0, 2);
    }


}
