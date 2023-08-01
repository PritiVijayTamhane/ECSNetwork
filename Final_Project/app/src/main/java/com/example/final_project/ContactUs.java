package com.example.final_project;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.final_project.databinding.ActivityMapsBinding;

public class ContactUs extends AppCompatActivity {

    TextView phn, mail;
    LinearLayout l1, l2;
    String number;

    ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        l1 = findViewById(R.id.l1);
        l2 = findViewById(R.id.l2);
        phn = findViewById(R.id.phn);
        mail = findViewById(R.id.mail);

        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailId = mail.getText().toString();
                Intent intent = new Intent(getApplicationContext(), Email.class);
                intent.putExtra("emailId", emailId);
                startActivity(intent);
            }
        });

        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                number = phn.getText().toString().trim();

                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(number)));
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = getIntent();
        String flag = intent.getStringExtra("flag");

        if (flag == "1"){
            startActivity(new Intent(getApplicationContext(), Dashboard.class));
            finish();
        }
        else if (flag == "2"){
            startActivity(new Intent(getApplicationContext(), AdminDashboard.class));
            finish();
        }
        else {
            super.onBackPressed();
        }
    }
}