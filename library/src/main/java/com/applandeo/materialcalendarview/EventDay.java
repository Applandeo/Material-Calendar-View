package com.applandeo.materialcalendarview;

import android.support.annotation.RestrictTo;

import com.applandeo.materialcalendarview.utils.DateUtils;

import java.util.Calendar;

/**
 * This class represents an event of a day. An instance of this class is returned when user click
 * a day cell. This class can be overridden to make calendar more functional. A list of instances of
 * this class can be passed to CalendarView object using setEvents() method.
 * <p>
 * Created by Mateusz Kornakiewicz on 23.05.2017.
 */

public class EventDay {
    private Calendar mDay;
    private int mImageResource;
    private boolean mIsDisabled;

    /**
     * @param day Calendar object which represents a date of the event
     */
    public EventDay(Calendar day) {
        mDay = day;
    }


    /**
     * @param day           Calendar object which represents a date of the event
     * @param imageResource Resource of an image which will be displayed in a day cell
     */
    public EventDay(Calendar day, int imageResource) {
        DateUtils.setMidnight(day);
        mDay = day;
        mImageResource = imageResource;
    }


    /**
     * @return An image resource which will be displayed in the day row
     */
    public int getImageResource() {
        return mImageResource;
    }


    /**
     * @return Calendar object which represents a date of current event
     */
    public Calendar getCalendar() {
        return mDay;
    }


    /**
     * @return Boolean value if day is not disabled
     */
    public boolean isEnabled() {
        return !mIsDisabled;
    }

    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public void setEnabled(boolean enabled) {
        mIsDisabled = enabled;
    }
}
