package com.example.noblenotebooklouis.challenge2;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanFilter;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.UUID;

/**
 * Created by Thomas on 14-9-2017.
 */

public class FindBeacons {

    public static final void findBeacons(BluetoothDevice device, int rssi, List<Beacon> beacons) {
        String address = device.getAddress();
        for (Beacon beacon : beacons) {
            if (beacon.getAddress().equals(address)) {
                beacon.setRssi(rssi);
            }
        }

    }

}
