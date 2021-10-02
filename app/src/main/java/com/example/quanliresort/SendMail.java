package com.example.quanliresort;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SendMail extends AppCompatActivity {
    EditText mMessage, mSubject;
    TextView tvTo;
    Button btnSend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_mail);
        //mapping
        mMessage = findViewById(R.id.edt_Message);
        mSubject = findViewById(R.id.edt_Subject);
        tvTo = findViewById(R.id.tv_To);
        btnSend = findViewById(R.id.btn_Send);
        //
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMaid();
            }

            private void sendMaid() {
                String recipientList = tvTo.getText().toString();
                String [] tmp = recipientList.split(",");

                String subject = mSubject.getText().toString();
                String message = mMessage.getText().toString();

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.putExtra(Intent.EXTRA_EMAIL, tmp);
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, message);

                intent.setType("Message/rfc822");
                startActivity(Intent.createChooser(intent, "Choose an email client"));

            }
        });


    }
}
