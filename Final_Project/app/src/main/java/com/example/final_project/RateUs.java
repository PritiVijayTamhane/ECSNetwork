package com.example.final_project;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RateUs extends AppCompatActivity {

    RatingBar rt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_us);

        //binding MainActivity.java with activity_main.xml file
        rt = (RatingBar) findViewById(R.id.ratingBar);

        //finding the specific RatingBar with its unique ID
        LayerDrawable stars = (LayerDrawable) rt.getProgressDrawable();

        //Use for changing the color of RatingBar
        stars.getDrawable(2).setColorFilter(Color.DKGRAY, PorterDuff.Mode.SRC_ATOP);
    }

    public void Call(View v) {
        // This function is called when button is clicked.
        // Display ratings, which is required to be converted into string first.
        TextView t = (TextView) findViewById(R.id.textView2);
        t.setText("You Rated :" + String.valueOf(rt.getRating()));
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
