package com.example.final_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewStations extends AppCompatActivity {

    CompanyDatabase companyDatabase;
    ArrayList<String> stations = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stations);

        companyDatabase = new CompanyDatabase(ViewStations.this);

        Cursor res = companyDatabase.getdata();
        if (res.getCount() == 0) {
            Toast.makeText(ViewStations.this, "No entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }

        while (res.moveToNext()) {

            stations.add(res.getString(0));
        }

        int size = stations.size();
        String[] stationNames = new String[size];
        for (int i = 0; i < size; i++){
            stationNames[i] = stations.get(i);
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.station_list, stationNames);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ViewStations.this, AdminDashboard.class));
        finish();
    }
}
