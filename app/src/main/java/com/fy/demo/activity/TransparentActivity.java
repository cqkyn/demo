package com.fy.demo.activity;


import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageManager;
import android.graphics.Bitmap.Config;
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
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.IntRange;

import com.aczk.acsqzc.animator.SwitchOnAnimView;
import com.fy.demo.R;


public class TransparentActivity extends BaseActivity {
    public RelativeLayout f;
    public RelativeLayout g;
    public Handler mHandler;
    public SwitchOnAnimView i;
    public SwitchOnAnimView j;
    public ImageView k;
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

    public static SwitchOnAnimView a(TransparentActivity arg0) {
        return arg0.i;
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

    public static Bitmap d() {
        PackageManager v1;
        try {
            v1 = AczkHelpManager.mContext.getPackageManager();
        } catch (PackageManager.NameNotFoundException unused_ex) {
            return TransparentActivity.drawableToBitmap(null.getApplicationIcon(null));
        }

        try {
            return TransparentActivity.drawableToBitmap(v1.getApplicationIcon(v1.getApplicationInfo(AczkHelpManager.mContext.getPackageName(), 0)));
        } catch (PackageManager.NameNotFoundException unused_ex) {
            return TransparentActivity.drawableToBitmap(v1.getApplicationIcon(null));
        }
    }

    public static String b() {
        return Build.BRAND.toLowerCase();
    }

    public void e() {
        RelativeLayout layout;
        b();
        this.g.setVisibility(View.GONE);
        this.f.setVisibility(View.GONE);
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
                layout = this.f;
                layout.setVisibility(View.VISIBLE);
                return;
            case "huawei":
            case "honor":
                if (H.t()) {
                    this.a(((ImageView) this.findViewById(R.id.iv_jiantou)));
                    Spanned v0_2 = Html.fromHtml("第一步：滑动列表，找到“<font color=#FD5439>已安装的服务</font>”");
                    this.l.setText(v0_2);
                    this.f.setVisibility(View.VISIBLE);
                    this.n.setText("滑动列表找到“" + a() + "”");
                    return;
                }
                break;
            default: {
                layout = this.g;
                layout.setVisibility(0);
                return;
            }
        }


        this.g.setVisibility(0);
        this.a(((ImageView) this.findViewById(id.iv_up)));
    }

    public String a() {
        try {
            int v0_1 = AczkHelpManager.mContext.getPackageManager().getPackageInfo(AczkHelpManager.mContext.getPackageName(), 0).applicationInfo.labelRes;
            return AczkHelpManager.mContext.getResources().getString(v0_1);
        }
        catch(PackageManager.NameNotFoundException v0) {
            v0.printStackTrace();
            return null;
        }
    }

    private void f() {
        TextView v0 = (TextView) this.findViewById(R.id.tv_step);
        String v1 = k.a;
        v0.setText(Html.fromHtml("滑动列表找到“<font color=#FD5439>" + v1 + "</font>”"));
        ((TextView) this.findViewById(R.id.tv_permission_name)).setText("滑动列表找到“" + v1 + "”");
        this.n = (TextView) this.findViewById(R.id.tv_permission);
        ((TextView) this.findViewById(R.id.tv_step_two)).setText(Html.fromHtml("第二步：滑动列表，找到“<font color=#FD5439>" + v1 + "</font>”"));
        this.l = (TextView) this.findViewById(R.id.tv_step_one);
        Spanned v0_1 = Html.fromHtml("第一步：滑动列表，打开“<font color=#FD5439>已下载的应用</font>”");
        this.l.setText(v0_1);
    }

    @Override  // a.a.a.a.F
    public void onCreate(Bundle arg4) {
        super.onCreate(arg4);
        this.setContentView(R.layout.activity_seeding_transparent);
        this.g = (RelativeLayout) this.findViewById(R.id.rl_other);
        this.f = (RelativeLayout) this.findViewById(R.id.rl_xiaomi);
        ImageView v4 = (ImageView) this.findViewById(R.id.tv_icon);
        this.k = v4;
        v4.setImageBitmap(TransparentActivity.d());
        this.f();
        this.e();
        this.findViewById(R.id.container).setOnClickListener(new qb(this));
        this.i = (SwitchOnAnimView) this.findViewById(R.id.switch_on_anim_view);
        this.j = (SwitchOnAnimView) this.findViewById(R.id.click_on_anim_view);
        this.mHandler.postDelayed(new rb(this), 500L);
        Log.e("liuqiang-->", H.r());
    }

    @Override
    public void onDestroy() {
        AnimatorSet v0 = this.o;
        if (v0 != null) {
            v0.cancel();
        }

        SwitchOnAnimView v0_1 = this.i;
        if (v0_1 != null) {
            v0_1.stopAnim();
            this.i = null;
        }

        SwitchOnAnimView v0_2 = this.j;
        if (v0_2 != null) {
            v0_2.stopAnim();
            this.j = null;
        }

        super.onDestroy();
    }
}

