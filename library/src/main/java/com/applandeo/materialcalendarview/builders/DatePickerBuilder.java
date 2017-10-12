package com.applandeo.materialcalendarview.builders;

import android.content.Context;
import android.support.annotation.ArrayRes;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import com.applandeo.materialcalendarview.DatePicker;
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener;

import java.util.Calendar;

/**
 * Created by Mateusz Kornakiewicz on 12.10.2017.
 */
public class DatePickerBuilder {
    private Context mContext;
    private Calendar mCalendar;
    private OnSelectDateListener mOnSelectDateListener;
    private int mHeaderColor;
    private int mHeaderLabelColor;
    private int mPreviousButtonSrc;
    private int mForwardButtonSrc;
    private int mSelectionColor;
    private int mTodayLabelColor;
    private int mDialogButtonsColor;
    private int mCancelButtonLabel;
    private int mOkButtonLabel;
    private int mMonthsNames;
    private int mDaysNames;

    public DatePickerBuilder(Context context, OnSelectDateListener onSelectDateListener) {
        mContext = context;
        mOnSelectDateListener = onSelectDateListener;
    }

    public DatePickerBuilder date(Calendar calendar) {
        mCalendar = calendar;
        return this;
    }

    public DatePickerBuilder headerColor(@ColorRes int color) {
        mHeaderColor = color;
        return this;
    }

    public DatePickerBuilder headerLabelColor(@ColorRes int color) {
        mHeaderLabelColor = color;
        return this;
    }

    public DatePickerBuilder previousButtonSrc(@DrawableRes int drawable) {
        mPreviousButtonSrc = drawable;
        return this;
    }

    public DatePickerBuilder forwardButtonSrc(@DrawableRes int drawable) {
        mForwardButtonSrc = drawable;
        return this;
    }

    public DatePickerBuilder selectionColor(@ColorRes int color) {
        mSelectionColor = color;
        return this;
    }

    public DatePickerBuilder todayLabelColor(@ColorRes int color) {
        mTodayLabelColor = color;
        return this;
    }

    public DatePickerBuilder dialogButtonsColor(@ColorRes int color) {
        mDialogButtonsColor = color;
        return this;
    }

    public DatePickerBuilder cancelButtonLabel(@StringRes int label) {
        mCancelButtonLabel = label;
        return this;
    }

    public DatePickerBuilder okButtonLabel(@StringRes int label) {
        mOkButtonLabel = label;
        return this;
    }

    public DatePickerBuilder monthsNames(@ArrayRes int names) {
        mMonthsNames = names;
        return this;
    }

    public DatePickerBuilder daysNames(@ArrayRes int names) {
        mDaysNames = names;
        return this;
    }

    public DatePicker show() {
        return build().show();
    }

    public DatePicker build() {
        return new DatePicker(mContext, mCalendar, mOnSelectDateListener, mHeaderColor,
                mHeaderLabelColor, mPreviousButtonSrc, mForwardButtonSrc, mSelectionColor,
                mTodayLabelColor, mDialogButtonsColor, mCancelButtonLabel, mOkButtonLabel,
                mMonthsNames, mDaysNames);
    }
}
