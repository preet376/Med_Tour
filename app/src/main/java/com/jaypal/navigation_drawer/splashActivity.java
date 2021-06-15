package com.jaypal.navigation_drawer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;

public class splashActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN = 5000;

    Animation topAnim,bottomAnim;
    ImageView imageview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);



    getSupportActionBar().hide();
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Animation
        // topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        //bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        topAnim = android.view.animation.AnimationUtils.loadAnimation(this,R.anim.top_anim);
        bottomAnim = android.view.animation.AnimationUtils.loadAnimation(this,R.anim.bottom_anim);

        imageview = findViewById(R.id.splashimageView);
        imageview.setAnimation(topAnim);
        imageview.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splashActivity.this,login.class);
                startActivity(intent);
                finish();

            }
        },SPLASH_SCREEN);

    }
}