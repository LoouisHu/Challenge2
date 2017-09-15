package com.example.noblenotebooklouis.challenge2;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;
import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {

    private ViewGroup mainLayout;
    private ImageView image;
    private Button button;
    private boolean running;

    private List<Beacon> beacons;
    private Database database;
    private BluetoothAdapter mBluetoothAdapter;
    private boolean scanning;
    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new Database();
        beacons = database.getAnchors();
        running = false;

        final BluetoothManager bluetoothManager = (BluetoothManager)  getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();

        setImage();

        if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 99);
        }
        Log.d("SCAN", "starting scan...");
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildMessageBox();

        }

        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!running) {
                    running = true;
                    button.setText("stop");

                    mBluetoothAdapter.getBluetoothLeScanner().startScan(new ScanCallback() {
                        @Override
                        public void onScanResult(int callbackType, ScanResult result) {
                            final int rssi = result.getRssi();
                            final String address = result.getDevice().getAddress();
                            for (Beacon b : beacons) {
                                if (b.getAddress().equals(address)) {
                                    b.setRssi(rssi);
                                    Log.d("SCAN RESULT", "rssi: " + rssi + ", address: " + address);
                                }
                            }

                        }

                        @Override
                        public void onBatchScanResults(List<ScanResult> results) {
                            super.onBatchScanResults(results);
                        }

                        @Override
                        public void onScanFailed(int errorCode) {
                            super.onScanFailed(errorCode);
                        }
                    });
                } else {
                    running = false;
                    button.setText("start");

                    mBluetoothAdapter.getBluetoothLeScanner().stopScan(new ScanCallback() {
                        @Override
                        public void onScanResult(int callbackType, ScanResult result) {
                            super.onScanResult(callbackType, result);
                        }

                        @Override
                        public void onBatchScanResults(List<ScanResult> results) {
                            super.onBatchScanResults(results);
                        }

                        @Override
                        public void onScanFailed(int errorCode) {
                            super.onScanFailed(errorCode);
                        }
                    });
                }
            }

        });
    }

    private void setImage() {
        image = (ImageView) findViewById(R.id.image);
        image.setImageResource(R.drawable.designlab);
        final Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap().copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(bitmap);
        
        if (running) {
            Position pos = new Position(BoundedBoxAlgorithm.getNodePosition(beacons).getX(), BoundedBoxAlgorithm.getNodePosition(beacons).getY());
            Paint paint = new Paint();
            paint.setColor(Color.GREEN);
            canvas.drawCircle(pos.getX() * 4, pos.getY() * 4, 10, paint);
        }

        for (Beacon b : database.getAnchors()) {
            Paint paint = new Paint();
            paint.setColor(Color.BLUE);
            canvas.drawCircle(b.getPos().getX() * 4, b.getPos().getY() * 4, 10, paint); //multiply by 3 so it fits the png.
        }
        image.setImageBitmap(bitmap);
        image.invalidate();
    }

    private void buildMessageBox() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }
}
