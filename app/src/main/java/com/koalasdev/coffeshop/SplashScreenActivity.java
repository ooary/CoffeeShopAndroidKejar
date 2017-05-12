package com.koalasdev.coffeshop;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.Image;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SplashScreenActivity extends AppCompatActivity {

    ImageView mSplashImg;
    TextView mCoffeShopText,mTagLineText;
    private boolean doAnimation = false;
    //const config for animation
    public static final int STARTUP_DELAY = 500;
    public  static final int ITEM_DURATION =1000;
    public static  final int ITEM_DELAY = 300;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (!hasFocus || doAnimation) return;
        startAnimation();
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }
    private void startAnimation(){
        mSplashImg = (ImageView)findViewById(R.id.mSplashImg);
        mCoffeShopText = (TextView) findViewById(R.id.mCoffeShopText);
        mTagLineText = (TextView)findViewById(R.id.mTagLineText);

        ViewCompat.animate(mSplashImg)
                   .translationY(250)
                    .alpha(1)
                .setStartDelay(STARTUP_DELAY)
                .setDuration(ITEM_DURATION)
                .setInterpolator(new BounceInterpolator())
                .start();

        ViewPropertyAnimatorCompat animatorView;

        animatorView = ViewCompat.animate(mCoffeShopText)
                        .translationY(-50)
                        .alpha(1)
                        .setStartDelay(500)
                        .setDuration(1000);

        ViewCompat.animate(mTagLineText)
                  .translationY(-50)
                  .alpha(1)
                  .setStartDelay(500)
                   .setDuration(1000);

        animatorView.setInterpolator(new DecelerateInterpolator()).start();

        Thread thread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    Intent intent = new Intent(SplashScreenActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        thread.start();


    }
}
