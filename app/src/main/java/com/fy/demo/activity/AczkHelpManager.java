package com.fy.demo.activity;


import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import androidx.fragment.app.Fragment;
import com.aczk.acsqzc.fragment.OrderFragment;
import com.aczk.acsqzc.fragment.SeedingFragment;

public class AczkHelpManager {
    public static class InitSdk {
        public Application a;

        public static Application a(InitSdk arg0) {
            return arg0.a;
        }

        public void build() {
            new AczkHelpManager(this, null);
            t.c(AczkHelpManager.mContext);
            Application v0 = AczkHelpManager.mContext;
            if((v0 instanceof Application)) {
                q.a(v0);
                u.a().a("independent_app", AczkHelpManager.mIsSdk);
                return;
            }

            throw new Error("shophelp SDK init activity manager throw a Error");
        }

        public InitSdk isApp(boolean arg1) {
            AczkHelpManager.mIsSdk = arg1;
            return this;
        }

        public InitSdk setContext(Application arg1) {
            this.a = arg1;
            return this;
        }

        public InitSdk setPrintLogUtil(boolean arg1) {
            AczkHelpManager.mIsDebug = arg1;
            return this;
        }
    }

    public static Application mContext;
    public static boolean mIsDebug;
    public static boolean mIsSdk;

    public AczkHelpManager(InitSdk arg1) {
        AczkHelpManager.mContext = InitSdk.a(arg1);
    }

    public AczkHelpManager(InitSdk arg1, com.aczk.acsqzc.activity.AczkHelpManager.1 arg2) {
        this(arg1);
    }

    public static void addStatistics(Context arg1, String arg2) {
        z.a().a(arg1, arg2);
    }

    public static boolean getAccessiblityOnOrOff() {
        return f.c().b();
    }

    public static Fragment getOrderFragment() {
        return OrderFragment.newInstance();
    }

    public static boolean getRedPackageOnOrOff() {
        return f.c().d();
    }

    public static Fragment getSeedingFragment() {
        return SeedingFragment.newInstance();
    }

    public static Fragment getSeedingSettingFragment() {
        return n.newInstance();
    }

    public static boolean isOPenAllPermission() {
        boolean v0;
        if(("huawei".equals(H.b())) || ("honor".equals(H.b())) || ("oppo".equals(H.b())) || ("vivo".equals(H.b())) || ("xiaomi".equals(H.b())) || ("redmi".equals(H.b())) || ("realme".equals(H.b())) || ("oneplus".equals(H.b())) || ("blackshark".equals(H.b()))) {
            v0 = (f.c().e()) && (f.c().a()) && (j.a().c());
        }
        else if((f.c().e()) && (f.c().a())) {
            v0 = true;
        }
        else {
            v0 = false;
        }

        if(!f.c().e()) {
            u.a().a("open_battery_white", false);
        }

        return v0;
    }

    public static boolean isOPenPermission() {
        return (f.c().e()) && (f.c().a());
    }

    public static void openSeedingPage(Activity arg2) {
        arg2.startActivity(new Intent(arg2, SeedingMainActivity.class));
    }

    public static void setAccessiblityOnOrOff(Activity arg1, Boolean arg2) {
        f.c().a(arg1, arg2.booleanValue());
    }

    public static void setRedPackageOnOrOff(Activity arg1, Boolean arg2) {
        f.c().b(arg1, arg2.booleanValue());
    }

    public static void startAccessibilityIntroduceActivity(Activity arg2) {
        arg2.startActivity(new Intent(arg2, AccessibiltyPermissionActivity.class));
    }

    public static void startAccessibilityIntroduceActivityForResult(Activity arg2, int arg3) {
        arg2.startActivityForResult(new Intent(arg2, AccessibiltyPermissionActivity.class), arg3);
    }

    public class com.aczk.acsqzc.activity.AczkHelpManager.1 {
    }

}

