package com.example.mainqlsv;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ActivityHome extends AppCompatActivity {
ImageView imgSv, imgInf, imgLogout;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        imgSv = (ImageView) findViewById(R.id.imgSinhVen);
        imgInf = (ImageView) findViewById(R.id.imgInformation);
        imgLogout = (ImageView) findViewById(R.id.imgLogout);

        imgSv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ActivityHome.this, ActivitySinhVien.class);
                startActivity(i);
            }
        });
        imgLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ActivityHome.this, ActivityIntro.class);
                startActivity(i);
            }
        });
        imgInf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ActivityHome.this, ActivityInformation.class);
                startActivity(i);
            }
        });
    }
}