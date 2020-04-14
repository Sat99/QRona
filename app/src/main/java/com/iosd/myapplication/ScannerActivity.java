package com.iosd.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;
import com.iosd.myapplication.qrgenearator.QRGContents;
import com.iosd.myapplication.qrgenearator.QRGEncoder;

import java.util.Scanner;


public class ScannerActivity extends AppCompatActivity {
    private CodeScanner mCodeScanner;
    private  CodeScannerView scannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
         scannerView = findViewById(R.id.scanner_view);
         mCodeScanner = new CodeScanner(this, scannerView);

        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                ScannerActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(ScannerActivity.this , PosttScanningActivity.class);
                        i.putExtra("result", result.getText());
                        startActivity(i);
                        Toast.makeText(ScannerActivity.this, result.getText(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
            }
        });
    }

  @Override
    protected void onResume() {
        super.onResume();

        mCodeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }

}
