package com.applandeo.materialcalendarsampleapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.annimon.stream.Stream;
import com.applandeo.materialcalendarview.CalendarUtils;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.DatePicker;
import com.applandeo.materialcalendarview.builders.DatePickerBuilder;
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener;
import com.applandeo.materialcalendarview.utils.DateUtils;

import java.util.ArrayList;
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

        Button openOneDayPickerDialog = (Button) findViewById(R.id.openOneDayPickerDialogButton);
        openOneDayPickerDialog.setOnClickListener(v -> openOneDayPicker());

        Button openManyDaysPickerDialog = (Button) findViewById(R.id.openManyDaysPickerDialogButton);
        openManyDaysPickerDialog.setOnClickListener(v -> openManyDaysPicker());

        Button openRangePickerDialog = (Button) findViewById(R.id.openRangePickerDialogButton);
        openRangePickerDialog.setOnClickListener(v -> openRangePicker());
    }

    private void openOneDayPicker() {
        Calendar min = Calendar.getInstance();
        min.add(Calendar.MONTH, -5);

        Calendar max = Calendar.getInstance();
        max.add(Calendar.DAY_OF_MONTH, 3);

        DatePickerBuilder oneDayBuilder = new DatePickerBuilder(this, this)
                .setPickerType(CalendarView.ONE_DAY_PICKER)
                .setDate(max)
                .setHeaderColor(R.color.colorPrimaryDark)
                .setHeaderLabelColor(R.color.currentMonthDayColor)
                .setSelectionColor(R.color.daysLabelColor)
                .setTodayLabelColor(R.color.colorAccent)
                .setDialogButtonsColor(android.R.color.holo_green_dark)
                .setDisabledDaysLabelsColor(android.R.color.holo_purple)
                .setPreviousButtonSrc(R.drawable.ic_chevron_left_black_24dp)
                .setForwardButtonSrc(R.drawable.ic_chevron_right_black_24dp)
                .setMinimumDate(min)
                .setMaximumDate(max)
                .setTodayColor(R.color.sampleLighter)
                .setHeaderVisibility(View.VISIBLE)
                .setDisabledDays(getDisabledDays());

        DatePicker oneDayPicker = oneDayBuilder.build();
        oneDayPicker.show();
    }

    private void openManyDaysPicker() {
        Calendar min = Calendar.getInstance();
        min.add(Calendar.DAY_OF_MONTH, -5);

        Calendar max = Calendar.getInstance();
        max.add(Calendar.DAY_OF_MONTH, 3);

        List<Calendar> selectedDays = new ArrayList<>(getDisabledDays());
        selectedDays.add(min);
        selectedDays.add(max);

        DatePickerBuilder manyDaysBuilder = new DatePickerBuilder(this, this)
                .setPickerType(CalendarView.MANY_DAYS_PICKER)
                .setHeaderColor(android.R.color.holo_green_dark)
                .setSelectionColor(android.R.color.holo_green_dark)
                .setTodayLabelColor(android.R.color.holo_green_dark)
                .setDialogButtonsColor(android.R.color.holo_green_dark)
                .setSelectedDays(selectedDays)
                .setNavigationVisibility(View.GONE)
                .setDisabledDays(getDisabledDays());

        DatePicker manyDaysPicker = manyDaysBuilder.build();
        manyDaysPicker.show();
    }

    private void openRangePicker() {
        Calendar min = Calendar.getInstance();
        min.add(Calendar.DAY_OF_MONTH, -5);

        Calendar max = Calendar.getInstance();
        max.add(Calendar.DAY_OF_MONTH, 3);

        List<Calendar> selectedDays = new ArrayList<>();
        selectedDays.add(min);
        selectedDays.addAll(CalendarUtils.getDatesRange(min, max));
        selectedDays.add(max);

        DatePickerBuilder rangeBuilder = new DatePickerBuilder(this, this)
                .setPickerType(CalendarView.RANGE_PICKER)
                .setHeaderColor(R.color.sampleDark)
                .setAbbreviationsBarColor(R.color.sampleLight)
                .setAbbreviationsLabelsColor(android.R.color.white)
                .setPagesColor(R.color.sampleLighter)
                .setSelectionColor(android.R.color.white)
                .setSelectionLabelColor(R.color.sampleDark)
                .setTodayLabelColor(R.color.dialogAccent)
                .setDialogButtonsColor(android.R.color.white)
                .setDaysLabelsColor(android.R.color.white)
                .setAnotherMonthsDaysLabelsColor(R.color.sampleLighter)
                .setSelectedDays(selectedDays)
                .setMaximumDaysRange(10)
                .setDisabledDays(getDisabledDays());

        DatePicker rangePicker = rangeBuilder.build();
        rangePicker.show();
    }

    private List<Calendar> getDisabledDays() {
        Calendar firstDisabled = DateUtils.getCalendar();
        firstDisabled.add(Calendar.DAY_OF_MONTH, 2);

        Calendar secondDisabled = DateUtils.getCalendar();
        secondDisabled.add(Calendar.DAY_OF_MONTH, 1);

        Calendar thirdDisabled = DateUtils.getCalendar();
        thirdDisabled.add(Calendar.DAY_OF_MONTH, 18);

        List<Calendar> calendars = new ArrayList<>();
        calendars.add(firstDisabled);
        calendars.add(secondDisabled);
        calendars.add(thirdDisabled);
        return calendars;
    }

    @Override
    public void onSelect(List<Calendar> calendars) {
        Stream.of(calendars).forEach(calendar ->
                Toast.makeText(getApplicationContext(),
                        calendar.getTime().toString(),
                        Toast.LENGTH_SHORT).show());
    }
}
