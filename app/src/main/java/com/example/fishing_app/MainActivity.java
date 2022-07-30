package com.example.fishing_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;



import com.example.fishing_app.databinding.ActivityMainBinding;
import com.example.fishing_app.settings.SettingActivity;
import com.example.fishing_app.utills.CustomArrayAdapter;
import com.example.fishing_app.utills.ListItem;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener{
    public static final String CATEGORY = "category";
    public static final String POSITION = "position";
    private int categoryIndex;
    private ActivityMainBinding activityMainBinding;
    private String[] array, arraySecName;
    DrawerLayout drawerLayout;
    private CustomArrayAdapter arrayAdapter;
    Toolbar toolbar;
    List<ListItem> itemList = new ArrayList<>();
    private  int[] array_fish_color = {R.color.red, R.color.teal, R.color.green};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        array = getResources().getStringArray(R.array.fish_array);
        arraySecName = getResources().getStringArray(R.array.fish_array_2);
        ListItem listItem;
        for (int i = 0; i < array.length; ++i) {
            listItem = new ListItem();
            listItem.setFish_name(array[i]);
            listItem.setSecond_name(arraySecName[i]);
            listItem.setImage_id(getImageColorByLength(i));
            itemList.add(listItem);
        }
        arrayAdapter = new CustomArrayAdapter(this, R.layout.list_view_item_1, itemList, getLayoutInflater());

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

    private int getImageColorByLength(int i) {
        switch (i) {
            case 0:
            case 3:
                return R.color.red;
            case 1:
            case 4:
                return R.color.teal;
            case 2:
                return R.color.green;
        }
        return R.color.red;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        toolbar.setTitle(R.string.fish);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this, SettingActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    // setNavigationItemSelectedListener(this) - при нажатии на кнопку навигации суда будет передаваться
    // выбранный элемент
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // понять какой именно item нажат
        int id = item.getItemId();
        switch (id) {
            case R.id.id_fish:
                array = getResources().getStringArray(R.array.fish_array);
                arraySecName = getResources().getStringArray(R.array.fish_array_2);
                ListItem listItem;
                for (int i = 0; i < array.length; ++i) {
                    listItem = new ListItem();
                    listItem.setFish_name(array[i]);
                    listItem.setSecond_name(arraySecName[i]);
                    listItem.setImage_id(getImageColorByLength(array_fish_color[i]));
                    itemList.add(listItem);
                }
                toolbar.setTitle(R.string.fish);
                //arrayAdapter.clear();
                //arrayAdapter.notifyDataSetChanged();
                categoryIndex = R.array.fish_array;
                break;
            case R.id.id_lure:
                //clearAddAllNotify(R.string.lure, R.array.lure_array);
                break;
            case R.id.id_staff:
                //clearAddAllNotify(R.string.staff, R.array.staff_array);
                break;
            case R.id.id_history:
                //clearAddAllNotify(R.string.history, R.array.history_array);
                break;
            case R.id.id_advice:
                //clearAddAllNotify(R.string.advice, R.array.advice_array);
                break;
        }
        // свернуть Nav Drawer при нажатии на кнопку меню
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    //private void clearAddAllNotify(int category, int resources_array) {
        //arrayAdapter.addAll(array);
        //this.array = getResources().getStringArray(resources_array);

    //}
}