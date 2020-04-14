package com.iosd.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.iosd.myapplication.qrgenearator.QRGContents;
import com.iosd.myapplication.qrgenearator.QRGEncoder;


public class PosttScanningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_scanning);

        Button button_sos = findViewById(R.id.button_sos);
        button_sos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  mCodeScanner.startPreview();

                Intent intent = new Intent(Intent.ACTION_DIAL);
                String temp = "tel:" + 100;
                intent.setData(Uri.parse(temp));

                startActivity(intent);
            }
        });

    }


}
