package ait.hu.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final ImageView ivCloudy = findViewById(R.id.ivCloudy);
        final ImageView ivSunny = findViewById(R.id.ivSunny);
        final ImageView ivSunnyWithClouds = findViewById(R.id.ivSunnyWithClouds);
        final ImageView ivSnow = findViewById(R.id.ivSnow);
        final ImageView ivRaining = findViewById(R.id.ivRaining);
        final ImageView ivLightning = findViewById(R.id.ivLightning);

        // for sun and snow
        final Animation rotateSnow = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.rotate);
        final Animation rotateSun = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.rotate);
        final Animation enlargeSun = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.enlarge);

        // for all others
        final Animation translateSnow = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.translate_right);
        final Animation translateRaining = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.translate_left);
        final Animation translateLightning = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.translate_right);
        final Animation translateCloudy = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.translate_left);
        final Animation translateSunnyWithClouds = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.translate_right);


        //Snow rotate
        ivSnow.startAnimation(rotateSnow);
        rotateSnow.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ivSnow.startAnimation(translateSnow);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        //Snow translate_right
        translateSnow.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ivRaining.startAnimation(translateRaining);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        //Rain translate_right
        translateRaining.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ivLightning.startAnimation(translateLightning);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        translateLightning.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ivCloudy.startAnimation(translateCloudy);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        translateCloudy.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ivSunnyWithClouds.startAnimation(translateSunnyWithClouds);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        translateSunnyWithClouds.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ivSunny.startAnimation(rotateSun);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        rotateSun.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ivSunny.startAnimation(enlargeSun);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        enlargeSun.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                finish();
                startActivity(new Intent(SplashActivity.this, MainActivity.class));

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}