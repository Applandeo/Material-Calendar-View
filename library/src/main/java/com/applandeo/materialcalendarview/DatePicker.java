package com.applandeo.materialcalendarview;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.annimon.stream.Optional;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.applandeo.materialcalendarview.utils.CalendarProperties;
import com.applandeo.materialcalendarview.utils.DateUtils;

/**
 * This class is responsible for creating DatePicker dialog.
 * <p>
 * Created by Mateusz Kornakiewicz on 27.07.2017.
 */

public class DatePicker {
    private final Context mContext;
    private CalendarProperties mCalendarProperties;

    private AppCompatButton mCancelButton;
    private AppCompatButton mOkButton;
    private AppCompatButton mTodayButton;

    public DatePicker(Context context, CalendarProperties calendarProperties) {
        mContext = context;
        mCalendarProperties = calendarProperties;
    }

    public DatePicker show() {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        final View view = layoutInflater.inflate(R.layout.date_picker_dialog, null);

        if(mCalendarProperties.getPagesColor() != 0){
            view.setBackgroundColor(mCalendarProperties.getPagesColor());
        }

        mCancelButton = (AppCompatButton) view.findViewById(R.id.negative_button);
        mOkButton = (AppCompatButton) view.findViewById(R.id.positive_button);
        mTodayButton = (AppCompatButton) view.findViewById(R.id.today_button);

        setTodayButtonVisibility();
        setDialogButtonsColors();
        setOkButtonState(mCalendarProperties.getCalendarType() == CalendarView.ONE_DAY_PICKER);
        mCalendarProperties.setOnSelectionAbilityListener(this::setOkButtonState);

        CalendarView calendarView = new CalendarView(mContext, mCalendarProperties);

        FrameLayout calendarContainer = (FrameLayout) view.findViewById(R.id.calendarContainer);
        calendarContainer.addView(calendarView);

        Optional.ofNullable(mCalendarProperties.getCalendar()).ifPresent(calendar -> {
            try {
                calendarView.setDate(calendar);
            } catch (OutOfDateRangeException exception) {
                exception.printStackTrace();
            }
        });

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(mContext);
        final AlertDialog alertdialog = alertBuilder.create();
        alertdialog.setView(view);

        mCancelButton.setOnClickListener(v -> alertdialog.cancel());

        mOkButton.setOnClickListener(v -> {
            alertdialog.cancel();
            mCalendarProperties.getOnSelectDateListener().onSelect(calendarView.getSelectedDates());
        });

        mTodayButton.setOnClickListener(v -> calendarView.showCurrentMonthPage());

        alertdialog.show();

        return this;
    }

    private void setDialogButtonsColors() {
        if (mCalendarProperties.getDialogButtonsColor() != 0) {
            mCancelButton.setTextColor(ContextCompat.getColor(mContext, mCalendarProperties.getDialogButtonsColor()));
            mTodayButton.setTextColor(ContextCompat.getColor(mContext, mCalendarProperties.getDialogButtonsColor()));
        }
    }

    private void setOkButtonState(boolean enabled) {
        mOkButton.setEnabled(enabled);

        if (mCalendarProperties.getDialogButtonsColor() != 0) {
            mOkButton.setTextColor(ContextCompat.getColor(mContext, enabled
                    ? mCalendarProperties.getDialogButtonsColor() : R.color.disabledDialogButtonColor));
        }
    }

    private void setTodayButtonVisibility() {
        if (DateUtils.isMonthAfter(mCalendarProperties.getMaximumDate(), DateUtils.getCalendar())
                || DateUtils.isMonthBefore(mCalendarProperties.getMinimumDate(), DateUtils.getCalendar())) {
            mTodayButton.setVisibility(View.GONE);
        }
    }
}
