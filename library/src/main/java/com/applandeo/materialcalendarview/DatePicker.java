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
import com.applandeo.materialcalendarview.utils.CalendarProperties;

/**
 * This class is responsible for creating DatePicker dialog.
 * <p>
 * Created by Mateusz Kornakiewicz on 27.07.2017.
 */

public class DatePicker {
    private final Context mContext;
    private CalendarProperties mCalendarProperties;

    public DatePicker(Context context, CalendarProperties calendarProperties) {
        mContext = context;
        mCalendarProperties = calendarProperties;
    }

    public DatePicker show() {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        final View view = layoutInflater.inflate(R.layout.date_picker_dialog, null);

        AppCompatButton cancelButton = (AppCompatButton) view.findViewById(R.id.cancel_button);
        AppCompatButton okButton = (AppCompatButton) view.findViewById(R.id.ok_button);

        okButton.setEnabled(mCalendarProperties.getCalendarType() == CalendarView.ONE_DAY_PICKER);
        setDialogButtonsColors(cancelButton, okButton);

        CalendarView calendarView = new CalendarBuilder(mContext)
                .setType(mCalendarProperties.getCalendarType())
                .headerColor(mCalendarProperties.getHeaderColor())
                .headerLabelColor(mCalendarProperties.getHeaderLabelColor())
                .previousButtonSrc(mCalendarProperties.getPreviousButtonSrc())
                .forwardButtonSrc(mCalendarProperties.getForwardButtonSrc())
                .selectionColor(mCalendarProperties.getSelectionColor())
                .todayLabelColor(mCalendarProperties.getTodayLabelColor())
                .daysNames(mCalendarProperties.getDaysNames())
                .monthsNames(mCalendarProperties.getMonthsNames())
                .minimumDate(mCalendarProperties.getMinimumDate())
                .maximumDate(mCalendarProperties.getMaximumDate())
                .selectionAbilityListener(enabled -> {
                    okButton.setEnabled(enabled);
                    setDialogButtonsColors(cancelButton, okButton);
                })
                .create();

        FrameLayout calendarContainer = (FrameLayout) view.findViewById(R.id.calendarContainer);
        calendarContainer.addView(calendarView);

        Optional.ofNullable(mCalendarProperties.getCalendar()).ifPresent(calendar -> {
            try {
                calendarView.setDate(calendar);
            } catch (OutOfDateRangeException exception) {
                exception.printStackTrace();
            }
        });

        if (mCalendarProperties.getCancelButtonLabel() != 0) {
            cancelButton.setText(mCalendarProperties.getCancelButtonLabel());
        }

        if (mCalendarProperties.getOkButtonLabel() != 0) {
            okButton.setText(mCalendarProperties.getOkButtonLabel());
        }

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(mContext);
        final AlertDialog alertdialog = alertBuilder.create();
        alertdialog.setView(view);

        cancelButton.setOnClickListener(v -> alertdialog.cancel());

        okButton.setOnClickListener(v -> {
            alertdialog.cancel();
            mCalendarProperties.getOnSelectDateListener().onSelect(calendarView.getSelectedDates());
        });

        alertdialog.show();

        return this;
    }

    private void setDialogButtonsColors(AppCompatButton cancelButton, AppCompatButton okButton) {
        if (mCalendarProperties.getDialogButtonsColor() != 0) {
            cancelButton.setTextColor(ContextCompat.getColor(mContext, mCalendarProperties.getDialogButtonsColor()));

            okButton.setTextColor(ContextCompat.getColor(mContext, okButton.isEnabled()
                    ? mCalendarProperties.getDialogButtonsColor() : R.color.disabledDialogButtonColor));
        }
    }
}
