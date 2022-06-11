package com.utils;

import android.graphics.drawable.Drawable;

import android.content.Context;
import android.content.Intent;
import android.content.pm.*;

import java.util.ArrayList;
import java.util.List;

public class AppUtils {
    public Context mContext;

    public AppUtils(Context context){
        this.mContext =  context;
    }

    public boolean isSystemPackage(ResolveInfo resolveInfo){
        return ((resolveInfo.activityInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0);
    }

    public Drawable getAppIconByPkgName(){
        Drawable drawable = null;
        try{
            drawable = mContext.getPackageManager().getApplicationIcon(mContext.getPackageName());
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }
        return drawable;
    }

    public String getAppName(){
        String appName = "";
        ApplicationInfo applicationInfo;
        PackageManager packageManager = mContext.getPackageManager();
        try{
            applicationInfo = packageManager.getApplicationInfo(mContext.getPackageName(), 0);
            if (applicationInfo != null){
                appName = (String)packageManager.getApplicationLabel(applicationInfo);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appName;
    }

    public String getAppVersionName(){
        String versionName = "";
        try{
            versionName = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionName;
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }
        return versionName;
    }
}