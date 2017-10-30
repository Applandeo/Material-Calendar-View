package com.applandeo.materialcalendarview.utils;

import com.applandeo.materialcalendarview.listeners.OnSelectDateListener;

import java.util.Calendar;

/**
 * Created by Mateusz Kornakiewicz on 30.10.2017.
 */

public class CalendarProperties {
    private int mCalendarType;
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
    private Calendar mMinimumDate;
    private Calendar mMaximumDate;

    public int getCalendarType() {
        return mCalendarType;
    }

    public void setCalendarType(int calendarType) {
        mCalendarType = calendarType;
    }

    public Calendar getCalendar() {
        return mCalendar;
    }

    public void setCalendar(Calendar calendar) {
        mCalendar = calendar;
    }

    public OnSelectDateListener getOnSelectDateListener() {
        return mOnSelectDateListener;
    }

    public void setOnSelectDateListener(OnSelectDateListener onSelectDateListener) {
        mOnSelectDateListener = onSelectDateListener;
    }

    public int getHeaderColor() {
        return mHeaderColor;
    }

    public void setHeaderColor(int headerColor) {
        mHeaderColor = headerColor;
    }

    public int getHeaderLabelColor() {
        return mHeaderLabelColor;
    }

    public void setHeaderLabelColor(int headerLabelColor) {
        mHeaderLabelColor = headerLabelColor;
    }

    public int getPreviousButtonSrc() {
        return mPreviousButtonSrc;
    }

    public void setPreviousButtonSrc(int previousButtonSrc) {
        mPreviousButtonSrc = previousButtonSrc;
    }

    public int getForwardButtonSrc() {
        return mForwardButtonSrc;
    }

    public void setForwardButtonSrc(int forwardButtonSrc) {
        mForwardButtonSrc = forwardButtonSrc;
    }

    public int getSelectionColor() {
        return mSelectionColor;
    }

    public void setSelectionColor(int selectionColor) {
        mSelectionColor = selectionColor;
    }

    public int getTodayLabelColor() {
        return mTodayLabelColor;
    }

    public void setTodayLabelColor(int todayLabelColor) {
        mTodayLabelColor = todayLabelColor;
    }

    public int getDialogButtonsColor() {
        return mDialogButtonsColor;
    }

    public void setDialogButtonsColor(int dialogButtonsColor) {
        mDialogButtonsColor = dialogButtonsColor;
    }

    public int getCancelButtonLabel() {
        return mCancelButtonLabel;
    }

    public void setCancelButtonLabel(int cancelButtonLabel) {
        mCancelButtonLabel = cancelButtonLabel;
    }

    public int getOkButtonLabel() {
        return mOkButtonLabel;
    }

    public void setOkButtonLabel(int okButtonLabel) {
        mOkButtonLabel = okButtonLabel;
    }

    public int getMonthsNames() {
        return mMonthsNames;
    }

    public void setMonthsNames(int monthsNames) {
        mMonthsNames = monthsNames;
    }

    public int getDaysNames() {
        return mDaysNames;
    }

    public void setDaysNames(int daysNames) {
        mDaysNames = daysNames;
    }

    public Calendar getMinimumDate() {
        return mMinimumDate;
    }

    public void setMinimumDate(Calendar minimumDate) {
        mMinimumDate = minimumDate;
    }

    public Calendar getMaximumDate() {
        return mMaximumDate;
    }

    public void setMaximumDate(Calendar maximumDate) {
        mMaximumDate = maximumDate;
    }
}
