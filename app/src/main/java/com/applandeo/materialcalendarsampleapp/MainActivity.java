package com.applandeo.materialcalendarsampleapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button openCalendarButton = (Button) findViewById(R.id.openCalendarButton);

        openCalendarButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, CalendarActivity.class);
            startActivity(intent);
        });


        Button openDatePicker = (Button) findViewById(R.id.openDatePickerButton);

        openDatePicker.setOnClickListener(v -> {
            Intent intent = new Intent(this, DatePickerActivity.class);
            startActivity(intent);
        });
    }

}
