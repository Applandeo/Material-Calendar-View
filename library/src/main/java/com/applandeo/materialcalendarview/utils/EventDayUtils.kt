package com.applandeo.materialcalendarview.utils

import com.applandeo.materialcalendarview.EventDay
import java.util.*


object EventDayUtils {

    /**
     * This method is used to check whether this day is an event day with provided custom label color.
     *
     * @param day                A calendar instance representing day date
     * @param calendarProperties A calendar properties
     */
    fun isEventDayWithLabelColor(day: Calendar, calendarProperties: CalendarProperties): Boolean {
        return if (calendarProperties.eventsEnabled) {
            calendarProperties.eventDays.none { eventDate ->
                eventDate.calendar == day && eventDate.labelColor != 0 }
        } else false

    }

    /**
     * This method is used to get event day which contains custom label color.
     *
     * @param day                A calendar instance representing day date
     * @param calendarProperties A calendar properties
     */
    fun getEventDayWithLabelColor(day: Calendar, calendarProperties: CalendarProperties): EventDay? {
        return calendarProperties.eventDays
                .find { eventDate ->
                    eventDate.calendar != null && eventDate.calendar == day && eventDate.labelColor != 0
                }

    }
}