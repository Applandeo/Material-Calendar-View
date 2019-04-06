package com.applandeo.materialcalendarsampleapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;

import java.util.Calendar;

/**
 * Created by Mateusz Kornakiewicz on 23.10.2017.
 */

public class RangePickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.range_picker_activity);

        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);

        Button getDateButton = (Button) findViewById(R.id.getDateButton);
        getDateButton.setOnClickListener(v -> {
            for (Calendar calendar : calendarView.getSelectedDates()) {
                System.out.println(calendar.getTime().toString());

                Toast.makeText(getApplicationContext(),
                        calendar.getTime().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}