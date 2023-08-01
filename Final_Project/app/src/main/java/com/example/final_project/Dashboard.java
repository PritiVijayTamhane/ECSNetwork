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

import com.google.android.gms.location.LocationRequest;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FirebaseAuth fAuth;
    androidx.appcompat.widget.Toolbar toolbar;
    CardView searchStation, bookSession;
    private LocationRequest locationRequest;
    public static final int REQUEST_CHECK_SETTINGS = 1001;
    int flag1;
    String email, name;
    ArrayList<String> strArray = new ArrayList<String>();
    static String flag = "1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Intent intent = getIntent();
        strArray = intent.getStringArrayListExtra("uData");

        String[] userD = new String[strArray.size()];

        for(int i = 0; i < strArray.size(); i++){
            userD[i] = strArray.get(i);
        }

        email = userD[0];
        name = userD[1];


        searchStation = findViewById(R.id.card1);
        bookSession = findViewById(R.id.card2);

        bookSession.setVisibility(View.INVISIBLE);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.toolbar);

        Menu m1 = navigationView.getMenu();

        m1.findItem(R.id.login).setVisible(false);
        m1.findItem(R.id.book).setVisible(false);

        setSupportActionBar(toolbar);

        searchStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(Dashboard.this, MapsActivity.class);
                intent1.putExtra("name", name);
                startActivity(intent1);
            }
        });

        bookSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (flag1 == 1) {
                    Intent intent2 = new Intent(Dashboard.this, Booking.class);
                    intent2.putExtra("flag", "2");
                    startActivity(intent2);
                } else {
                    Intent intent2 = new Intent(Dashboard.this, Login.class);
                    startActivity(intent2);
                }
            }
        });

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.login:
                Intent intent3 = new Intent(Dashboard.this, Login.class);
                startActivity(intent3);
                break;
            case R.id.search:
                Intent intent4 = new Intent(Dashboard.this, MapsActivity.class);
                intent4.putExtra("name", name);
                startActivity(intent4);
                break;
            case R.id.book:
                Intent intent5 = new Intent(Dashboard.this, Booking.class);
                startActivity(intent5);
                break;
            case R.id.contact:
                Intent intent6 = new Intent(getApplicationContext(), ContactUs.class);
                intent6.putExtra("flag", flag);
                startActivity(intent6);
                break;
            case R.id.share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                //TODO:
                break;
            case R.id.rateus:
                Intent intent7 = new Intent(getApplicationContext(), RateUs.class);
                intent7.putExtra("flag", flag);
                startActivity(intent7);
                break;

            case R.id.profile:
                Intent intent8 = new Intent(getApplicationContext(), User_Profilr.class);
                intent8.putStringArrayListExtra("strArray", strArray);
                startActivity(intent8);
                break;
            case R.id.logout:
                startActivity(new Intent(getApplicationContext(), Login.class));
                Toast.makeText(this, "Signed Out", Toast.LENGTH_SHORT).show();
                this.recreate();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(Dashboard.this);

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


}