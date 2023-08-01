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

public class AdminUserLogin extends Fragment {
    EditText admEmail, admPassword;
    Button admLoginButton;
    TextView mCreatetext;
    ProgressBar progressBar;
    String email, password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_user_login, container, false);
        admEmail = view.findViewById(R.id.Email);
        admPassword = view.findViewById(R.id.Password);
        progressBar = view.findViewById(R.id.progressBar);
        admLoginButton = view.findViewById(R.id.LoginButton);

        mCreatetext = view.findViewById(R.id.Createtext);


        mCreatetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AdminReg.class);
                startActivity(intent);
                ((Login) getActivity()).overridePendingTransition(0, 0);
            }
        });

        admLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = admEmail.getText().toString().trim();
                password = admPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    admEmail.setError("Email is Required");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    admPassword.setError("Password is Required");
                    return;
                }

                if (password.length() < 6) {
                    admPassword.setError("Password must be minimum 6 letters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                CompanyDatabase adminDatabase = new CompanyDatabase(getActivity());
                boolean result = adminDatabase.authenticateAdmin(email, password);
                if (result) {
                    Toast.makeText(getActivity(), "Login Successful", Toast.LENGTH_SHORT).show();
                    NavigationView navigationView = view.findViewById(R.id.navigation_view);
                    Intent intent = new Intent(getActivity(), AdminDashboard.class);
                    intent.putExtra("email", email);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
        return view;
    }
}