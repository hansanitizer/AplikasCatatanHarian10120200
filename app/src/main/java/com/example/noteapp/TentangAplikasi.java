package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.WindowManager;

//10120200 - Mochamad Farhan Fadilah Ansori - IF5
public class TentangAplikasi extends AppCompatActivity {

    ViewPager viewPager;
    TentangAplikasiAdapter tentangAplikasiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang_aplikasi);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        viewPager = findViewById(R.id.vp_tentangApk);
        tentangAplikasiAdapter = new TentangAplikasiAdapter(this);
        viewPager.setAdapter(tentangAplikasiAdapter);
    }
}