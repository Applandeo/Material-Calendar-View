package com.applandeo.materialcalendarsampleapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.applandeo.materialcalendarsampleapp.utils.DrawableUtils;
import com.applandeo.materialcalendarview.CalendarDay;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.applandeo.materialcalendarview.utils.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * Created by Mateusz Kornakiewicz on 26.05.2017.
 */

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_activity);

        List<CalendarDay> events = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        CalendarDay calendarDay = new CalendarDay(calendar);
        calendarDay.setImageDrawable(DrawableUtils.getCircleDrawableWithText(this, "M"));
        events.add(calendarDay);

        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DAY_OF_MONTH, 10);
        CalendarDay calendarDay1 = new CalendarDay(calendar1);
        calendarDay1.setImageResource(R.drawable.sample_icon_2);
        events.add(calendarDay1);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(Calendar.DAY_OF_MONTH, 10);
        CalendarDay calendarDay2 = new CalendarDay(calendar2);
        calendarDay2.setImageResource(R.drawable.sample_icon_3);
        events.add(calendarDay2);

        Calendar calendar3 = Calendar.getInstance();
        calendar3.add(Calendar.DAY_OF_MONTH, 7);
        CalendarDay calendarDay3 = new CalendarDay(calendar3);
        calendarDay3.setImageResource(R.drawable.sample_four_icons);
        events.add(calendarDay3);

        Calendar calendar4 = Calendar.getInstance();
        calendar4.add(Calendar.DAY_OF_MONTH, 13);
        CalendarDay calendarDay4 = new CalendarDay(calendar4);
        calendarDay4.setImageDrawable(DrawableUtils.getThreeDots(this));
        events.add(calendarDay4);

        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);

        Calendar min = Calendar.getInstance();
        min.add(Calendar.MONTH, -2);

        Calendar max = Calendar.getInstance();
        max.add(Calendar.MONTH, 2);

        calendarView.setMinimumDate(min);
        calendarView.setMaximumDate(max);

        calendarView.setCalendarDays(events);

        calendarView.setDisabledDays(getDisabledDays());

        calendarView.setOnDayClickListener(eventDay ->
                Toast.makeText(getApplicationContext(),
                        eventDay.getCalendar().getTime().toString() + " "
                                + eventDay.isEnabled(),
                        Toast.LENGTH_SHORT).show());

        Button setDateButton = (Button) findViewById(R.id.setDateButton);
        setDateButton.setOnClickListener(v -> {
            try {
                Calendar randomCalendar = getRandomCalendar();
                String text = randomCalendar.getTime().toString();
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
                calendarView.setDate(randomCalendar);
            } catch (OutOfDateRangeException exception) {
                exception.printStackTrace();

                Toast.makeText(getApplicationContext(),
                        "Date is out of range",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private List<Calendar> getDisabledDays() {
        Calendar firstDisabled = DateUtils.getMidnightCalendar();
        firstDisabled.add(Calendar.DAY_OF_MONTH, 2);

        Calendar secondDisabled = DateUtils.getMidnightCalendar();
        secondDisabled.add(Calendar.DAY_OF_MONTH, 1);

        Calendar thirdDisabled = DateUtils.getMidnightCalendar();
        thirdDisabled.add(Calendar.DAY_OF_MONTH, 18);

        List<Calendar> calendars = new ArrayList<>();
        calendars.add(firstDisabled);
        calendars.add(secondDisabled);
        calendars.add(thirdDisabled);
        return calendars;
    }

    private Calendar getRandomCalendar() {
        Random random = new Random();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, random.nextInt(99));

        return calendar;
    }
}