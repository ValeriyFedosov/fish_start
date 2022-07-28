package com.example.fishing_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextContentActivity extends AppCompatActivity {
    public static final String LIST_PREFERENCE_1 = "list_preference_1";
    private int category;
    private int position;
    private ActionBar actionBar;
    private SharedPreferences sharedPreferences;
    private Typeface typeface;
    private TextView text_content_view;
    private ImageView image_сontent;
    private int[] fishes_array = {R.string.fish_1,R.string.fish_2,R.string.fish_3,R.string.fish_4,R.string.fish_5};
    private String[] categ_array = {};
    private int[] image_fishes_array = {R.drawable.distr,R.drawable.jp1,R.drawable.jp2,R.drawable.jp3,R.drawable.jp4};
    private static final String BIG = "Большой";
    private static final String MID = "Средний";
    private static final String SMAll = "Маленький";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_content);
        init();
        receiveIntent();
    }

    private void init() {
        text_content_view = findViewById(R.id.text_content_view);
        image_сontent = findViewById(R.id.image_сontent);
        typeface = Typeface.createFromAsset(this.getAssets(), "fonts/Merriweather-Italic.ttf");
        text_content_view.setTypeface(typeface);
        actionBar = getSupportActionBar();
        categ_array = getResources().getStringArray(R.array.fish_array);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Resources resources = getResources();
        switch (sharedPreferences.getString(LIST_PREFERENCE_1, resources
                .getString(R.string.middle_value))) {
            case BIG: text_content_view.setTextSize(24);
                break;
            case MID: text_content_view.setTextSize(18);
                break;
            case SMAll: text_content_view.setTextSize(14);
                break;

        }
    }

    private void receiveIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            category = intent.getIntExtra(MainActivity.CATEGORY,0);
            position = intent.getIntExtra(MainActivity.POSITION,0);
        }
        switch (category) {
            case R.string.fish:
                actionBar.setTitle(categ_array[position]);
                image_сontent.setImageResource(image_fishes_array[position]);
                text_content_view.setText(fishes_array[position]);
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
