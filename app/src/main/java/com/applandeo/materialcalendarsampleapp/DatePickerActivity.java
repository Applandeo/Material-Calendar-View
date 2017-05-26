package com.applandeo.materialcalendarsampleapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;

/**
 * Created by Mateusz Kornakiewicz on 26.05.2017.
 */

public class DatePickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_picker_activity);

        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);

        Button getDateButton = (Button) findViewById(R.id.getDateButton);
        getDateButton.setOnClickListener(v ->
                Toast.makeText(getApplicationContext(),
                        calendarView.getSelectedDate().getTime().toString(),
                        Toast.LENGTH_LONG).show());

    }
}