package com.example.noblenotebooklouis.challenge2;

import android.content.res.AssetManager;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Noble Notebook Louis on 13-Sep-17.
 * Static utilities to calculate or extract data.
 */


public class Utils {

    /**
     * Calculate estimate distance through RSSI.
     * @param rssi RSSI
     * @param txPower RSSI power at 1m from the iBeacon
     * @return
     */
    public static double calculateDistance(int rssi, double txPower) {
        return Math.pow(10d, (txPower - (double) rssi) / (10 * 2));
    }

    /**
     *  Uses Apache POI to extract data from the designlab.xlsx Excel file.
     *  Deprecated.
     * @return A list of beacons with address and pixel (x,y)-coordinates.
     * @throws IOException
     * @throws InvalidFormatException
     */
    public static List<Beacon> getExcelBeacons() throws IOException, InvalidFormatException {
        List<Beacon> result = new ArrayList<Beacon>();
//        OPCPackage pkg = OPCPackage.open(new File("C:/Users/Louis/AndroidStudioProjects/Challenge2/app/src/main/assets/designlab.xlsx"));
        XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(new File("C:/Users/Louis/AndroidStudioProjects/Challenge2/app/src/main/assets/designlab.xlsx")));
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


    /**
     *  Compare the beacons with the existing list of beacons in the Excel file.
     *  Compare the unique address with the beacon in the file and return the list.
     *  Deprecated.
     * @param beacons       The list of beacons from scanning with the Bluetooth LE.
     * @param excelBeacons  The existing list in the Excel file.
     * @return
     */
    public static List<Beacon> getActiveBeacons(List<Beacon> beacons, List<Beacon> excelBeacons) {
        List<Beacon> result = new ArrayList<Beacon>();
        for (Beacon b : beacons) {
            for (Beacon eB : excelBeacons) {
                if (b.getAddress().equals(eB.getAddress())) {
                    result.add(b);
                }
            }
        }
        return result;
    }

    /**
     *
     * @return Returns the FOUR nearby beacons with the highest RSSI
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

        return beacons.subList(0, 3);
    }


}
