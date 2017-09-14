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
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewGroup mainLayout;
    private ImageView image;

    private int xDelta;
    private int yDelta;

    private List<Beacon> beacons;

    private BluetoothAdapter adapter;
    private BluetoothLeScanner scanner;
    private ScanCallback callback;
    private AssetManager assetManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        beacons = null;
            // Creating Input Stream
   /*
    * File file = new File( filename); FileInputStream myInput = new
    * FileInputStream(file);
    */



        //  Don't forget to Change to your assets folder excel sheet
        try {
            InputStream myInput = assetManager.open("designlab.xlsx");

            beacons = new ArrayList<Beacon>();

            XSSFWorkbook workbook = (XSSFWorkbook) WorkbookFactory.create(myInput);
            //XSSFWorkbook wb = new XSSFWorkbook(pkg);
            XSSFSheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                Cell address = row.getCell(0);
                Cell x = row.getCell(7);
                Cell y = row.getCell(8);
                if (row.getRowNum() == 0) {
                    continue;
                }
                beacons.add(new Beacon(address.getStringCellValue(), new Position((int) x.getNumericCellValue(), (int) y.getNumericCellValue())));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }


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
            position = new BoundedBoxAlgorithm().getNodePosition(beacons);
        }

        image = (ImageView) findViewById(R.id.image);
        image.setImageResource(R.drawable.designlab);
        final Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap().copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(bitmap);
        if (position != null) {
            Paint paint = new Paint();
            paint.setColor(Color.GREEN);
            canvas.drawCircle(position.getX(), position.getY(), 10, paint);    // for circle dot
            //canvas.drawPoint(touchX, touchY, paint);  // for single point
        }
        image.setImageBitmap(bitmap);
        image.invalidate();
    }
}
