package com.example.final_project;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class AdminDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FirebaseAuth fAuth;

    androidx.appcompat.widget.Toolbar toolbar;
    CardView compRegister, stationView;
    ArrayList<String> stations = new ArrayList<String>();
    CompanyDatabase companyDatabase;
    int back;
    String email;
    String flag = "2";
    ArrayList<String> strArray = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");

        strArray.add(email);
        strArray.add(flag);

        compRegister = findViewById(R.id.card1);
        stationView = findViewById(R.id.card2);

        drawerLayout = (DrawerLayout) findViewById(R.id.adminDashboard);
        navigationView = findViewById(R.id.navigation_view2);
        toolbar = findViewById(R.id.toolbar);

        stationView.setVisibility(View.INVISIBLE);

        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.admLogin).setVisible(false);
        menu.findItem(R.id.view).setVisible(false);
//        menu.findItem(R.id.admProfile).setVisible(false);

        compRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Intent intent = new Intent(AdminDashboard.this, StationReg.class);
               startActivity(intent);
            }
        });


        stationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminDashboard.this, ViewStations.class);
                startActivity(intent);
            }
        });

        setSupportActionBar(toolbar);

        fAuth = FirebaseAuth.getInstance();

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.admHome:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.admLogin:
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
                break;
            case R.id.register:
                Intent intent1 = new Intent(AdminDashboard.this, StationReg.class);
                startActivity(intent1);
                break;
            case R.id.view:
                startActivity(new Intent(AdminDashboard.this, ViewStations.class));
                finish();
                break;
            case R.id.Contact1:
                Intent intent = new Intent(getApplicationContext(), ContactUs.class);
                intent.putExtra("flag", flag);
                startActivity(intent);
                break;
            case R.id.share1:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                //TODO:
                break;
            case R.id.rateus1:
                Intent intent2 = new Intent(getApplicationContext(), RateUs.class);
                intent2.putExtra("flag", flag);
                startActivity(intent2);
                break;
            case R.id.admProfile:
                Intent intent4 = new Intent(getApplicationContext(), User_Profilr.class);
                intent4.putStringArrayListExtra("strArray", strArray);
                startActivity(intent4);
                break;
            case R.id.admLogout:
                Menu menu1 = navigationView.getMenu();
                menu1.findItem(R.id.admProfile).setVisible(false);
                menu1.findItem(R.id.admLogout).setVisible(false);
                startActivity(new Intent(getApplicationContext(), Login.class));
                Toast.makeText(this, "Signed Out", Toast.LENGTH_SHORT).show();
                this.recreate();
                break;
            case R.id.admRegister:
                startActivity(new Intent(getApplicationContext(), AdminReg.class));
                finish();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(AdminDashboard.this);

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

