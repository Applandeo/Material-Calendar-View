package com.applandeo.materialcalendarsampleapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.utils.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Mateusz Kornakiewicz on 23.10.2017.
 */

public class ManyDaysPickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.many_days_picker_activity);

        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);

        calendarView.setOnForwardPageChangeListener(() ->
                Toast.makeText(getApplicationContext(), "Forward", Toast.LENGTH_SHORT).show());

        calendarView.setOnPreviousPageChangeListener(() ->
                Toast.makeText(getApplicationContext(), "Previous", Toast.LENGTH_SHORT).show());

        calendarView.setSelectedDates(getSelectedDays());

        List<EventDay> events = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 7);
        events.add(new EventDay(cal, R.drawable.sample_four_icons));

        calendarView.setEvents(events);

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

    private List<Calendar> getSelectedDays() {
        List<Calendar> calendars = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Calendar calendar = DateUtils.getCalendar();
            calendar.add(Calendar.DAY_OF_MONTH, i);
            calendars.add(calendar);
        }

        return calendars;
    }
}