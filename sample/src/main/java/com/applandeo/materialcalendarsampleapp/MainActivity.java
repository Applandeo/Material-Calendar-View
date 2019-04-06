package com.applandeo.materialcalendarsampleapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
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
                .pickerType(CalendarView.ONE_DAY_PICKER)
                .date(max)
                .headerColor(R.color.colorPrimaryDark)
                .headerLabelColor(R.color.currentMonthDayColor)
                .selectionColor(R.color.daysLabelColor)
                .todayLabelColor(R.color.colorAccent)
                .dialogButtonsColor(android.R.color.holo_green_dark)
                .disabledDaysLabelsColor(android.R.color.holo_purple)
                .previousButtonSrc(R.drawable.ic_chevron_left_black_24dp)
                .forwardButtonSrc(R.drawable.ic_chevron_right_black_24dp)
                .minimumDate(min)
                .maximumDate(max)
                .disabledDays(getDisabledDays());

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
                .pickerType(CalendarView.MANY_DAYS_PICKER)
                .headerColor(android.R.color.holo_green_dark)
                .selectionColor(android.R.color.holo_green_dark)
                .todayLabelColor(android.R.color.holo_green_dark)
                .dialogButtonsColor(android.R.color.holo_green_dark)
                .selectedDays(selectedDays)
                .disabledDays(getDisabledDays());

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
                .pickerType(CalendarView.RANGE_PICKER)
                .headerColor(R.color.sampleDark)
                .abbreviationsBarColor(R.color.sampleLight)
                .abbreviationsLabelsColor(android.R.color.white)
                .pagesColor(R.color.sampleLighter)
                .selectionColor(android.R.color.white)
                .selectionLabelColor(R.color.sampleDark)
                .todayLabelColor(R.color.dialogAccent)
                .dialogButtonsColor(android.R.color.white)
                .daysLabelsColor(android.R.color.white)
                .anotherMonthsDaysLabelsColor(R.color.sampleLighter)
                .selectedDays(selectedDays)
                .disabledDays(getDisabledDays());

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
