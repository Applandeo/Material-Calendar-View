package com.applandeo.materialcalendarview.utils;

import android.content.Context;

import com.annimon.stream.Stream;
import com.applandeo.materialcalendarview.R;

import java.util.Calendar;
import java.util.List;
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
     * This method is used to count a number of months between two dates
     *
     * @param startCalendar Calendar representing a first date
     * @param endCalendar   Calendar representing a last date
     * @return Number of months
     */
    public static int getMonthsBetweenDates(Calendar startCalendar, Calendar endCalendar) {
        int years = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        return years * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
    }


    /**
     * This method is used to count a number of days between two dates
     *
     * @param startCalendar Calendar representing a first date
     * @param endCalendar   Calendar representing a last date
     * @return Number of days
     */
    private static long getDaysBetweenDates(Calendar startCalendar, Calendar endCalendar) {
        long milliseconds = endCalendar.getTimeInMillis() - startCalendar.getTimeInMillis();
        return TimeUnit.MILLISECONDS.toDays(milliseconds);
    }

    public static boolean isFullDatesRange(List<Calendar> days) {
        int listSize = days.size();

        if (days.isEmpty() || listSize == 1) {
            return true;
        }

        List<Calendar> sortedCalendars = Stream.of(days).sortBy(Calendar::getTimeInMillis).toList();

        return listSize == getDaysBetweenDates(sortedCalendars.get(0), sortedCalendars.get(listSize - 1)) + 1;
    }
}
