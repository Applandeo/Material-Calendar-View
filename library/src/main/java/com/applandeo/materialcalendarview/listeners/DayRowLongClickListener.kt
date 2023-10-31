package com.applandeo.materialcalendarview.listeners

import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemLongClickListener
import com.applandeo.materialcalendarview.CalendarDay
import com.applandeo.materialcalendarview.EventDay
import com.applandeo.materialcalendarview.utils.CalendarProperties
import com.applandeo.materialcalendarview.utils.isBetweenMinAndMax
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar

/**
 * This class is responsible for handle click events
 *
 *
 * Created by Applandeo Team.
 */
class DayRowLongClickListener(
    private val calendarProperties: CalendarProperties
) : OnItemLongClickListener {

    override fun onItemLongClick(
        adapterView: AdapterView<*>,
        view: View,
        position: Int,
        id: Long
    ): Boolean {
        val day = GregorianCalendar().apply {
            time = adapterView.getItemAtPosition(position) as Date
        }

        if (calendarProperties.onDayLongClickListener != null) {
            onLongClick(day)
        }

        if (calendarProperties.onCalendarDayLongClickListener != null) {
            onCalendarDayClick(day)
        }

        return true
    }

    // TODO remove when EventDay will be removed
    private fun onLongClick(day: Calendar) {
        val eventDay = calendarProperties.eventDays.firstOrNull { it.calendar == day }
        callOnLongClickListener(eventDay ?: EventDay(day))
    }

    // TODO remove when EventDay will be removed
    private fun callOnLongClickListener(eventDay: EventDay) {
        val enabledDay = calendarProperties.disabledDays.contains(eventDay.calendar)
                || !eventDay.calendar.isBetweenMinAndMax(calendarProperties)
        eventDay.isEnabled = enabledDay
        calendarProperties.onDayLongClickListener?.onDayLongClick(eventDay)
    }

    private fun onCalendarDayClick(day: Calendar) {
        val calendarDay = calendarProperties.calendarDays.firstOrNull { it.calendar == day }
        callOnCalendarDayLongClickListener(calendarDay ?: CalendarDay(day))
    }

    private fun callOnCalendarDayLongClickListener(calendarDay: CalendarDay) {
        calendarProperties.onCalendarDayLongClickListener?.onClick(calendarDay)
    }
}