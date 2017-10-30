package com.applandeo.materialcalendarview;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.annimon.stream.Optional;
import com.applandeo.materialcalendarview.builders.CalendarBuilder;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener;

import java.util.Calendar;

/**
 * This class is responsible for creating DatePicker dialog.
 * <p>
 * Created by Mateusz Kornakiewicz on 27.07.2017.
 */

public class DatePicker {
    private final Context mContext;
    private final int mCalendarType;
    private final Calendar mCalendar;
    private final OnSelectDateListener mOnSelectDateListener;
    private final int mHeaderColor;
    private final int mHeaderLabelColor;
    private final int mPreviousButtonSrc;
    private final int mForwardButtonSrc;
    private final int mSelectionColor;
    private final int mTodayLabelColor;
    private final int mDialogButtonsColor;
    private final int mCancelButtonLabel;
    private final int mOkButtonLabel;
    private final int mMonthsNames;
    private final int mDaysNames;
    private Calendar mMinimumDate;
    private Calendar mMaximumDate;

    public DatePicker(Context context, int calendarType, Calendar calendar, OnSelectDateListener onSelectDateListener,
                      int headerColor, int headerLabelColor, int previousButtonSrc, int forwardButtonSrc,
                      int selectionColor, int todayLabelColor, int dialogButtonsColor, int cancelButtonLabel,
                      int okButtonLabel, int monthsNames, int daysNames, Calendar minimumDate, Calendar maximumDate) {
        mContext = context;
        mCalendarType = calendarType;
        mCalendar = calendar;
        mOnSelectDateListener = onSelectDateListener;
        mHeaderColor = headerColor;
        mHeaderLabelColor = headerLabelColor;
        mPreviousButtonSrc = previousButtonSrc;
        mForwardButtonSrc = forwardButtonSrc;
        mSelectionColor = selectionColor;
        mTodayLabelColor = todayLabelColor;
        mDialogButtonsColor = dialogButtonsColor;
        mCancelButtonLabel = cancelButtonLabel;
        mOkButtonLabel = okButtonLabel;
        mMonthsNames = monthsNames;
        mDaysNames = daysNames;
        mMinimumDate = minimumDate;
        mMaximumDate = maximumDate;
    }

    public DatePicker show() {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        final View view = layoutInflater.inflate(R.layout.date_picker_dialog, null);

        AppCompatButton cancelButton = (AppCompatButton) view.findViewById(R.id.cancel_button);
        AppCompatButton okButton = (AppCompatButton) view.findViewById(R.id.ok_button);

        okButton.setEnabled(mCalendarType == CalendarView.ONE_DAY_PICKER);
        setDialogButtonsColors(cancelButton, okButton);

        CalendarView calendarView = new CalendarBuilder(mContext)
                .setType(mCalendarType)
                .headerColor(mHeaderColor)
                .headerLabelColor(mHeaderLabelColor)
                .previousButtonSrc(mPreviousButtonSrc)
                .forwardButtonSrc(mForwardButtonSrc)
                .selectionColor(mSelectionColor)
                .todayLabelColor(mTodayLabelColor)
                .daysNames(mDaysNames)
                .monthsNames(mMonthsNames)
                .minimumDate(mMinimumDate)
                .maximumDate(mMaximumDate)
                .selectionAbilityListener(enabled -> {
                    okButton.setEnabled(enabled);
                    setDialogButtonsColors(cancelButton, okButton);
                })
                .create();

        FrameLayout calendarContainer = (FrameLayout) view.findViewById(R.id.calendarContainer);
        calendarContainer.addView(calendarView);

        Optional.ofNullable(mCalendar).ifPresent(calendar -> {
            try {
                calendarView.setDate(calendar);
            } catch (OutOfDateRangeException exception) {
                exception.printStackTrace();
            }
        });

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
            mOnSelectDateListener.onSelect(calendarView.getSelectedDates());
        });

        alertdialog.show();

        return this;
    }

    private void setDialogButtonsColors(AppCompatButton cancelButton, AppCompatButton okButton) {
        if (mDialogButtonsColor != 0) {
            cancelButton.setTextColor(ContextCompat.getColor(mContext, mDialogButtonsColor));

            okButton.setTextColor(ContextCompat.getColor(mContext,
                    okButton.isEnabled() ? mDialogButtonsColor : R.color.disabledDialogButtonColor));
        }
    }
}
