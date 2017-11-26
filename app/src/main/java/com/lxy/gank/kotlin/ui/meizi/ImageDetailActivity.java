package com.lxy.gank.kotlin.ui.meizi;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.transition.Explode;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lxy.gank.kotlin.R;
import com.lxy.gank.kotlin.base.BaseActivity;
import com.wingsofts.dragphotoview.DragPhotoView;



public class ImageDetailActivity extends BaseActivity implements DragPhotoView.OnExitListener {

    public static final String IMG_URL = "img_url";
    public static final String LEFT = "img_left";
    public static final String TOP = "img_top";
    public static final String HEIGHT = "img_height";
    public static final String WIDTH = "img_width";

    private int mOriginLeft;
    private int mOriginTop;
    private int mOriginHeight;
    private int mOriginWidth;
    private int mOriginCenterX;
    private int mOriginCenterY;
    private float mTargetHeight;
    private float mTargetWidth;
    private float mScaleX;
    private float mScaleY;
    private float mTranslationX;
    private float mTranslationY;
    private DragPhotoView mDragView;

    public static void goToPage(Object context, String url, ImageView imageView) {

        Activity activity;
        Fragment fragment;

        Intent intent = new Intent(imageView.getContext(), ImageDetailActivity.class);
        intent.putExtra(IMG_URL, url);

        int location[] = new int[2];
        imageView.getLocationOnScreen(location);
        intent.putExtra(LEFT, location[0]);
        intent.putExtra(TOP, location[1]);
        intent.putExtra(HEIGHT, imageView.getHeight());
        intent.putExtra(WIDTH, imageView.getWidth());

        imageView.getContext().startActivity(intent);


        if (context instanceof Activity) {
            activity = (Activity) context;
            activity.overridePendingTransition(0, 0);

        } else if (context instanceof Fragment) {
            fragment = (Fragment) context;

            fragment.getActivity().overridePendingTransition(0, 0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(new Explode());//slide  explode
        }

        setContentView(R.layout.activity_image_preview);
        mDragView = (DragPhotoView) findViewById(R.id.drag_view);

        String url = getIntent().getStringExtra(IMG_URL);

        mOriginLeft = getIntent().getIntExtra(LEFT, 0);
        mOriginTop = getIntent().getIntExtra(TOP, 0);
        mOriginHeight = getIntent().getIntExtra(HEIGHT, 0);
        mOriginWidth = getIntent().getIntExtra(WIDTH, 0);
        mOriginCenterX = mOriginLeft + mOriginWidth / 2;
        mOriginCenterY = mOriginTop + mOriginHeight / 2;

        initData();

        Glide.with(this).load(url).thumbnail(0.1f).into(mDragView);

        mDragView.setOnExitListener(this);

        mDragView.setOnTapListener(new DragPhotoView.OnTapListener() {
            @Override
            public void onTap(DragPhotoView dragPhotoView) {
                // finishWithAnimation();
                //onBackPressed();
            }
        });
    }

    public void initData() {
        mDragView.getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        int[] location = new int[2];
                        mDragView.getLocationOnScreen(location);

                        mTargetHeight = (float) mDragView.getMeasuredHeight();
                        mTargetWidth = (float) mDragView.getMeasuredWidth();
                        mScaleX = (float) mOriginWidth / mTargetWidth;
                        mScaleY = (float) mOriginHeight / mTargetHeight;

                        float targetCenterX = location[0] + mTargetWidth / 2;
                        float targetCenterY = location[1] + mTargetHeight / 2;

                        mTranslationX = mOriginCenterX - targetCenterX;
                        mTranslationY = mOriginCenterY - targetCenterY;
                        mDragView.setTranslationX(mTranslationX);
                        mDragView.setTranslationY(mTranslationY);

                        mDragView.setScaleX(mScaleX);
                        mDragView.setScaleY(mScaleY);

                        startEnterAnimation();

                        mDragView.setMinScale(mScaleX);

                        mDragView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
    }


    public void startEnterAnimation() {
        final DragPhotoView photoView = mDragView;
        ValueAnimator translateXAnimator = ValueAnimator.ofFloat(photoView.getX(), 0);
        translateXAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                photoView.setX((Float) valueAnimator.getAnimatedValue());
            }
        });
        translateXAnimator.setDuration(300);
        translateXAnimator.start();

        ValueAnimator translateYAnimator = ValueAnimator.ofFloat(photoView.getY(), 0);
        translateYAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                photoView.setY((Float) valueAnimator.getAnimatedValue());
            }
        });
        translateYAnimator.setDuration(300);
        translateYAnimator.start();

        ValueAnimator scaleYAnimator = ValueAnimator.ofFloat(mScaleY, 1);
        scaleYAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                photoView.setScaleY((Float) valueAnimator.getAnimatedValue());
            }
        });
        scaleYAnimator.setDuration(300);
        scaleYAnimator.start();

        ValueAnimator scaleXAnimator = ValueAnimator.ofFloat(mScaleX, 1);
        scaleXAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                photoView.setScaleX((Float) valueAnimator.getAnimatedValue());
            }
        });
        scaleXAnimator.setDuration(300);
        scaleXAnimator.start();
    }

    private void performExitAnimation(final DragPhotoView view, float x, float y, float w, float h) {
        view.finishAnimationCallBack();
        float viewX = mTargetWidth / 2 + x - mTargetWidth * mScaleX / 2;
        float viewY = mTargetHeight / 2 + y - mTargetHeight * mScaleY / 2;
        view.setX(viewX);
        view.setY(viewY);

        float centerX = view.getX() + mOriginWidth / 2;
        float centerY = view.getY() + mOriginHeight / 2;

        float translateX = mOriginCenterX - centerX;
        float translateY = mOriginCenterY - centerY;


        ValueAnimator translateXAnimator = ValueAnimator.ofFloat(view.getX(), view.getX() + translateX);
        translateXAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                view.setX((Float) valueAnimator.getAnimatedValue());
            }
        });
        translateXAnimator.setDuration(300);
        translateXAnimator.start();
        ValueAnimator translateYAnimator = ValueAnimator.ofFloat(view.getY(), view.getY() + translateY);
        translateYAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                view.setY((Float) valueAnimator.getAnimatedValue());
            }
        });
        translateYAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                animator.removeAllListeners();
                finish();
                overridePendingTransition(0, 0);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        translateYAnimator.setDuration(300);
        translateYAnimator.start();
    }

    private void finishWithAnimation() {

        final DragPhotoView photoView = mDragView;
        ValueAnimator translateXAnimator = ValueAnimator.ofFloat(0, mTranslationX);
        translateXAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                photoView.setX((Float) valueAnimator.getAnimatedValue());
            }
        });
        translateXAnimator.setDuration(300);
        translateXAnimator.start();

        ValueAnimator translateYAnimator = ValueAnimator.ofFloat(0, mTranslationY);
        translateYAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                photoView.setY((Float) valueAnimator.getAnimatedValue());
            }
        });
        translateYAnimator.setDuration(300);
        translateYAnimator.start();

        ValueAnimator scaleYAnimator = ValueAnimator.ofFloat(1, mScaleY);
        scaleYAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                photoView.setScaleY((Float) valueAnimator.getAnimatedValue());
            }
        });
        scaleYAnimator.setDuration(300);
        scaleYAnimator.start();

        ValueAnimator scaleXAnimator = ValueAnimator.ofFloat(1, mScaleX);
        scaleXAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                photoView.setScaleX((Float) valueAnimator.getAnimatedValue());
            }
        });

        scaleXAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                animator.removeAllListeners();
                finish();
                overridePendingTransition(0, 0);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        scaleXAnimator.setDuration(300);
        scaleXAnimator.start();
    }


    @Override
    public void onBackPressed() {
       // EventBus.getDefault().post(new OnResumeEvent());
        finishWithAnimation();
    }

    @Override
    public void onExit(DragPhotoView view, float x, float y, float w, float h) {
       // EventBus.getDefault().post(new OnResumeEvent());

        performExitAnimation(view, x, y, w, h);
    }

}
