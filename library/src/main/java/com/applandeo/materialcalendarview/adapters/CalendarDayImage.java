package com.applandeo.materialcalendarview.adapters;

import android.widget.LinearLayout;

import com.applandeo.materialcalendarview.EventDay;

/**
 * Created by shuwnyuan on 07/04/2018.
 */

public interface CalendarDayImage {
    void addImagesToLayout (LinearLayout layout, EventDay day, boolean setTransparent);
}
