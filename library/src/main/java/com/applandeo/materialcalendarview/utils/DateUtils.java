package com.applandeo.materialcalendarview.utils;

import android.content.Context;

import com.applandeo.materialcalendarview.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
     * This method compares calendars using month and year
     *
     * @param firstCalendar  First calendar object to compare
     * @param secondCalendar Second calendar object to compare
     * @return Boolean value if second calendar is before the first one
     */
    public static boolean isMonthBefore(Calendar firstCalendar, Calendar secondCalendar) {
        if (firstCalendar == null) {
            return false;
        }

        Calendar firstDay = (Calendar) firstCalendar.clone();
        setMidnight(firstDay);
        firstDay.set(Calendar.DAY_OF_MONTH, 1);
        Calendar secondDay = (Calendar) secondCalendar.clone();
        setMidnight(secondDay);
        secondDay.set(Calendar.DAY_OF_MONTH, 1);

        return secondDay.before(firstDay);
    }

    /**
     * This method compares calendars using month and year
     *
     * @param firstCalendar  First calendar object to compare
     * @param secondCalendar Second calendar object to compare
     * @return Boolean value if second calendar is after the first one
     */
    public static boolean isMonthAfter(Calendar firstCalendar, Calendar secondCalendar) {
        if (firstCalendar == null) {
            return false;
        }

        Calendar firstDay = (Calendar) firstCalendar.clone();
        setMidnight(firstDay);
        firstDay.set(Calendar.DAY_OF_MONTH, 1);
        Calendar secondDay = (Calendar) secondCalendar.clone();
        setMidnight(secondDay);
        secondDay.set(Calendar.DAY_OF_MONTH, 1);

        return secondDay.after(firstDay);
    }

    /**
     * This method returns a string containing a month's name and a year (in number).
     * It's used instead of new SimpleDateFormat("MMMM yyyy", Locale.getDefault()).format([Date]);
     * because that method returns a month's name in incorrect form in some languages (i.e. in Polish)
     *
     * @param context  An array of months names
     * @param calendar A Calendar object containing date which will be formatted
     * @return A string of the formatted date containing a month's name and a year (in number)
     */
    public static String getMonthAndYearDate(Context context, Calendar calendar) {
        return String.format("%s  %s",
                context.getResources().getStringArray(R.array.material_calendar_months_array)[calendar.get(Calendar.MONTH)],
                calendar.get(Calendar.YEAR));
    }

    /**
     * This method returns a list of calendar objects between to dates
     * @param firstDay Calendar representing a first selected date
     * @param lastDay Calendar representing a last selected date
     * @return List of selected dates between two dates
     */
    public static ArrayList<Calendar> getDatesRange(Calendar firstDay, Calendar lastDay) {
        if (lastDay.before(firstDay)) {
            return getCalendarsBetweenDates(lastDay.getTime(), firstDay.getTime());
        }

        return getCalendarsBetweenDates(firstDay.getTime(), lastDay.getTime());
    }

    private static ArrayList<Calendar> getCalendarsBetweenDates(Date dateFrom, Date dateTo) {
        ArrayList<Calendar> calendars = new ArrayList<>();

        Calendar calendarFrom = Calendar.getInstance();
        calendarFrom.setTime(dateFrom);

        Calendar calendarTo = Calendar.getInstance();
        calendarTo.setTime(dateTo);

        long daysBetweenDates = TimeUnit.MILLISECONDS.toDays(
                calendarTo.getTimeInMillis() - calendarFrom.getTimeInMillis());

        for (int i = 1; i < daysBetweenDates; i++) {
            Calendar calendar = (Calendar) calendarFrom.clone();
            calendars.add(calendar);
            calendar.add(Calendar.DATE, i);
        }

        return calendars;
    }

    /**
     * This method is used to count a number of months between two dates
     * @param startCalendar Calendar representing a first date
     * @param endCalendar Calendar representing a last date
     * @return Number of months
     */
    public static int getMonthsBetweenDates(Calendar startCalendar, Calendar endCalendar) {
        int years = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        return years * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
    }
}
