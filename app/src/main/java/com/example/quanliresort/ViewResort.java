package com.example.quanliresort;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ViewResort extends AppCompatActivity {
    ImageView mimageView1,  mimageView2,  mimageView3,  mimageView4,  mimageView5,  mimageView6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_resort);
        mimageView1 = findViewById(R.id.imageView1);
        mimageView2 = findViewById(R.id.imageView2);
        mimageView3 = findViewById(R.id.imageView3);
        mimageView4 = findViewById(R.id.imageView4);
        mimageView5 = findViewById(R.id.imageView5);
        mimageView6 = findViewById(R.id.imageView6);
        /* onclick Image Reception */
        mimageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(ViewResort.this,vr1.class);
                startActivity(intentLoadNewActivity);
            }
        });

        /* onclick Image Restaurant*/
        mimageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(ViewResort.this,vr2.class);
                startActivity(intentLoadNewActivity);
            }
        });
        /* onclick Image Conference Room*/
        mimageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(ViewResort.this,vr3.class);
                startActivity(intentLoadNewActivity);
            }
        });
        /* onclick Image Gym*/
        mimageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(ViewResort.this,vr4.class);
                startActivity(intentLoadNewActivity);
            }
        });
        /* onclick Image Lounge Bar*/
        mimageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(ViewResort.this,vr5.class);
                startActivity(intentLoadNewActivity);
            }
        });
        /* onclick Image Pool*/
        mimageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(ViewResort.this,vr6.class);
                startActivity(intentLoadNewActivity);
            }
        });

    }
}
