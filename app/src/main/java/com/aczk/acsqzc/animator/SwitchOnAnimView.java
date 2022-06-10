package com.aczk.acsqzc.animator;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.animations.BaseAnimatorListener;
import com.fy.demo.R;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;
import com.utils.ViewUtil;


public class SwitchOnAnimView extends FrameLayout {
    public static final int FINGER_ANIM_DURATION = 300;
    public static final int CIRCLE_PT_ANIM_DURATION = 500;
    public Handler mHandler;
    public ImageView mCirclePtImgv;
    public ImageView mFingerImgv;
    public float mFingerMoveDistance;
    public float mCirclePtMoveDistance;
    public boolean isStopAnim;

    public SwitchOnAnimView(Context context) {
        this(context, null);
    }

    public SwitchOnAnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mHandler = new Handler();
        this.isStopAnim = false;
        LayoutInflater.from(context).inflate(R.layout.finger_switch_on_guide_layout, this, true);
        this.initView();
    }

    public static ImageView a(SwitchOnAnimView arg0) {
        return arg0.mFingerImgv;
    }

    public void startAnim() {
        this.isStopAnim = false;
        ViewHelper.setTranslationX(this.mCirclePtImgv, 0.0f);
        this.mCirclePtImgv.setBackgroundResource(R.mipmap.switch_off_circle_point);
        this.mFingerImgv.setBackgroundResource(R.mipmap.finger_normal);
        this.startFingerUpAnim();
    }

    public static Handler b(SwitchOnAnimView arg0) {
        return arg0.mHandler;
    }

    public void stopAnim() {
        this.isStopAnim = true;
    }

    public static ImageView c(SwitchOnAnimView arg0) {
        return arg0.mCirclePtImgv;
    }

    private void initView() {
        this.mCirclePtImgv = (ImageView)this.findViewById(R.id.switch_anim_circle_point);
        this.mFingerImgv = (ImageView)this.findViewById(R.id.finger_switch);
        this.mFingerMoveDistance = ViewUtil.dp2px(this.getContext(), 20.0f);
        this.mCirclePtMoveDistance = ViewUtil.dp2px(this.getContext(), 17.5f);
    }

    private void startCirclePointAnim() {
        ImageView mCirclePtImgv = this.mCirclePtImgv;
        if(mCirclePtImgv == null) {
            return;
        }

        ObjectAnimator circlePtAnim = ObjectAnimator.ofFloat(mCirclePtImgv, "translationX", new float[]{0.0f, this.mCirclePtMoveDistance});
        circlePtAnim.setDuration(500L);
        circlePtAnim.start();
    }

    public static void d(SwitchOnAnimView arg0) {
        arg0.startCirclePointAnim();
    }

    private void e() {
        ImageView mFingerImgv = this.mFingerImgv;
        if(mFingerImgv == null) {
            return;
        }

        ObjectAnimator fingerUpAnim = ObjectAnimator.ofFloat(mFingerImgv, "translationY", new float[]{-this.mFingerMoveDistance, 0.0f});
        fingerUpAnim.setDuration(300L);
        fingerUpAnim.addListener(new BaseAnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animator) {
                if (mHandler == null) {
                    return;
                }
                // 手指向上动画执行完成就设置手指View背景为点击状态的背景
                mFingerImgv.setBackgroundResource(R.mipmap.finger_click);
                // 点击之后为了提现停顿一下的感觉，延迟200毫秒执行其他动画
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mCirclePtImgv == null || mHandler == null) {
                            return;
                        }
                        // 将中间圆圈View背景设置为开关打开状态然后开始向右平移
                        mCirclePtImgv.setBackgroundResource(R.mipmap.switch_on_circle_point);
                        startCirclePointAnim();
                        // 延迟100毫秒启动手指向下平移动画
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                // 手指向下移动开始时设置手指背景为正常的状态
                                mFingerImgv.setBackgroundResource(R.mipmap.finger_normal);
                                startFingerUpAnim();
                            }
                        }, 100);
                    }
                }, 200);
            }
        });
        fingerUpAnim.start();
    }

    public static void e(SwitchOnAnimView arg0) {
        arg0.e();
    }

    private void startFingerUpAnim() {
        ObjectAnimator fingerDownAnim = ObjectAnimator.ofFloat(this.mFingerImgv, "translationY", new float[]{0.0f, -this.mFingerMoveDistance});
        fingerDownAnim.setDuration(300L);
        fingerDownAnim.addListener(new BaseAnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animator) {
                // 手指向下移动动画完成，整个动画流程结束，重新开始下一次流程，循环执行动画，间隔1秒
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (isStopAnim) {
                            return;
                        }
                        startAnim();
                    }
                }, 1000);
            }
        });
        fingerDownAnim.start();
    }

    public static boolean f(SwitchOnAnimView arg0) {
        return arg0.isStopAnim;
    }

    public void setFingerVisible(boolean arg2) {
        int v0;
        ImageView v2;
        if(arg2) {
            v2 = this.mFingerImgv;
            v0 = VISIBLE;
        } else {
            v2 = this.mFingerImgv;
            v0 = GONE;
        }

        v2.setVisibility(v0);
    }
}

