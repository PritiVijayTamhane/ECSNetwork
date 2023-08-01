package com.example.final_project;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Bookingstatus extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<recyclerviewdata> dataholder = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookingstatus);

        // Initialize the RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
//        recyclerView.setHasFixedSize(true);

        // Use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        CompanyDatabase mydb = new CompanyDatabase(this);
        mydb.getWritableDatabase();
        Cursor cursor = mydb.slotbookedby();
        while (cursor.moveToNext())
        {
            recyclerviewdata recyclerviewdata = new recyclerviewdata(cursor.getString(0), cursor.getString(1),cursor.getString(2),cursor.getString(3), cursor.getString(4) );
            dataholder.add(recyclerviewdata);
        }
//         Add some data to the list
//        myDataList.add(new recyclerviewdata("pranav","12-12-12","11-12","2-Wheeler","FAST"));
//        myDataList.add(new recyclerviewdata("Text A", "Text B", "Text C", "Text D", "Text E", "Text F", "Text G", "Text H", "Text I", "Text J", "Text K", "Text L"));

        // Specify an adapter
        recyclerviewadapter mAdapter = new recyclerviewadapter(dataholder);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), Booking.class));
        finish();
    }
}