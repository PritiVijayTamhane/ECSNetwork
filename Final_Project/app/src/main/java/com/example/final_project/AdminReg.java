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

public class AdminReg extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText admName, admEmail, admPassword, admPhoneNumber;
    Button RegisterButton;
    TextView mLoginButton;
    ProgressBar progressBar;
    String email, password, fullname, phonenumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        admName = findViewById(R.id.fullname);
        admEmail = findViewById(R.id.email);
        admPassword = findViewById(R.id.password);
        admPhoneNumber = findViewById(R.id.phonenumber);
        RegisterButton = findViewById(R.id.registerbutton);
        progressBar = findViewById(R.id.progressbar);
        mLoginButton = findViewById(R.id.createtext);


        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });


        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = admEmail.getText().toString().trim();
                password = admPassword.getText().toString().trim();
                fullname = admName.getText().toString();
                phonenumber = admPhoneNumber.getText().toString();

                if (TextUtils.isEmpty(fullname)) {
                    admName.setError("Name is required");
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    admEmail.setError("Email is required");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    admPassword.setError("Password is required");
                    return;
                }

                if (password.length() < 6) {
                    admPassword.setError("Password must be more than 6 letters");
                    return;
                }

                if (TextUtils.isEmpty(phonenumber)) {
                    admPhoneNumber.setError("Phone number is required");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                CompanyDatabase adminDatabase = new CompanyDatabase(AdminReg.this);
                boolean result = adminDatabase.regAdmin(fullname, email, phonenumber, password);

                if (result) {
                    Toast.makeText(AdminReg.this, "Registeration Successful", Toast.LENGTH_SHORT).show();
//                    NavigationView navigationView = findViewById(R.id.navigation_view);
                    Intent intent = new Intent(getApplicationContext(), AdminDashboard.class);
                    intent.putExtra("email", email);
                    startActivity(intent);
                } else {
                    Toast.makeText(AdminReg.this, "Registeration failed", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(AdminReg.this);

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