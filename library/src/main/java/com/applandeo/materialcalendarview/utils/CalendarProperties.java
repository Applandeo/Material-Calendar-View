package com.applandeo.materialcalendarview.utils;

import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.applandeo.materialcalendarview.listeners.OnNavigationButtonClickListener;
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener;
import com.applandeo.materialcalendarview.listeners.OnSelectionAbilityListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * This class contains all properties of the calendar
 * <p>
 * Created by Mateusz Kornakiewicz on 30.10.2017.
 */

public class CalendarProperties {
    private int mCalendarType, mHeaderColor, mHeaderLabelColor, mPreviousButtonSrc, mForwardButtonSrc,
            mSelectionColor, mTodayLabelColor, mDialogButtonsColor, mItemLayoutResource;

    private Calendar mCurrentDate = DateUtils.getCalendar();
    private Calendar mSelectedDate = DateUtils.getCalendar();
    private Calendar mCalendar, mMinimumDate, mMaximumDate;

    private OnDayClickListener mOnDayClickListener;
    private OnSelectDateListener mOnSelectDateListener;
    private OnSelectionAbilityListener mOnSelectionAbilityListener;
    private OnNavigationButtonClickListener mOnPreviousButtonClickListener;
    private OnNavigationButtonClickListener mOnForwardButtonClickListener;

    private List<EventDay> mEventDays = new ArrayList<>();

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

    public OnSelectionAbilityListener getOnSelectionAbilityListener() {
        return mOnSelectionAbilityListener;
    }

    public void setOnSelectionAbilityListener(OnSelectionAbilityListener onSelectionAbilityListener) {
        mOnSelectionAbilityListener = onSelectionAbilityListener;
    }

    public int getItemLayoutResource() {
        return mItemLayoutResource;
    }

    public void setItemLayoutResource(int itemLayoutResource) {
        mItemLayoutResource = itemLayoutResource;
    }

    public OnNavigationButtonClickListener getOnPreviousButtonClickListener() {
        return mOnPreviousButtonClickListener;
    }

    public void setOnPreviousButtonClickListener(OnNavigationButtonClickListener onPreviousButtonClickListener) {
        mOnPreviousButtonClickListener = onPreviousButtonClickListener;
    }

    public OnNavigationButtonClickListener getOnForwardButtonClickListener() {
        return mOnForwardButtonClickListener;
    }

    public void setOnForwardButtonClickListener(OnNavigationButtonClickListener onForwardButtonClickListener) {
        mOnForwardButtonClickListener = onForwardButtonClickListener;
    }

    public Calendar getCurrentDate() {
        return mCurrentDate;
    }

    public Calendar getSelectedDate() {
        return mSelectedDate;
    }

    public OnDayClickListener getOnDayClickListener() {
        return mOnDayClickListener;
    }

    public void setOnDayClickListener(OnDayClickListener onDayClickListener) {
        mOnDayClickListener = onDayClickListener;
    }

    public List<EventDay> getEventDays() {
        return mEventDays;
    }

    public void setEventDays(List<EventDay> eventDays) {
        mEventDays = eventDays;
    }
}
