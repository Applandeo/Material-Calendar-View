package com.applandeo.materialcalendarsampleapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.applandeo.materialcalendarview.DatePicker;
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements OnSelectDateListener {

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

        Button openDatePickerDialog = (Button) findViewById(R.id.openDatePickerDialogButton);

        openDatePickerDialog.setOnClickListener(v -> {
            DatePicker datePicker = new DatePicker(this, this);
            datePicker.show();
        });
    }

    @Override
    public void onSelect(Calendar calendar) {
        Toast.makeText(getApplicationContext(), calendar.getTime().toString(), Toast.LENGTH_LONG).show();
    }
}
