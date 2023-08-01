package com.example.final_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class User_Profilr extends AppCompatActivity {

    String[] userData = new String[3];
    TextView userName, userEmail, userPhn;
    TextView uName, uEmail;
    String email, flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profilr);

        Intent intent = getIntent();
        ArrayList<String> arr = intent.getStringArrayListExtra("strArray");

        String[] str = new String[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            str[i] = arr.get(i);
        }

        email = str[0];
        flag = str[1];

        Button update = findViewById(R.id.update);
        userName = findViewById(R.id.uName);
        userEmail = findViewById(R.id.uEmail);
        userPhn = findViewById(R.id.uPhn);
        uName = findViewById(R.id.full_name);
        uEmail = findViewById(R.id.email);

        CompanyDatabase dataBaseP = new CompanyDatabase(User_Profilr.this);
        CompanyDatabase adminDatabase = new CompanyDatabase(User_Profilr.this);

        if(flag.equals("2")) {

            userData = adminDatabase.getAdminData(email);

            userName.setText(userData[0]);
            userEmail.setText(userData[1]);
            userPhn.setText(userData[2]);
            uName.setText(userData[0]);
            uEmail.setText(userData[1]);
        }
        else {
            userData = dataBaseP.getUserData(email);

            userName.setText(userData[0]);
            userEmail.setText(userData[1]);
            userPhn.setText(userData[2]);
            uName.setText(userData[0]);
            uEmail.setText(userData[1]);
        }


        update.setVisibility(View.INVISIBLE);
    }
}