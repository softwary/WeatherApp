package ait.hu.weatherapp;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import ait.hu.weatherapp.Adapter.CityRecyclerAdapter;
import ait.hu.weatherapp.Data.AppDatabase;
import ait.hu.weatherapp.Data.City;
import ait.hu.weatherapp.touch.CityTouchHelperCallback;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, CityDialog.CityHandler {

    private CityRecyclerAdapter cityRecyclerAdapter;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        drawerLayout = findViewById(R.id.drawer_layout);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        RecyclerView recyclerView = findViewById(R.id.recyclerWeather);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));


        initCities(recyclerView);


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.btnAddCity) {
            showNewCityDialog();
        } else if (id == R.id.btnAbout) {
            Toast.makeText(MainActivity.this, "This app was created by Nikki Adevai", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initCities(final RecyclerView recyclerView) {
        new Thread(){
            @Override
            public void run() {
                final List<City> cities =
                        AppDatabase.getAppDatabase(MainActivity.this).cityDAO().getAll();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        cityRecyclerAdapter = new CityRecyclerAdapter(cities, MainActivity.this);
                        recyclerView.setAdapter(cityRecyclerAdapter);

                        CityTouchHelperCallback touchHelperCallback =
                                new CityTouchHelperCallback(cityRecyclerAdapter);
                        ItemTouchHelper touchHelper = new ItemTouchHelper(
                                touchHelperCallback);
                        touchHelper.attachToRecyclerView(recyclerView);
                    }
                });
            }
        }.start();
    }



    private void showNewCityDialog() {
        new CityDialog().show(getSupportFragmentManager(), "CityCreateDialog");
    }

    @Override
    public void onNewCityCreated(final City city) {
        new Thread() {
            @Override
            public void run() {
                long id = AppDatabase.getAppDatabase(MainActivity.this).cityDAO().insertCity(city);
                city.setCityId(id);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        cityRecyclerAdapter.addCity(city);
                    }
                });
            }
        }.start();
    }
}
