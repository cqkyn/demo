package com.fy.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.fy.demo.activity.TransparentActivity;
import com.utils.AppUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppUtils appUtils = new AppUtils(this);
    }

    public void onOpenTransClick(View view){
        startActivityForResult(new Intent("android.settings.ACCESSIBILITY_SETTINGS"), 0);
        Intent intent = new Intent(MainActivity.this, TransparentActivity.class);
        intent.setFlags(0x10000000);
        this.startActivity(intent);
    }
}