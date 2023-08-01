package com.example.final_project;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class Register extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText mFullName, mEmail, mPassword, mPhoneNumber;
    Button mRegisterButton;
    TextView mLoginButton;
    ProgressBar progressBar;
    ArrayList<String> uData = new ArrayList<String>();
    String email, password, fullname, phonenumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFullName = findViewById(R.id.fullname);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mPhoneNumber = findViewById(R.id.phonenumber);
        mRegisterButton = findViewById(R.id.registerbutton);
        mLoginButton = findViewById(R.id.createtext);
        progressBar = findViewById(R.id.progressbar);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = mEmail.getText().toString().trim();
                password = mPassword.getText().toString().trim();
                fullname = mFullName.getText().toString();
                phonenumber = mPhoneNumber.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is required");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is required");
                    return;
                }

                if (password.length() < 6) {
                    mPassword.setError("Password must be more than 6 letters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                CompanyDatabase dbHelper = new CompanyDatabase(Register.this);
                boolean result = dbHelper.registerUser(fullname,email,password,phonenumber);
                if (result) {
                    uData.add(email);
                    uData.add(fullname);

                    Toast.makeText(Register.this, "Registeration Successful", Toast.LENGTH_SHORT).show();
                    NavigationView navigationView = findViewById(R.id.navigation_view);

                    Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                    intent.putExtra("uData", uData);
                    startActivity(intent);

                } else {
                    Toast.makeText(Register.this, "Registeration failed", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);

                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Register.this);

        alertDialog.setTitle("Exit Application");
        alertDialog.setMessage("Do you want to exit ?");

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishAffinity();
            }
        });

        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        alertDialog.show();
    }
}

