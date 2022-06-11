package com.fy.demo.activity;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aczk.acsqzc.animator.SwitchOnAnimView;
import com.fy.demo.R;
import com.utils.AppUtils;


public class TransparentActivity extends BaseActivity {
    public RelativeLayout rlXiaoMi;
    public RelativeLayout rlOther;
    public Handler mHandler;
    public SwitchOnAnimView switchOnAnimView;
    public SwitchOnAnimView clickOnAnimView;
    public ImageView mIcon;
    public TextView l;
    public TextView n;
    public AnimatorSet o;

    public TransparentActivity() {
        this.mHandler = new Handler();
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap
                .createBitmap(
                        drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight(),
                        drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                                : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        // canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static SwitchOnAnimView a(TransparentActivity transparentActivity) {
        return transparentActivity.switchOnAnimView;
    }

    public void a(ImageView arg5) {
        ObjectAnimator v5 = ObjectAnimator.ofFloat(arg5, "translationY", -20.0f, 20.0f, -20.0f);
        v5.setDuration(500L);
        v5.setRepeatCount(-1);
        v5.setRepeatMode(ValueAnimator.RESTART);
        v5.start();
        AnimatorSet v1 = new AnimatorSet();
        v1.playTogether(v5);
        v1.setStartDelay(10000L);
        v1.start();
    }



    public static String b() {
        return Build.BRAND.toLowerCase();
    }

    public void e() {
        RelativeLayout layout;
        b();
        this.rlOther.setVisibility(View.GONE);
        this.rlXiaoMi.setVisibility(View.GONE);
        String v0 = b();
        switch (v0) {
            case "oppo":
            case "vivo":
            case "meizu":
            case "samsung":
            case "smartisan":
            case "letv":
                break;
            case "xiaomi":
            case "redmi":
                this.a(((ImageView) this.findViewById(R.id.iv_jiantou)));
                layout = this.rlXiaoMi;
                layout.setVisibility(View.VISIBLE);
                return;
            case "huawei":
            case "honor":
                if (isHarmonyOs()) {
                    this.a(((ImageView) this.findViewById(R.id.iv_jiantou)));
                    Spanned v0_2 = Html.fromHtml("第一步：滑动列表，找到“<font color=#FD5439>已安装的服务</font>”");
                    this.l.setText(v0_2);
                    this.rlXiaoMi.setVisibility(View.VISIBLE);
                    this.n.setText("滑动列表找到“" + getAppName(this) + "”");
                    return;
                }
                break;
            default: {
                layout = this.rlOther;
                layout.setVisibility(0);
                return;
            }
        }
        this.rlOther.setVisibility(0);
        this.a(((ImageView) this.findViewById(R.id.iv_up)));
    }

    public static boolean isHarmonyOs() {
        try {
            Class<?> buildExClass = Class.forName("com.huawei.system.BuildEx");
            Object osBrand = buildExClass.getMethod("getOsBrand").invoke(buildExClass);
            return "harmony".equalsIgnoreCase(osBrand.toString());
        }catch (Throwable e){
            return false;
        }
    }

    public static String getAppName(Context context) {
        if (context == null) {
            return null;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            return String.valueOf(packageManager.getApplicationLabel(context.getApplicationInfo()));
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    private void f() {
        TextView v0 = (TextView) this.findViewById(R.id.tv_step);
        String appName = getAppName(this);
        v0.setText(Html.fromHtml("滑动列表找到“<font color=#FD5439>" + appName + "</font>”"));
        ((TextView) this.findViewById(R.id.tv_permission_name)).setText("滑动列表找到“" + appName + "”");
        this.n = (TextView) this.findViewById(R.id.tv_permission);
        ((TextView) this.findViewById(R.id.tv_step_two)).setText(Html.fromHtml("第二步：滑动列表，找到“<font color=#FD5439>" + appName + "</font>”"));
        this.l = (TextView) this.findViewById(R.id.tv_step_one);
        Spanned v0_1 = Html.fromHtml("第一步：滑动列表，打开“<font color=#FD5439>已下载的应用</font>”");
        this.l.setText(v0_1);
    }

    @Override
    public void onCreate(Bundle arg4) {
        super.onCreate(arg4);
        AppUtils appUtils = new AppUtils(this);
        this.setContentView(R.layout.activity_seeding_transparent);
        this.rlOther = (RelativeLayout) this.findViewById(R.id.rl_other);
        this.rlXiaoMi = (RelativeLayout) this.findViewById(R.id.rl_xiaomi);
        ImageView tvIcon = (ImageView) this.findViewById(R.id.tv_icon);
        this.mIcon = tvIcon;
        tvIcon.setImageBitmap(drawableToBitmap(appUtils.getAppIconByPkgName()));
        this.f();
        this.e();
        this.findViewById(R.id.container).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        this.switchOnAnimView = (SwitchOnAnimView) this.findViewById(R.id.switch_on_anim_view);
        this.clickOnAnimView = (SwitchOnAnimView) this.findViewById(R.id.click_on_anim_view);
        this.mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    switchOnAnimView.startAnim();
                    clickOnAnimView.startAnim();
                } catch (Exception e) {
                    Log.d("TransparentActivity", e.getMessage());
                }

            }
        }, 500L);
        Log.e("liuqiang-->", Build.VERSION.RELEASE.toLowerCase());
    }

    @Override
    public void onDestroy() {
        AnimatorSet animatorSet = this.o;
        if (animatorSet != null) {
            animatorSet.cancel();
        }

        SwitchOnAnimView switchOnAnimView = this.switchOnAnimView;
        if (switchOnAnimView != null) {
            switchOnAnimView.stopAnim();
            this.switchOnAnimView = null;
        }

        SwitchOnAnimView clickOnAnimView1 = this.clickOnAnimView;
        if (clickOnAnimView1 != null) {
            clickOnAnimView1.stopAnim();
            this.clickOnAnimView = null;
        }

        super.onDestroy();
    }
}

