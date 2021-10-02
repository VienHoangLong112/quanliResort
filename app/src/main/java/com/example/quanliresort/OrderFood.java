package com.example.quanliresort;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.google.android.gms.common.SignInButton;
        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;

        import java.util.HashMap;
        import java.util.Map;

public class OrderFood extends AppCompatActivity {
    EditText mFood1, mFood2, mDrink1, mDrink2, mRoom, mNote;
    Button btnBookFood;
    FirebaseDatabase mDatabase1 = FirebaseDatabase.getInstance();
    DatabaseReference mReference1 = mDatabase1.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_food);
        //mapping
        mFood1 = findViewById(R.id.edt_oFood1);
        mFood2 = findViewById(R.id.edt_oFood2);
        mDrink1 = findViewById(R.id.edt_oDrink1);
        mDrink2 = findViewById(R.id.edt_oDrink2);
        mRoom = findViewById(R.id.edt_room);
        mNote = findViewById(R.id.edt_note);
        btnBookFood = findViewById(R.id.btn_bookFood);
        //
        btnBookFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Food1 = mFood1.getText().toString().trim();
                String Food2 = mFood2.getText().toString().trim();
                String Drink1 = mDrink1.getText().toString().trim();
                String Drink2 = mDrink2.getText().toString().trim();
                String Room = mRoom.getText().toString().trim();
                String Note = mNote.getText().toString().trim();
                //
                Map<String, String> nameMap = new HashMap<String, String>();
                nameMap.put("Room:",Room);
                nameMap.put("Food:",Food1);
                nameMap.put("Number of Dishes:",Food2);
                nameMap.put("Drink water:",Drink1);
                nameMap.put("Number of bottles:",Drink2);
                nameMap.put("Note:",Note);
                //
                mReference1.push().setValue(nameMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(OrderFood.this, "BOOKING FOOD SUCCESSFUL!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Home.class));
                        }
                    }
                });


            }
        });
    }
}
