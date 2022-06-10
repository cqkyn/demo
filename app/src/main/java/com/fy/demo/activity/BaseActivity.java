package com.fy.demo.activity;

import static android.content.Intent.FLAG_ACTIVITY_NO_HISTORY;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class BaseActivity extends AppCompatActivity {
    public static final String TAG = "BaseActivity";
    public Toast a;
    public long b;
    public AppCompatActivity c;
    public Handler mHandler;
    public Runnable mRunnable;

    public BaseActivity() {
        this.b = 0L;
        this.mHandler = new Handler();
    }

    public static boolean fixOrientation(Activity activity) {
        try {
            Field field = Activity.class.getDeclaredField("mActivityInfo");
            field.setAccessible(true);
            ActivityInfo o = (ActivityInfo)field.get(activity);
            o.screenOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED;
            field.setAccessible(false);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void a(AppCompatActivity arg1) {
        this.c = arg1;
    }

    public void a(boolean arg2) {
        int v0;
        View v2;
        if(arg2) {
            v2 = this.getWindow().getDecorView();
            v0 = 0x400;
        }
        else {
            v2 = this.getWindow().getDecorView();
            v0 = 0x2400;
        }

        v2.setSystemUiVisibility(v0);
    }

    public static boolean isTranslucentOrFloating(Activity arg7) {
        try {
            TypedArray ta = arg7.obtainStyledAttributes(((int[])Class.forName("com.android.internal.R$styleable").getField("Window").get(null)));
            Method m = ActivityInfo.class.getMethod("isTranslucentOrFloating", TypedArray.class);
            m.setAccessible(true);
            boolean isTranslucentOrFloating = ((Boolean)m.invoke(null, ta)).booleanValue();
            m.setAccessible(false);
            return isTranslucentOrFloating;
        }
        catch(Exception v7) {
            v7.printStackTrace();
            return false;
        }
    }


    @Override
    public void onBackPressed() {
        if(this.c != null) {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.setFlags(FLAG_ACTIVITY_NO_HISTORY);
            intent.addCategory("android.intent.category.HOME");
            this.startActivity(intent);
        }else{
            finish();
        }
    }

    @Override
    public void onCreate(Bundle arg3) {
        if(Build.VERSION.SDK_INT == 26 && (BaseActivity.isTranslucentOrFloating(this))) {
            BaseActivity.fixOrientation(this);
        }
        super.onCreate(arg3);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public void onDestroy() {
        Toast toast = this.a;
        if(toast != null) {
            toast.cancel();
            this.a = null;
        }
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        Toast toast = this.a;
        if(toast != null) {
            toast.cancel();
            this.a = null;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.mHandler.postDelayed(this.mRunnable, 1000L);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void setRequestedOrientation(int arg3) {
        if(Build.VERSION.SDK_INT == 26 && (BaseActivity.isTranslucentOrFloating(this))) {
            return;
        }
        super.setRequestedOrientation(arg3);
    }
}

