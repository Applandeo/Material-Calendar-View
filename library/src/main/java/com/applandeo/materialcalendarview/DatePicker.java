package com.applandeo.materialcalendarview;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

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

    public DatePicker(Context context, OnSelectDateListener onSelectDateListener) {
        mContext = context;
        mOnSelectDateListener = onSelectDateListener;
    }

    public DatePicker(Context context, OnSelectDateListener onSelectDateListener, Calendar calendar) {
        mContext = context;
        mCalendar = calendar;
        mOnSelectDateListener = onSelectDateListener;
    }

    public DatePicker setHeaderColor(int color) {
        return this;
    }

    public DatePicker setHeaderLabelColor(int color) {
        return this;
    }

    public DatePicker setSelectionColor(int color) {
        return this;
    }

    public DatePicker setTodayLabelColor(int color) {
        return this;
    }

    public void show() {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        final View view = layoutInflater.inflate(R.layout.date_picker_dialog, null);

        CalendarView calendarView = (CalendarView) view.findViewById(R.id.calendarView);

        Optional.ofNullable(mCalendar).ifPresent(calendarView::setDate);

        Button cancelButton = (Button) view.findViewById(R.id.cancel_button);
        Button okButton = (Button) view.findViewById(R.id.ok_button);

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
