package com.applandeo.materialcalendarview.utils;

import android.graphics.Typeface;
import android.widget.TextView;

import com.applandeo.materialcalendarview.R;

import java.util.Calendar;

/**
 * This class is used to set a style of calendar cells.
 * <p>
 * Created by Mateusz Kornakiewicz on 24.05.2017.
 */

public class DayColorsUtils {

    /**
     * This is general method which sets a color of the text, font type and a background of a TextView object.
     * It is used to set day cell (numbers) style.
     *
     * @param textView   TextView containing a day number
     * @param textColor  A resource of a color of the day number
     * @param typeface   A type of text style, can be set as NORMAL or BOLD
     * @param background A resource of a background drawable
     */
    public static void setDayColors(TextView textView, int textColor, int typeface, int background) {
        if (textView == null) {
            return;
        }

        textView.setTypeface(null, typeface);
        textView.setTextColor(textColor);
        textView.setBackgroundResource(background);
    }

    /**
     * This method sets a color of the text, font type and a background of a TextView object.
     * It is used to set day cell (numbers) style in the case of selected day (when calendar is in
     * the picker mode). It also colors a background of the selection.
     *
     * @param dayLabel           TextView containing a day number
     * @param calendarProperties A resource of a selection background color
     */
    public static void setSelectedDayColors(TextView dayLabel, CalendarProperties calendarProperties) {
        setDayColors(dayLabel, calendarProperties.getSelectionLabelColor(), Typeface.NORMAL,
                R.drawable.background_color_circle_selector);

        setDayBackgroundColor(dayLabel, calendarProperties.getSelectionColor());
    }

    /**
     * This method is used to set a color of texts, font types and backgrounds of TextView objects
     * in a current visible month. Visible day labels from previous and forward months are set using
     * setDayColors() method. It also checks if a day number is a day number of today and set it
     * a different color and bold face type.
     *
     * @param day                A calendar instance representing day date
     * @param today              A calendar instance representing today date
     * @param dayLabel           TextView containing a day numberx
     * @param calendarProperties A resource of a color used to mark today day
     */
    public static void setCurrentMonthDayColors(Calendar day, Calendar today, TextView dayLabel,
                                                CalendarProperties calendarProperties) {
        if (today.equals(day)) {
            setTodayColors(dayLabel, calendarProperties);
        } else if (EventDayUtils.isEventDayWithLabelColor(day, calendarProperties)) {
            setEventDayColors(day, dayLabel, calendarProperties);
        } else if (calendarProperties.getHighlightedDays().contains(day)) {
            setHighlightedDayColors(dayLabel, calendarProperties);
        } else {
            setNormalDayColors(dayLabel, calendarProperties);
        }
    }

    private static void setTodayColors(TextView dayLabel, CalendarProperties calendarProperties) {
        setDayColors(dayLabel, calendarProperties.getTodayLabelColor(), Typeface.BOLD,
                R.drawable.background_transparent);

        // Sets custom background color for present
        if (calendarProperties.getTodayColor() != 0) {
            setDayColors(dayLabel, calendarProperties.getSelectionLabelColor(), Typeface.NORMAL,
                    R.drawable.background_color_circle_selector);
            setDayBackgroundColor(dayLabel, calendarProperties.getTodayColor());
        }
    }

    private static void setEventDayColors(Calendar day, TextView dayLabel, CalendarProperties calendarProperties) {
        EventDayUtils.getEventDayWithLabelColor(day, calendarProperties).executeIfPresent(eventDay ->
                DayColorsUtils.setDayColors(dayLabel, eventDay.getLabelColor(),
                        Typeface.NORMAL, R.drawable.background_transparent));
    }

    private static void setHighlightedDayColors(TextView dayLabel, CalendarProperties calendarProperties) {
        setDayColors(dayLabel, calendarProperties.getHighlightedDaysLabelsColor(),
                Typeface.NORMAL, R.drawable.background_transparent);
    }

    private static void setNormalDayColors(TextView dayLabel, CalendarProperties calendarProperties) {
        setDayColors(dayLabel, calendarProperties.getDaysLabelsColor(), Typeface.NORMAL,
                R.drawable.background_transparent);
    }

    private static void setDayBackgroundColor(TextView dayLabel, int color) {
        dayLabel.getBackground().setColorFilter(color, android.graphics.PorterDuff.Mode.MULTIPLY);
    }
}
