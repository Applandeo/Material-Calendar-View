package com.applandeo.materialcalendarsampleapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.listeners.OnCalendarPageChangeListener;

import java.util.Calendar;

/**
 * Created by Mateusz Kornakiewicz on 23.10.2017.
 */

public class ManyDaysPickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.many_days_picker_activity);

        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);

        calendarView.setOnForwardPageChangeListener(new OnCalendarPageChangeListener() {
            @Override
            public void onChange(Calendar newDate) {
                Toast.makeText(getApplicationContext(),
                        "New page is: " + newDate.get(Calendar.YEAR) +"" +newDate.get(Calendar.MONTH)
                        , Toast.LENGTH_SHORT).show();

            }
        });

        calendarView.setOnPreviousPageChangeListener(new OnCalendarPageChangeListener() {
            @Override
            public void onChange(Calendar newDate) {
                Toast.makeText(getApplicationContext(),
                        "New page is: " + newDate.get(Calendar.YEAR) +"" +newDate.get(Calendar.MONTH)
                        , Toast.LENGTH_SHORT).show();
            }
        });

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