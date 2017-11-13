package com.applandeo.materialcalendarsampleapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.DatePicker;
import com.applandeo.materialcalendarview.builders.DatePickerBuilder;
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener;

import java.util.Calendar;
import java.util.List;

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


        Button openOneDayPicker = (Button) findViewById(R.id.openOneDayPickerButton);
        openOneDayPicker.setOnClickListener(v -> startActivity(new Intent(this, OneDayPickerActivity.class)));

        Button openManyDaysPicker = (Button) findViewById(R.id.openManyDayPickerButton);
        openManyDaysPicker.setOnClickListener(v -> startActivity(new Intent(this, ManyDaysPickerActivity.class)));

        Button openRangePicker = (Button) findViewById(R.id.openRangePickerButton);
        openRangePicker.setOnClickListener(v -> startActivity(new Intent(this, RangePickerActivity.class)));

        Calendar min = Calendar.getInstance();
        min.add(Calendar.MONTH, -5);

        Calendar max = Calendar.getInstance();
        max.add(Calendar.MONTH, -3);

        DatePickerBuilder oneDayBuilder = new DatePickerBuilder(this, this)
                .pickerType(CalendarView.ONE_DAY_PICKER)
                .date(max)
                .headerColor(R.color.colorPrimaryDark)
                .headerLabelColor(R.color.currentMonthDayColor)
                .selectionColor(R.color.daysLabelColor)
                .todayLabelColor(R.color.colorAccent)
                .dialogButtonsColor(android.R.color.holo_green_dark)
                .previousButtonSrc(R.drawable.ic_chevron_left_black_24dp)
                .forwardButtonSrc(R.drawable.ic_chevron_right_black_24dp)
                .minimumDate(min)
                .maximumDate(max);

        DatePicker oneDayPicker = oneDayBuilder.build();

        Button openOneDayPickerDialog = (Button) findViewById(R.id.openOneDayPickerDialogButton);
        openOneDayPickerDialog.setOnClickListener(v -> oneDayPicker.show());

        DatePickerBuilder manyDaysBuilder = new DatePickerBuilder(this, this)
                .pickerType(CalendarView.MANY_DAYS_PICKER)
                .headerColor(android.R.color.holo_green_dark)
                .selectionColor(android.R.color.holo_green_dark)
                .todayLabelColor(android.R.color.holo_green_dark)
                .dialogButtonsColor(android.R.color.holo_green_dark);

        DatePicker manyDayPicker = manyDaysBuilder.build();

        Button openManyDaysPickerDialog = (Button) findViewById(R.id.openManyDaysPickerDialogButton);
        openManyDaysPickerDialog.setOnClickListener(v -> manyDayPicker.show());

        DatePickerBuilder rangeBuilder = new DatePickerBuilder(this, this)
                .pickerType(CalendarView.RANGE_PICKER)
                .headerColor(android.R.color.holo_green_dark)
                .selectionColor(android.R.color.holo_green_dark)
                .todayLabelColor(android.R.color.holo_green_dark)
                .dialogButtonsColor(android.R.color.holo_green_dark);

        DatePicker rangePicker = rangeBuilder.build();

        Button openRangePickerDialog = (Button) findViewById(R.id.openRangePickerDialogButton);
        openRangePickerDialog.setOnClickListener(v -> rangePicker.show());
    }

    @Override
    public void onSelect(List<Calendar> calendars) {
        for (Calendar calendar :
                calendars) {
            Toast.makeText(getApplicationContext(),
                    calendar.getTime().toString(),
                    Toast.LENGTH_SHORT).show();
        }
    }
}
