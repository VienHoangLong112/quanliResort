package com.example.quanliresort;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class BookingRoom extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    private TextView dateArrival;
    private EditText mStay,mRoom,mRSingle,mRoomTwin,mName,mNumber,mEmail;
    private Button mBooking;

    //Firebase
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference mReference = mDatabase.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_room);

        //mapping
        dateArrival = findViewById(R.id.textView_Arrival);
        mStay = findViewById(R.id.edtStaytime);
        mRoom = findViewById(R.id.edtRoom);
        mRSingle = findViewById(R.id.edtRoomSingle);
        mRoomTwin = findViewById(R.id.edtRoomTwin);
        mName = findViewById(R.id.edtFullname);
        mNumber = findViewById(R.id.edtPhone);
        mEmail = findViewById(R.id.edtEmail);
        mBooking = findViewById(R.id.btnBook);

        //
        mBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Room = mRoom.getText().toString().trim();
                String RSingle = mRSingle.getText().toString().trim();
                String RTwin = mRoomTwin.getText().toString().trim();
                String Name = mName.getText().toString().trim();
                String Number = mNumber.getText().toString().trim();
                String Email = mEmail.getText().toString().trim();
                String Arrival = dateArrival.getText().toString().trim();
                String Stay = mStay.getText().toString().trim();
                //
                Map<String,String > nameMap = new HashMap<String, String>();
                nameMap.put("Arrival Date:",Arrival);
                nameMap.put("Stay:",Stay);
                nameMap.put("Single Room",RSingle);
                nameMap.put("Twin Room",RTwin);
                nameMap.put("Number Rooms:",Room);
                nameMap.put("Full Name Of Customer:",Name);
                nameMap.put("Number Phone:",Number);
                nameMap.put("Email:", Email);

                mReference.push().setValue(nameMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(BookingRoom.this, "BOOKING SUCCESSFUL!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Home.class));
                        }
                    }
                });
            }
        });
        findViewById(R.id.btnArrival).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
    }
    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        String date = i2+"/"+i1+"/"+i;
        dateArrival.setText(date);

    }
}
