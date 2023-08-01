package com.example.final_project;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class LocalUserLogin extends Fragment {
    EditText mEmail, mPassword;
    Button mLoginButton;
    TextView mCreatetext;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    String email, password, fullname;
    ArrayList<String> uData = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_local_user_login, container, false);
        mEmail = view.findViewById(R.id.Email);
        mPassword = view.findViewById(R.id.Password);
        progressBar = view.findViewById(R.id.progressBar);
        mLoginButton = view.findViewById(R.id.LoginButton);
        mCreatetext = view.findViewById(R.id.Createtext);

        fAuth = FirebaseAuth.getInstance();

        mCreatetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Register.class);
                startActivity(intent);
                ((Login) getActivity()).overridePendingTransition(0, 0);
            }
        });

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = mEmail.getText().toString().trim();
                password = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is Required");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is Required");
                    return;
                }

                if (password.length() < 6) {
                    mPassword.setError("Password must be minimum 6 letters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                mCreatetext.setVisibility(View.INVISIBLE);

                CompanyDatabase dbHelper = new CompanyDatabase(getActivity());
                boolean result = dbHelper.authenticateUser(email, password);
                if (result) {

                    String[] ud = dbHelper.getUserData(email);
                    fullname = ud[0];

                    uData.add(email);
                    uData.add(fullname);

                    Toast.makeText(getActivity(), "Login Successful", Toast.LENGTH_SHORT).show();
                    NavigationView navigationView = view.findViewById(R.id.navigation_view);

                    Intent intent = new Intent(getActivity(), Dashboard.class);
                    intent.putExtra("uData", uData);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    mCreatetext.setVisibility(View.VISIBLE);
                }

            }

        });

        return view;
    }
}