package com.example.fishing_app;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class LogoActivity extends AppCompatActivity {
    private Animation logoAnim, buttonLogoAnim;
    private Button button;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logo_activity);
        init();
        startMainActivity();
    }

    private void init() {
        logoAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.alpha_anim);
        buttonLogoAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.logo_anim);
        imageView = findViewById(R.id.imageView2);
        button = findViewById(R.id.button);
        imageView.startAnimation(logoAnim);
        button.startAnimation(buttonLogoAnim);
    }

    public void onClickStart(View view) {
        startActivity(new Intent(LogoActivity.this, MainActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    private void startMainActivity() {
            new Thread(() -> {
                try {
                    Thread.sleep(3000);
                    startActivity(new Intent(LogoActivity.this, MainActivity.class));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
    }
}
