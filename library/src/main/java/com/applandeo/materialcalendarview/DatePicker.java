package com.applandeo.materialcalendarview;

import android.content.Context;
import android.support.annotation.ArrayRes;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.annimon.stream.Optional;
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener;

import java.util.Calendar;

/**
 * Created by Mateusz Kornakiewicz on 27.07.2017.
 */

public class DatePicker {

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

    public DatePicker(Context context, OnSelectDateListener onSelectDateListener) {
        mContext = context;
        mOnSelectDateListener = onSelectDateListener;
    }

    public DatePicker(Context context, OnSelectDateListener onSelectDateListener, Calendar calendar) {
        mContext = context;
        mCalendar = calendar;
        mOnSelectDateListener = onSelectDateListener;
    }

    public DatePicker setHeaderColor(@ColorRes int color) {
        mHeaderColor = color;
        return this;
    }

    public DatePicker setHeaderLabelColor(@ColorRes int color) {
        mHeaderLabelColor = color;
        return this;
    }

    public DatePicker setPreviousButtonSrc(@DrawableRes int drawable) {
        mPreviousButtonSrc = drawable;
        return this;
    }

    public DatePicker setForwardButtonSrc(@DrawableRes int drawable) {
        mForwardButtonSrc = drawable;
        return this;
    }

    public DatePicker setSelectionColor(@ColorRes int color) {
        mSelectionColor = color;
        return this;
    }

    public DatePicker setTodayLabelColor(@ColorRes int color) {
        mTodayLabelColor = color;
        return this;
    }

    public DatePicker setDialogButtonsColor(@ColorRes int color) {
        mDialogButtonsColor = color;
        return this;
    }

    public DatePicker setCancelButtonLabel(@StringRes int label) {
        mCancelButtonLabel = label;
        return this;
    }

    public DatePicker setOkButtonLabel(@StringRes int label) {
        mOkButtonLabel = label;
        return this;
    }

    public DatePicker setMonthsNames(@ArrayRes int names) {
        mMonthsNames = names;
        return this;
    }

    public DatePicker setDaysNames(@ArrayRes int names) {
        mDaysNames = names;
        return this;
    }

    public void show() {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        final View view = layoutInflater.inflate(R.layout.date_picker_dialog, null);

        CalendarView calendarView = new CalendarView(mContext);
        calendarView.datePicker(true);
        calendarView.setHeaderColor(mHeaderColor);
        calendarView.setHeaderLabelColor(mHeaderLabelColor);
        calendarView.setPreviousButtonSrc(mPreviousButtonSrc);
        calendarView.setForwardButtonSrc(mForwardButtonSrc);
        calendarView.setSelectionColor(mSelectionColor);
        calendarView.setTodayLabelColor(mTodayLabelColor);
        calendarView.setDaysNames(mDaysNames);
        calendarView.setMonthsNames(mMonthsNames);
        calendarView.create();

        FrameLayout calendarContainer = (FrameLayout) view.findViewById(R.id.calendarContainer);

        calendarContainer.addView(calendarView);

        Optional.ofNullable(mCalendar).ifPresent(calendarView::setDate);

        Button cancelButton = (Button) view.findViewById(R.id.cancel_button);
        Button okButton = (Button) view.findViewById(R.id.ok_button);

        if (mDialogButtonsColor != 0) {
            cancelButton.setTextColor(ContextCompat.getColor(mContext, mDialogButtonsColor));
            okButton.setTextColor(ContextCompat.getColor(mContext, mDialogButtonsColor));
        }

        if (mCancelButtonLabel != 0) {
            cancelButton.setText(mCancelButtonLabel);
        }

        if (mOkButtonLabel != 0) {
            okButton.setText(mOkButtonLabel);
        }

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(mContext);
        final AlertDialog alertdialog = alertBuilder.create();
        alertdialog.setView(view);

        cancelButton.setOnClickListener(v -> alertdialog.cancel());

        okButton.setOnClickListener(v -> {
            alertdialog.cancel();
            mOnSelectDateListener.onSelect(calendarView.getSelectedDate());
        });

        alertdialog.show();
    }
}
