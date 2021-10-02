package com.applandeo.materialcalendarview;

import android.graphics.drawable.Drawable;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;

import com.applandeo.materialcalendarview.utils.EventImage;

import java.util.Calendar;

public class EventDay {

    private final Calendar calendar;

    private EventImage imageDrawable;

    private int labelColor;

    private boolean isEnabled;

    public EventDay(Calendar calendar) {
        this.calendar = calendar;
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    public EventDay(Calendar calendar, Drawable imageDrawable) {
        this(calendar);
        this.imageDrawable = new EventImage.EventImageDrawable(imageDrawable);
    }

    public EventDay(Calendar calendar, @DrawableRes int imageDrawable) {
        this(calendar);
        this.imageDrawable = new EventImage.EventImageResource(imageDrawable);
    }

    public EventDay(Calendar calendar, @DrawableRes int imageDrawable, @ColorInt int labelColor) {
        this(calendar);
        this.imageDrawable = new EventImage.EventImageResource(imageDrawable);
        this.labelColor = labelColor;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public EventImage getImageDrawable() {
        return imageDrawable;
    }

    public int getLabelColor() {
        return labelColor;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public Calendar getCalendar() {
        return calendar;
    }
}
