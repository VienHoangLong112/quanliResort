package com.example.quanliresort;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

import static com.example.quanliresort.R.layout.activity_home;
import static com.google.android.material.navigation.NavigationView.*;

public class Home extends AppCompatActivity implements OnNavigationItemSelectedListener {
    //Var
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_home);
        //Mapping
        toolbar = findViewById(R.id.tool_bar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        //Resolve Toolbar
        setSupportActionBar(toolbar);
        //Resolve Navigation View
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle); toggle.syncState();
        navigationView.bringToFront();

        navigationView.setNavigationItemSelectedListener(this);

    }
    // Avoid close application on Back pressed
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }


    //Onclick item
    public void click_Room(View view){
        startActivity(new Intent(getApplicationContext(), BookingRoom.class));
        finish();

    }
    public void click_Menu(View view){
        startActivity(new Intent(getApplicationContext(),FoodAndDrink.class));
        finish();

    }
    public void click_viewResort(View view){
        startActivity(new Intent(getApplicationContext(),ViewResort.class));
        finish();

    }
    public void click_Service(View view){
        startActivity(new Intent(getApplicationContext(),Service.class));
        finish();

    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_bookroom:
                Intent intent = new Intent(Home.this, BookingRoom.class);
                startActivity(intent);
                break;
        }
        switch (item.getItemId()){
            case R.id.menu_logout:
                Intent intent = new Intent(Home.this, Login.class);
                startActivity(intent);
                break;
        }
        switch (item.getItemId()){
            case R.id.menu_account:
                Intent intent = new Intent(Home.this, Register.class);
                startActivity(intent);
                break;
        }
        switch (item.getItemId()){
            case R.id.menu_aboutus:
                Intent intent = new Intent(Home.this, AboutUs.class);
                startActivity(intent);
                break;
        }
        switch (item.getItemId()){
            case R.id.menu_order:
                Intent intent = new Intent(Home.this, OrderFood.class);
                startActivity(intent);
                break;
        }
        switch (item.getItemId()){
            case R.id.menu_location:
                Intent intent = new Intent(Home.this, Location.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}
