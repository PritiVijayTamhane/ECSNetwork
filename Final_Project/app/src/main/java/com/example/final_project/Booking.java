package com.example.final_project;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDateTime;
import java.util.Calendar;

public class Booking extends AppCompatActivity {
    //    private DatabaseReference mydb;
    String vehicle;
    private TimePickerDialog timePickerDialog;
    private TimePickerDialog timePickerDialog2;
    private DatePickerDialog datePickerDialog;
    private TextView datepicker;
    private TextView fromtime, totime;
    private TimePicker timepicker;
    private EditText textInputUsername;
    public static  boolean  inserted  ;
    String name;


//    Spinner spinner;
//    String[] vehicle = {"2 wheeler", "3 wheeler", "4 wheeler"};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");

        textInputUsername = (EditText) findViewById(R.id.name);
        datepicker = (TextView) findViewById(R.id.date);
        timepicker = (TimePicker) findViewById(R.id.av2);
        fromtime = (TextView) findViewById(R.id.fromtime);
        totime = (TextView) findViewById(R.id.totime);

        textInputUsername.setText(name);

        TimePicker t1;
//        spinner = (Spinner) findViewById(R.id.spinner);
        Button b1 = (Button) findViewById(R.id.b2);
        Button b2 = (Button) findViewById(R.id.b3);
//        Button b3 = (Button) findViewById(R.id.b4);
//      ArrayAdapter<String> adapter= new ArrayAdapter<>(Booking.this, android.R.layout.simple_spinner_dropdown_item, vehicle);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//        if (spinner!=null) {
//            spinner.setAdapter(adapter);
//        }
//        else
//        {
//            Toast.makeText(Booking.this, "Spinner is null", Toast.LENGTH_SHORT).show();
//        }
        RadioGroup rg = (RadioGroup) findViewById(R.id.rgroup);
        RadioButton r1 = (RadioButton) findViewById(R.id.r1);
        RadioButton r2 = (RadioButton) findViewById(R.id.r2);
        RadioButton r3 = (RadioButton) findViewById(R.id.r3);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (r1.isChecked()) {
                    Toast.makeText(Booking.this, "2-wheeler is selected from group", Toast.LENGTH_SHORT).show();
                } else if (r2.isChecked()) {
                    Toast.makeText(Booking.this, "3-wheeler is selected", Toast.LENGTH_SHORT).show();
                } else if (r3.isChecked()) {
                    Toast.makeText(Booking.this, "4-wheeler is selected from group", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Booking.this, "Please select any one of them ", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                                              @Override
//                                              public void onItemSelected(AdapterView<?> parent , View view, int posotion, long id) {
//                                                  String value = parent.getItemAtPosition(posotion).toString();
//                                                  Toast.makeText(Booking.this, value, Toast.LENGTH_SHORT).show();
//                                              }
//
//                                              @Override
//                                              public void onNothingSelected(AdapterView<?> parent) {
//
//                                              }
//                                          });

        initDatePicker();
        datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        initTimePicker();
        timepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
            }
        });


        initTimePicker2();
//        timepicker2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) { timePickerDialog2.show(); }
//        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String vehicle;
                String name = textInputUsername.getText().toString();
                String date = datepicker.getText().toString();
                String time = String.valueOf(timepicker.getHour() + ":" + timepicker.getMinute());
                String time2 = totime.getText().toString();
                if (name == null | date == null | time == null) {
                    Toast.makeText(Booking.this, "There is nothing Entered please input", Toast.LENGTH_SHORT).show();
                } else if (validateUsername()) {

                    LocalDateTime checktime = LocalDateTime.now();
                    if ((timepicker.getHour() + timepicker.getMinute()) >= (checktime.getHour() + checktime.getMinute())) {
                        Toast.makeText(Booking.this, "Succesfully Selected time", Toast.LENGTH_SHORT).show();

                        if (r1.isChecked()) {
                            vehicle = r1.getText().toString();
                            processinsert(name, date, time, totime.getText().toString(), vehicle);
                        } else if (r2.isChecked()) {
                            vehicle = r2.getText().toString();
                            processinsert(name, date, time, totime.getText().toString(), vehicle);
                        } else if (r3.isChecked()) {
                            vehicle = r3.getText().toString();
                            processinsert(name, date, time, totime.getText().toString(), vehicle);
                            inserted = true;
                        } else {
                            Toast.makeText(Booking.this, "Please select any one ", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Booking.this, "Please Select correct time ", Toast.LENGTH_SHORT).show();
                    }

//                    if (name.isEmpty() | date.isEmpty() | time.isEmpty() | vehicle.isEmpty()) {
//                        Toast.makeText(Booking.this, "Please insert a record first", Toast.LENGTH_SHORT).show();
                }
//                    else {
//                        processinsert(name, date, time, totime.getText().toString(), vehicle);
//                    }
            }


        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!inserted) {
                    Toast.makeText(Booking.this, "Please insert a record first", Toast.LENGTH_SHORT).show();
                }


                else {
                    Intent intent1 = new Intent(getApplicationContext(), Bookingstatus.class);
                    intent1.putExtra("name", name);
                    startActivity(intent1);
                }
            }
        });


//        b3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                CompanyDatabase db = new CompanyDatabase(Booking.this);
//                String name = textInputUsername.getText().toString();
//                String date = datepicker.getText().toString();
//                String time = String.valueOf(timepicker.getHour())+":"+String.valueOf(timepicker.getMinute());
//
//
//
//
//
//                String vehicle = null;
//                if (r1.isChecked()) {
//                    vehicle = r1.getText().toString();
//                } else if (r2.isChecked()) {
//                    vehicle = r2.getText().toString();
//                } else if (r3.isChecked()) {
//                    vehicle = r3.getText().toString();
//                } else {
//                    Toast.makeText(Booking.this, "Please select any one ", Toast.LENGTH_SHORT).show();
//                }
//                String message = db.cancelBooking(name, date, time, totime.getText().toString(), vehicle);
//                Toast.makeText(Booking.this, message, Toast.LENGTH_SHORT).show();
//
//
//            }
//
//        });
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1 + 1;
                datepicker.setText(i2 + "/-" + i1 + "/" + i);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis());
        cal.add(Calendar.DAY_OF_MONTH,2);
        datePickerDialog.getDatePicker().setMaxDate(cal.getTimeInMillis()); // Set the maximum date to today
    }

    private void initTimePicker() {
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                timepicker.setHour(i);
                timePicker.setMinute(i1);

            }
        };
        Calendar cal = Calendar.getInstance();
        int hrs = cal.get(Calendar.HOUR);
        int mins = cal.get(Calendar.MINUTE);
        int style = AlertDialog.THEME_HOLO_DARK;
        timePickerDialog = new TimePickerDialog(this, style, timeSetListener, hrs, mins, true);
    }


    private void initTimePicker2() {
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                // Do nothing
            }
        };

        Calendar cal = Calendar.getInstance();
        int hours = cal.get(Calendar.HOUR_OF_DAY) + 4; // Add 4 hours to current time
        int minutes = cal.get(Calendar.MINUTE);

        timePickerDialog2 = new TimePickerDialog(this, AlertDialog.THEME_HOLO_DARK, timeSetListener, hours, minutes, true);
        timePickerDialog2.setTitle("Selected Time + 4 hours");

        timepicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {

            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                // TODO Auto-generated method stub
//               timepicker2.setHour(hourOfDay+4);
//               timepicker2.setMinute(minute);
                totime.setText((hourOfDay + 4) + ":" + minute);
                fromtime.setText(hourOfDay + ":" + minute);
            }

        });
    }


    private boolean validateUsername() {
        String usernameInput = textInputUsername.getText().toString().trim();

        if (usernameInput.isEmpty()) {
            textInputUsername.setError("Field can't be empty");
            return false;
        } else if (usernameInput.length() > 30) {
            textInputUsername.setError("Username too long");
            return false;
//
        } else {
            textInputUsername.setError(null);
            return true;
        }
    }

    private void processinsert(String Name, String Date, String From, String To, String vehicle) {
        CompanyDatabase mydb = new CompanyDatabase(this);

        String result = mydb.addbook(Name, Date, From, To, vehicle);
        Toast.makeText(Booking.this, result, Toast.LENGTH_LONG).show();
    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//    }


}