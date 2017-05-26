package com.applandeo.materialcalendarview.utils;

import android.view.View;

import java.util.Calendar;

/**
 * This helper class represent a selected day when calendar is in a picker date mode.
 * It is used to remember a selected calendar cell.
 * <p>
 * Created by Mateusz Kornakiewicz on 23.05.2017.
 */

public class SelectedDay {
    private View mView;
    private Calendar mCalendar;

    /**
     * @param view     View representing selected calendar cell
     * @param calendar Calendar instance representing selected cell date
     */
    public SelectedDay(View view, Calendar calendar) {
        mView = view;
        mCalendar = calendar;
    }

    /**
     * @return View representing selected calendar cell
     */
    public View getView() {
        return mView;
    }

    /**
     * @return Calendar instance representing selected cell date
     */
    public Calendar getCalendar() {
        return mCalendar;
    }
}

