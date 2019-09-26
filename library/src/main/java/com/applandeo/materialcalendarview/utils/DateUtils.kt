package com.applandeo.materialcalendarview.utils

import android.content.Context

import com.annimon.stream.Stream
import com.applandeo.materialcalendarview.R

import java.util.Calendar
import java.util.concurrent.TimeUnit

/**
 * Created by Mateusz Kornakiewicz on 23.05.2017.
 */

object DateUtils {

    /**
     * @return An instance of the Calendar object with hour set to 00:00:00:00
     */
    val calendar: Calendar
        get() {
            return Calendar.getInstance().also {
                setMidnight(it)
            }
        }

    /**
     * This method sets an hour in the calendar object to 00:00:00:00
     *
     * @param calendar Calendar object which hour should be set to 00:00:00:00
     */
    fun setMidnight(calendar: Calendar?) {
        calendar?.apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
    }

    /**
     * This method compares calendars using month and year
     *
     * @param firstCalendar  First calendar object to compare
     * @param secondCalendar Second calendar object to compare
     * @return Boolean value if second calendar is before the first one
     */
    fun isMonthBefore(firstCalendar: Calendar?, secondCalendar: Calendar): Boolean {
        if (firstCalendar == null) return false

        val firstDay = firstCalendar.clone() as Calendar
        setMidnight(firstDay)
        firstDay.set(Calendar.DAY_OF_MONTH, 1)
        val secondDay = secondCalendar.clone() as Calendar
        setMidnight(secondDay)
        secondDay.set(Calendar.DAY_OF_MONTH, 1)

        return secondDay.before(firstDay)
    }

    /**
     * This method compares calendars using month and year
     *
     * @param firstCalendar  First calendar object to compare
     * @param secondCalendar Second calendar object to compare
     * @return Boolean value if second calendar is after the first one
     */
    fun isMonthAfter(firstCalendar: Calendar?, secondCalendar: Calendar): Boolean {
        if (firstCalendar == null) return false

        val firstDay = firstCalendar.clone() as Calendar
        setMidnight(firstDay)
        firstDay.set(Calendar.DAY_OF_MONTH, 1)
        val secondDay = secondCalendar.clone() as Calendar
        setMidnight(secondDay)
        secondDay.set(Calendar.DAY_OF_MONTH, 1)

        return secondDay.after(firstDay)
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
    fun getMonthAndYearDate(context: Context, calendar: Calendar): String {
        return String.format("%s  %s",
                context.resources.getStringArray(R.array.material_calendar_months_array)[calendar.get(Calendar.MONTH)],
                calendar.get(Calendar.YEAR))
    }

    /**
     * This method is used to count a number of months between two dates
     *
     * @param startCalendar Calendar representing a first date
     * @param endCalendar   Calendar representing a last date
     * @return Number of months
     */
    fun getMonthsBetweenDates(startCalendar: Calendar, endCalendar: Calendar): Int {
        val years = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR)
        return years * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH)
    }


    /**
     * This method is used to count a number of days between two dates
     *
     * @param startCalendar Calendar representing a first date
     * @param endCalendar   Calendar representing a last date
     * @return Number of days
     */
    private fun getDaysBetweenDates(startCalendar: Calendar, endCalendar: Calendar): Long {
        val milliseconds = endCalendar.timeInMillis - startCalendar.timeInMillis
        return TimeUnit.MILLISECONDS.toDays(milliseconds)
    }

    fun isFullDatesRange(days: List<Calendar>): Boolean {
        val listSize = days.size

        if (days.isEmpty() || days.size == 1) {
            return true
        }

        val sortedCalendars = days.sortedBy { it.timeInMillis }.toList()

        return listSize.toLong() == getDaysBetweenDates(sortedCalendars[0], sortedCalendars[listSize - 1]) + 1
    }
}
