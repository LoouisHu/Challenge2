package com.example.noblenotebooklouis.challenge2;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewGroup mainLayout;
    private ImageView image;

    private int xDelta;
    private int yDelta;

    private List<Beacon> beacons;
    private Database database;

    private BluetoothAdapter adapter;
    private BluetoothLeScanner scanner;
    private ScanCallback callback;
    private AssetManager assetManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new Database();
        beacons = null;

        final BluetoothManager bluetoothManager = (BluetoothManager) getApplicationContext().getSystemService(Context.BLUETOOTH_SERVICE);
        adapter = bluetoothManager.getAdapter();
        callback = new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, ScanResult result) {
                if (result != null && result.getDevice() != null && result.getScanRecord() != null) {
                    FindBeacons.findBeacons(result.getDevice(), result.getRssi(), beacons);
                }
            }
            @Override
            public void onBatchScanResults(List< ScanResult > results) {
                if (results != null) {
                    for (ScanResult result: results) {
                        if (result != null && result.getDevice() != null && result.getScanRecord() != null) {
                            FindBeacons.findBeacons(result.getDevice(), result.getRssi(), beacons);
                        }
                    }
                }
            }
            @Override
            public void onScanFailed(int errorCode) {}
        };

        scanner = adapter.getBluetoothLeScanner();
        scanner.startScan(Collections. <ScanFilter> emptyList(), new ScanSettings.Builder().setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY).build(), callback);
        Position position = null;
        if (beacons != null) {
            position = BoundedBoxAlgorithm.getNodePosition(beacons);
        }

        image = (ImageView) findViewById(R.id.image);
        image.setImageResource(R.drawable.designlab);
        final Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap().copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(bitmap);
        if (position != null) {
            Paint paint = new Paint();
            paint.setColor(Color.GREEN);
            canvas.drawCircle(position.getX()*3, position.getY()*3, 10, paint);    // for circle dot
            //canvas.drawPoint(touchX, touchY, paint);  // for single point
        }

        for (Beacon b : database.getAnchors()) {
            Paint paint = new Paint();
            paint.setColor(Color.BLUE);
            canvas.drawCircle(b.getPos().getX()*3, b.getPos().getY()*3, 10, paint); //multiply by 3 so it fits the png.
        }
        image.setImageBitmap(bitmap);
        image.invalidate();
    }
}
