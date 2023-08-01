package com.example.final_project;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;

public class StationReg extends AppCompatActivity {

    EditText com_name, com_loc, com_manpow, com_c4, com_c3, com_c2, com_charavail;
    Button nxtBtn, save;
    LinearLayout l1, l2, l3;
    RadioGroup radioGroup4, radioGroup3, radioGroup2;
    RadioButton Yes4, No4, Yes3, No3, Yes2, No2;
    CardView cardView;
    int flag1, flag2, flag3;
    int f1, f2, f3, f4, f5, f6, f7;
    String compName, compLoc, manPower, chargersAvailable, c4w, c3w, c2w;
    int c4, c3, c2;
    int max4, max3, max2, avail;
    ArrayList<String> compDet = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_reg);

        nxtBtn = findViewById(R.id.next);
        cardView = findViewById(R.id.card1);
        com_name = findViewById(R.id.cName);
        com_loc = findViewById(R.id.cLoc);
        com_manpow = findViewById(R.id.mPow);
        com_charavail = findViewById(R.id.cAvail);
        com_c4 = findViewById(R.id.C4W);
        com_c3 = findViewById(R.id.C3W);
        com_c2 = findViewById(R.id.C2W);

        l1 = findViewById(R.id.layout1);
        l2 = findViewById(R.id.layout2);
        l3 = findViewById(R.id.layout3);

        l1.setVisibility(View.INVISIBLE);
        l2.setVisibility(View.INVISIBLE);
        l3.setVisibility(View.INVISIBLE);

        radioGroup4 = findViewById(R.id.grp4whl);
        Yes4 = findViewById(R.id.Yes4);
        No4 = findViewById(R.id.No4);

        radioGroup4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

                if (Yes4.isChecked()) {
                    l1.setVisibility(View.VISIBLE);
                    flag1 = 1;

                }
                if (No4.isChecked()) {
                    l1.setVisibility(View.INVISIBLE);
                    flag1 = 0;
                }
            }
        });

        radioGroup3 = findViewById(R.id.grp3whl);
        Yes3 = findViewById(R.id.Yes3);
        No3 = findViewById(R.id.No3);

        radioGroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

                if (Yes3.isChecked()) {
                    l2.setVisibility(View.VISIBLE);
                    flag2 = 1;
                }
                if (No3.isChecked()) {
                    l2.setVisibility(View.INVISIBLE);
                    flag2 = 0;
                }
            }
        });

        radioGroup2 = findViewById(R.id.grp2whl);
        Yes2 = findViewById(R.id.Yes2);
        No2 = findViewById(R.id.No2);

        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

                if (Yes2.isChecked()) {
                    l3.setVisibility(View.VISIBLE);
                    flag3 = 1;
                }
                if (No2.isChecked()) {
                    l3.setVisibility(View.INVISIBLE);
                    flag3 = 2;
                }
            }
        });


        nxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    compName = com_name.getText().toString();
                    compLoc = com_loc.getText().toString();
                    manPower = com_manpow.getText().toString();
                    chargersAvailable = com_charavail.getText().toString();

                    if (compName.length() < 4) {
                        com_name.setError("Station name must have at least 4 characters");
                        return;
                    }

                    if (compName.length() >= 4) {
                        for (int i = 0; i < 4; i++) {
                            if (!Character.isAlphabetic(compName.charAt(i))) {
                                com_name.setError("At least first four characters must be alphabetic");
                                return;
                            }
                        }
                    }

                    if (compLoc.length() == 0) {
                        com_loc.setError("Field is empty");
                        return;
                    }

                    if (manPower.length() == 0) {
                        com_manpow.setError("Field is empty");
                        return;
                    }

                    if(chargersAvailable.length() != 0){
                    for (int i = 0; i < 5; i++) {
                        String j = Integer.toString(i);
                        if (chargersAvailable.equals(j)) {
                            com_charavail.setError("Minimum five chargers are required");
                            return;
                        }
                    }}
                    else {
                        com_charavail.setError("Field is empty");
                    }

                    avail = Integer.parseInt(chargersAvailable);

                    if (flag1 == 1) {
                        c4w = com_c4.getText().toString();
                        c4 = Integer.parseInt(c4w);
                        if (c4w.length() == 0 || c4w.equals("0")) {
                            com_c4.setError("Minimum one charger is required, if not possible select no");
                            return;
                        }

                        if (c4 > avail) {
                            com_c4.setError("Conflict in number of Chargers");
                            return;
                        }
                    } else {
                        c4w = "0";
                    }

                    max3 = avail - c4;


                    if (flag2 == 1) {
                        c3w = com_c3.getText().toString();
                        c3 = Integer.parseInt(c3w);
                        if (c3w.length() == 0 || c3w.equals("0")) {
                            com_c3.setError("Minimum one charger is required, if not possible select no");
                            return;
                        }

                        if (c3 > max3) {
                            com_c3.setError("Conflict in number of Chargers");
                            return;
                        }
                    } else {
                        c3w = "0";
                    }

                    max2 = avail - (c4 + c3);

                    if (flag3 == 1) {
                        c2w = com_c2.getText().toString();
                        c2 = Integer.parseInt(c2w);
                        if (c2w.length() == 0 || c2w.equals("0")) {
                            com_c2.setError("Minimum one charger is required, if not possible select no");
                            return;
                        }

                        if (c2 > max2) {
                            com_c2.setError("Conflict in number of Chargers");
                            return;
                        }
                    } else {
                        c2w = "0";
                    }


                    compDet.add(compName);
                    compDet.add(compLoc);
                    compDet.add(manPower);
                    compDet.add(chargersAvailable);
                    compDet.add(c4w);
                    compDet.add(c3w);
                    compDet.add(c2w);

                    Intent intent = new Intent(StationReg.this, Company_Register.class);
                    intent.putExtra("compDet", compDet);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(StationReg.this, "Invalid Data Entries", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(StationReg.this);

        alertDialog.setTitle("Cancle Registration");
        alertDialog.setMessage("Do you want to cancle the registration ?");

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(StationReg.this, AdminDashboard.class));
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