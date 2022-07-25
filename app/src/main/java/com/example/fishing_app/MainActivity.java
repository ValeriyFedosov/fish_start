package com.example.fishing_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;



import com.example.fishing_app.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener{
    public static final String CATEGORY = "category";
    public static final String POSITION = "position";
    private int categoryIndex;
    private ActivityMainBinding activityMainBinding;
    private String[] array;
    DrawerLayout drawerLayout;
    private ArrayAdapter<String> arrayAdapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        array = getResources().getStringArray(R.array.fish_array);
        // android.R.layout.simple_list_item_1 - шаблон, в которм текст элементов массива идёт
        // один над другим с разделетельной полосой
        arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, new ArrayList<>(
                Arrays.asList(array)));

        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(arrayAdapter);
        //
        listView.setOnItemClickListener((parent, view, position, id) -> {
            // замапить/получить разрешение на создание нового Activity. Также добавление в Манифест
            Intent intent = new Intent(MainActivity.this, TextContentActivity.class);
            intent.putExtra(CATEGORY, categoryIndex);
            intent.putExtra(POSITION, position);
            startActivity(intent);
        });

        setSupportActionBar(activityMainBinding.appBarMain.toolbar);
        drawerLayout = activityMainBinding.drawerLayout;
        NavigationView navigationView = activityMainBinding.navView;

        toolbar = findViewById(R.id.toolbar);
        // кнопка сверху слева в 3 полоски
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        toolbar.setTitle(R.string.fish);
        return true;
    }


    // setNavigationItemSelectedListener(this) - при нажатии на кнопку суда будет передаваться выбранный элемент
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // понять какой именно item нажат
        int id = item.getItemId();
        switch (id) {
            case R.id.id_fish:
                clearAddAllNotify(R.string.fish, R.array.fish_array);
                break;
            case R.id.id_lure:
                clearAddAllNotify(R.string.lure, R.array.lure_array);
                break;
            case R.id.id_staff:
                clearAddAllNotify(R.string.staff, R.array.staff_array);
                break;
            case R.id.id_history:
                clearAddAllNotify(R.string.history, R.array.history_array);
                break;
            case R.id.id_advice:
                clearAddAllNotify(R.string.advice, R.array.advice_array);
                break;
        }
        // свернуть Nav Drawer при нажатии на кнопку меню
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    private void clearAddAllNotify(int category, int resources_array) {
        this.array = getResources().getStringArray(resources_array);
        toolbar.setTitle(category);
        arrayAdapter.clear();
        arrayAdapter.addAll(array);
        arrayAdapter.notifyDataSetChanged();
        categoryIndex = category;
    }
}