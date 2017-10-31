package com.applandeo.materialcalendarview.builders;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.DatePicker;
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener;
import com.applandeo.materialcalendarview.utils.CalendarProperties;

import java.util.Calendar;

/**
 * Created by Mateusz Kornakiewicz on 12.10.2017.
 */
public class DatePickerBuilder {
    private Context mContext;
    private CalendarProperties mCalendarProperties;

    public DatePickerBuilder(Context context, OnSelectDateListener onSelectDateListener) {
        mContext = context;
        mCalendarProperties = new CalendarProperties();
        mCalendarProperties.setCalendarType(CalendarView.ONE_DAY_PICKER);
        mCalendarProperties.setOnSelectDateListener(onSelectDateListener);
    }

    public DatePickerBuilder pickerType(int calendarType) {
        mCalendarProperties.setCalendarType(calendarType);
        return this;
    }

    public DatePickerBuilder date(Calendar calendar) {
        mCalendarProperties.setCalendar(calendar);
        return this;
    }

    public DatePickerBuilder headerColor(@ColorRes int color) {
        mCalendarProperties.setHeaderColor(color);
        return this;
    }

    public DatePickerBuilder headerLabelColor(@ColorRes int color) {
        mCalendarProperties.setHeaderLabelColor(color);
        return this;
    }

    public DatePickerBuilder previousButtonSrc(@DrawableRes int drawable) {
        mCalendarProperties.setPreviousButtonSrc(drawable);
        return this;
    }

    public DatePickerBuilder forwardButtonSrc(@DrawableRes int drawable) {
        mCalendarProperties.setForwardButtonSrc(drawable);
        return this;
    }

    public DatePickerBuilder selectionColor(@ColorRes int color) {
        mCalendarProperties.setSelectionColor(color);
        return this;
    }

    public DatePickerBuilder todayLabelColor(@ColorRes int color) {
        mCalendarProperties.setTodayLabelColor(color);
        return this;
    }

    public DatePickerBuilder dialogButtonsColor(@ColorRes int color) {
        mCalendarProperties.setDialogButtonsColor(color);
        return this;
    }

    public DatePickerBuilder minimumDate(Calendar calendar) {
        mCalendarProperties.setMinimumDate(calendar);
        return this;
    }

    public DatePickerBuilder maximumDate(Calendar calendar) {
        mCalendarProperties.setMaximumDate(calendar);
        return this;
    }

    public DatePicker show() {
        return build().show();
    }

    public DatePicker build() {
        return new DatePicker(mContext, mCalendarProperties);
    }
}
