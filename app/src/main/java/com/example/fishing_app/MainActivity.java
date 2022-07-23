package com.example.fishing_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;



import com.example.fishing_app.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener{
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private ListView listView;
    private String array[];
    private ArrayAdapter<String> arrayAdapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        listView = findViewById(R.id.list_view);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, TextContentActivity.class);
                startActivity(intent);
            }
        }

        );
        array = getResources().getStringArray(R.array.fish_array);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>(
                Arrays.asList(array)));
        listView.setAdapter(arrayAdapter);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        navigationView.setNavigationItemSelectedListener(this);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        toolbar.setTitle(R.string.fish);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.list_view);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.id_fish) {
            toolbar.setTitle(R.string.fish);
            array = getResources().getStringArray(R.array.fish_array);
            arrayAdapter.clear();
            arrayAdapter.addAll(array);
            arrayAdapter.notifyDataSetChanged();
        }
        if (id == R.id.id_lure) {
            toolbar.setTitle(R.string.lure);
            array = getResources().getStringArray(R.array.lure_array);
            arrayAdapter.clear();
            arrayAdapter.addAll(array);
            arrayAdapter.notifyDataSetChanged();
        }
        if (id == R.id.id_staff) {
            toolbar.setTitle(R.string.staff);
            array = getResources().getStringArray(R.array.staff_array);
            arrayAdapter.clear();
            arrayAdapter.addAll(array);
            arrayAdapter.notifyDataSetChanged();
        }
        if (id == R.id.id_history) {
            toolbar.setTitle(R.string.history);
            array = getResources().getStringArray(R.array.history_array);
            arrayAdapter.clear();
            arrayAdapter.addAll(array);
            arrayAdapter.addAll(array);
            arrayAdapter.notifyDataSetChanged();
        }
        if (id == R.id.id_advice) {
            toolbar.setTitle(R.string.advice);
            array = getResources().getStringArray(R.array.advice_array);
            arrayAdapter.clear();
            arrayAdapter.addAll(array);
            arrayAdapter.notifyDataSetChanged();
        }

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }
}