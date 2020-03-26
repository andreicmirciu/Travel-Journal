package com.andrei.traveljournal;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;

import com.andrei.traveljournal.activities.AddTripActivity;
import com.andrei.traveljournal.adapters.TripListAdapter;
import com.andrei.traveljournal.models.TripModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout mDrawer;
    private static ArrayList<TripModel> mTripList = new ArrayList<>();
    public static TripListAdapter adapter = new TripListAdapter(mTripList);

    public void getEditActivity() {
        startActivity(
                new Intent(MainActivity.this, AddTripActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setColorFilter(Color.WHITE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, AddTripActivity.class), 999);

            }
        });

        mDrawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        initImageBitmaps();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void initImageBitmaps() {

        ImageView image = findViewById(R.id.trip_photo);
        mTripList = new ArrayList<>();

        TripModel trip1 = new TripModel();
        trip1.setTripName("Test 1");
        trip1.setDestination("Destinatie test 1");
        trip1.setPicture(image);

        TripModel trip2 = new TripModel();
        trip2.setTripName("Test 2");
        trip2.setDestination("Destinatie test 2");
        trip2.setPicture(image);

        TripModel trip3 = new TripModel();
        trip3.setTripName("Test 3");
        trip3.setDestination("Destinatie test 3");
        trip3.setPicture(image);

        mTripList.add(trip1);
        mTripList.add(trip2);
        mTripList.add(trip3);

        initRecyclerView();

    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_favourite) {

        } else if (id == R.id.nav_aboutus) {

        } else if (id == R.id.nav_contact) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
