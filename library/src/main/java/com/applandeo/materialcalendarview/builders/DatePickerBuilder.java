package com.applandeo.materialcalendarview.builders;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.DatePicker;
import com.applandeo.materialcalendarview.listeners.OnCalendarPageChangeListener;
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener;
import com.applandeo.materialcalendarview.utils.CalendarProperties;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Mateusz Kornakiewicz on 12.10.2017.
 */
public class DatePickerBuilder {
    private Context mContext;
    private CalendarProperties mCalendarProperties;

    public DatePickerBuilder(Context context, OnSelectDateListener onSelectDateListener) {
        mContext = context;
        mCalendarProperties = new CalendarProperties(context);
        mCalendarProperties.setCalendarType(CalendarView.ONE_DAY_PICKER);
        mCalendarProperties.setOnSelectDateListener(onSelectDateListener);
    }

    public DatePicker show() {
        return build().show();
    }

    public DatePicker build() {
        return new DatePicker(mContext, mCalendarProperties);
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
        mCalendarProperties.setPreviousButtonSrc(ContextCompat.getDrawable(mContext, drawable));
        return this;
    }

    public DatePickerBuilder forwardButtonSrc(@DrawableRes int drawable) {
        mCalendarProperties.setForwardButtonSrc(ContextCompat.getDrawable(mContext, drawable));
        return this;
    }

    public DatePickerBuilder selectionColor(@ColorRes int color) {
        mCalendarProperties.setSelectionColor(ContextCompat.getColor(mContext, color));
        return this;
    }

    public DatePickerBuilder todayLabelColor(@ColorRes int color) {
        mCalendarProperties.setTodayLabelColor(ContextCompat.getColor(mContext, color));
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

    public DatePickerBuilder disabledDays(List<Calendar> disabledDays) {
        mCalendarProperties.setDisabledDays(disabledDays);
        return this;
    }

    public DatePickerBuilder previousPageChangeListener(OnCalendarPageChangeListener listener) {
        mCalendarProperties.setOnPreviousPageChangeListener(listener);
        return this;
    }

    public DatePickerBuilder forwardPageChangeListener(OnCalendarPageChangeListener listener) {
        mCalendarProperties.setOnForwardPageChangeListener(listener);
        return this;
    }

    public DatePickerBuilder disabledDaysLabelsColor(@ColorRes int color) {
        mCalendarProperties.setDisabledDaysLabelsColor(ContextCompat.getColor(mContext, color));
        return this;
    }

    public DatePickerBuilder abbreviationsBarColor(@ColorRes int color) {
        mCalendarProperties.setAbbreviationsBarColor(ContextCompat.getColor(mContext, color));
        return this;
    }

    public DatePickerBuilder pagesColor(@ColorRes int color) {
        mCalendarProperties.setPagesColor(ContextCompat.getColor(mContext, color));
        return this;
    }

    public DatePickerBuilder abbreviationsLabelsColor(@ColorRes int color) {
        mCalendarProperties.setAbbreviationsLabelsColor(ContextCompat.getColor(mContext, color));
        return this;
    }

    public DatePickerBuilder daysLabelsColor(int color) {
        mCalendarProperties.setDaysLabelsColor(ContextCompat.getColor(mContext, color));
        return this;
    }

    public DatePickerBuilder selectionLabelColor(int color) {
        mCalendarProperties.setSelectionLabelColor(ContextCompat.getColor(mContext, color));
        return this;
    }

    public DatePickerBuilder anotherMonthsDaysLabelsColor(int color) {
        mCalendarProperties.setAnotherMonthsDaysLabelsColor(ContextCompat.getColor(mContext, color));
        return this;
    }
}
