package com.example.final_project;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Company_Register extends AppCompatActivity {
    TextView cName, cLoc, mPow, cAvail, fCAvail, sCAvail, C4W, C3W, C2W, rBat;
    Button sbmtBtn, edtBtn;
    String companyName, companyLoation;
    int manPower,totalChargers, fstChargers, slwChargers, twoWheeler, threeWheeler, fourWheeler, remBatteries;
    RelativeLayout relativeLayout;
    ArrayList<String> compDet;
    CompanyDatabase companyDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_register);

        relativeLayout = findViewById(R.id.CompRegMain);

        Intent intent = getIntent();
        compDet = intent.getStringArrayListExtra("compDet");

        String[] comDetails = new String[compDet.size()];

        for(int i = 0; i < compDet.size(); i++){
            comDetails[i] = compDet.get(i);
        }

        companyName = comDetails[0];
        companyLoation = comDetails[1];
        manPower = Integer.parseInt(comDetails[2]);
        totalChargers = Integer.parseInt(comDetails[3]);
        fourWheeler = Integer.parseInt(comDetails[4]);
        threeWheeler = Integer.parseInt(comDetails[5]);
        twoWheeler = Integer.parseInt(comDetails[6]);

        cName = findViewById(R.id.cName);
        cName.setText(companyName);
        cLoc = findViewById(R.id.cLoc);
        cLoc.setText(companyLoation);
        mPow = findViewById(R.id.mPow);
        String MP = Integer.toString(manPower);
        mPow.setText(MP);
        cAvail = findViewById(R.id.cAvail);
        String CA = Integer.toString(totalChargers);
        cAvail.setText(CA);
        C4W = findViewById(R.id.C4W);
        String c4 = Integer.toString(fourWheeler);
        C4W.setText(c4);
        C3W = findViewById(R.id.C3W);
        String c3 = Integer.toString(threeWheeler);
        C3W.setText(c3);
        C2W = findViewById(R.id.C2W);
        String c2 = Integer.toString(twoWheeler);
        C2W.setText(c2);


        companyDatabase = new CompanyDatabase(this);

        sbmtBtn = findViewById(R.id.sbmtBtn);


        sbmtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Boolean checkInsertData = companyDatabase.insertData(companyName, companyLoation, manPower, totalChargers, twoWheeler, threeWheeler, fourWheeler);

                if (checkInsertData == true) {
                    Toast.makeText(Company_Register.this, "Company Registered Successfully", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Company_Register.this, "Company Not Register", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(Company_Register.this, ViewStations.class);
                startActivity(intent);
            }
        });
    }

   @Override
    public void onBackPressed() {

       AlertDialog.Builder alertDialog = new AlertDialog.Builder(Company_Register.this);

       alertDialog.setTitle("Cancle Registration");
       alertDialog.setMessage("Do you want to cancle the registration ?");

       alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialogInterface, int i) {
               startActivity(new Intent(Company_Register.this, AdminDashboard.class));
               finish();
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


