package com.example.quanliresort;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Service extends AppCompatActivity {
    Button btnOrderFood, btnViewRoom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        btnOrderFood = findViewById(R.id.btn_orderFoood);
        btnViewRoom = findViewById(R.id.btn_viewroom);
        //
        btnOrderFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(Service.this,OrderFood.class);
                startActivity(intentLoadNewActivity);
            }
        });
        //
        btnViewRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(Service.this,ViewRoom.class);
                startActivity(intentLoadNewActivity);
            }
        });
    }
}
