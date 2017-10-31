package com.applandeo.materialcalendarview.builders;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.listeners.OnSelectionAbilityListener;
import com.applandeo.materialcalendarview.utils.CalendarProperties;

import java.util.Calendar;

/**
 * This class is using to create CalendarView instance
 * Created by Mateusz Kornakiewicz on 12.10.2017.
 */

public class CalendarBuilder {
    private Context mContext;
    private CalendarProperties mCalendarProperties;

    public CalendarBuilder(Context context) {
        mContext = context;
        mCalendarProperties = new CalendarProperties();
        mCalendarProperties.setCalendarType(CalendarView.CLASSIC);
    }

    private CalendarView build() {
        return new CalendarView(mContext, mCalendarProperties);
    }

    @Deprecated
    public CalendarBuilder datePicker(boolean isDatePicker) {
        if (isDatePicker) {
            mCalendarProperties.setCalendarType(CalendarView.ONE_DAY_PICKER);
        }

        return this;
    }

    public CalendarBuilder setType(int calendarType) {
        mCalendarProperties.setCalendarType(calendarType);
        return this;
    }

    public CalendarBuilder headerColor(@ColorRes int color) {
        mCalendarProperties.setHeaderColor(color);
        return this;
    }

    public CalendarBuilder headerLabelColor(@ColorRes int color) {
        mCalendarProperties.setHeaderLabelColor(color);
        return this;
    }

    public CalendarBuilder previousButtonSrc(@DrawableRes int drawable) {
        mCalendarProperties.setPreviousButtonSrc(drawable);
        return this;
    }

    public CalendarBuilder forwardButtonSrc(@DrawableRes int drawable) {
        mCalendarProperties.setForwardButtonSrc(drawable);
        return this;
    }

    public CalendarBuilder selectionColor(@ColorRes int color) {
        mCalendarProperties.setSelectionColor(color);
        return this;
    }

    public CalendarBuilder todayLabelColor(@ColorRes int color) {
        mCalendarProperties.setTodayLabelColor(color);
        return this;
    }

    public CalendarBuilder minimumDate(Calendar calendar) {
        mCalendarProperties.setMinimumDate(calendar);
        return this;
    }

    public CalendarBuilder maximumDate(Calendar calendar) {
        mCalendarProperties.setMaximumDate(calendar);
        return this;
    }

    public CalendarBuilder selectionAbilityListener(OnSelectionAbilityListener onSelectionAbilityListener) {
        mCalendarProperties.setOnSelectionAbilityListener(onSelectionAbilityListener);
        return this;
    }

    public CalendarView create() {
        return build().create();
    }
}
