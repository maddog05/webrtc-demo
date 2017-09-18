package com.maddog05.webrtc;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        findViewById(R.id.btn_parte_uno).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermission(1);
            }
        });
        findViewById(R.id.btn_parte_dos).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermission(2);
            }
        });
    }

    private void checkPermission(int example) {
        boolean isGranted = ContextCompat.checkSelfPermission(IntroActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
        boolean isMic = ContextCompat.checkSelfPermission(IntroActivity.this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED;
        if (isGranted && isMic) {
            if (example == 1)
                startActivity(new Intent(IntroActivity.this, MainActivity.class));
            else if (example == 2)
                startActivity(new Intent(IntroActivity.this, ParesActivity.class));
        } else {
            ActivityCompat.requestPermissions(IntroActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO}, 101);
        }
    }

}
