package com.example.fishing_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class TextContentActivity extends AppCompatActivity {
    private int category;
    private int position;
    private TextView text_content_view;
    private ImageView image_сontent;
    private ConstraintLayout cnst;
    private int[] fishes_array = {R.string.fish_1,R.string.fish_2,R.string.fish_3,R.string.fish_4,R.string.fish_5};
    private int[] image_fishes_array = {R.drawable.distr,R.drawable.jp1,R.drawable.jp2,R.drawable.jp3,R.drawable.jp4};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_content);
        text_content_view = findViewById(R.id.text_content_view);
        image_сontent = findViewById(R.id.image_сontent);
        cnst = findViewById(R.id.cnst);
        receiveIntent();
    }

    private void receiveIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            category = intent.getIntExtra(MainActivity.CATEGORY,0);
            position = intent.getIntExtra(MainActivity.POSITION,0);
        }
        switch (category) {
            case R.string.fish:
                text_content_view.setText(fishes_array[position]);
                image_сontent.setImageResource(image_fishes_array[position]);
            break;
            case R.string.lure:
                break;
            case R.string.staff:
                break;
            case R.string.history:
                break;
            case R.string.advice:
                break;
        }
    }
}
