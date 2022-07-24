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
    private ActivityMainBinding activityMainBinding;
    private String[] array;
    DrawerLayout drawer;
    private ArrayAdapter<String> arrayAdapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        ListView listView = findViewById(R.id.list_view);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            startActivity(new Intent(MainActivity.this, TextContentActivity.class));
        });
        array = getResources().getStringArray(R.array.fish_array);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>(
                Arrays.asList(array)));
        listView.setAdapter(arrayAdapter);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(activityMainBinding.appBarMain.toolbar);
        drawer = activityMainBinding.drawerLayout;
        NavigationView navigationView = activityMainBinding.navView;

        // кнопка сверху слева в 3 полоски
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
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
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    private void clearAddAllNotify(int title, int resources_array) {
        this.array = getResources().getStringArray(resources_array);
        toolbar.setTitle(title);
        arrayAdapter.clear();
        arrayAdapter.addAll(array);
        arrayAdapter.notifyDataSetChanged();
    }
}