package com.applandeo.materialcalendarview.utils;

import java.util.Calendar;

/**
 * Created by Mateusz Kornakiewicz on 23.05.2017.
 */

public class DateUtils {

    /**
     * @return An instance of the Calendar object with hour set to 00:00:00:00
     */
    public static Calendar getCalendar() {
        Calendar calendar = Calendar.getInstance();
        setMidnight(calendar);

        return calendar;
    }

    /**
     * This method sets an hour in the calendar object to 00:00:00:00
     *
     * @param calendar Calendar object which hour should be set to 00:00:00:00
     */
    public static void setMidnight(Calendar calendar) {
        if (calendar != null) {
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
        }
    }

    /**
     * This method returns a string containing a month's name and a year (in number).
     * It's used instead of new SimpleDateFormat("MMMM yyyy", Locale.getDefault()).format([Date]);
     * because that method returns a month's name in incorrect form in some languages (i.e. in Polish)
     *
     * @param monthsNames  An array of months names
     * @param calendar A Calendar object containing date which will be formatted
     * @return A string of the formatted date containing a month's name and a year (in number)
     */
    public static String getMonthAndYearDate(String[] monthsNames, Calendar calendar) {
        return String.format("%s  %s", monthsNames[calendar.get(Calendar.MONTH)], calendar.get(Calendar.YEAR));
    }
}
