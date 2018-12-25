package com.applandeo.materialcalendarsampleapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;

import java.util.Calendar;

/**
 * Created by Mateusz Kornakiewicz on 26.05.2017.
 */

public class OneDayPickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_day_picker_activity);

        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);

        Calendar min = Calendar.getInstance();
        min.add(Calendar.DAY_OF_MONTH, -12);

        Calendar max = Calendar.getInstance();
        max.add(Calendar.DAY_OF_MONTH, 12);

        calendarView.setMinimumDate(min);
        calendarView.setMaximumDate(max);

        calendarView.setOnDayClickListener(eventDay ->
                Toast.makeText(getApplicationContext(),
                        eventDay.getCalendar().getTime().toString() + " "
                                + eventDay.isEnabled(),
                        Toast.LENGTH_SHORT).show());

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