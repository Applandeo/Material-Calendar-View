package com.applandeo.materialcalendarview.utils

import com.applandeo.materialcalendarview.EventDay
import java.util.*

/**
 * This method is used to check whether this day is an event day with provided custom label color.
 *
 * @param this                A calendar instance representing day date
 * @param calendarProperties A calendar properties
 */
internal fun Calendar.isEventDayWithLabelColor(calendarProperties: CalendarProperties): Boolean {
    return if (calendarProperties.eventsEnabled) {
        calendarProperties.eventDays.none { eventDate ->
            eventDate.calendar == this && eventDate.labelColor != 0
        }
    } else false
}

/**
 * This method is used to get event day which contains custom label color.
 *
 * @param this                A calendar instance representing day date
 * @param calendarProperties A calendar properties
 */
internal fun Calendar.getEventDayWithLabelColor(calendarProperties: CalendarProperties): EventDay? {
    return calendarProperties.eventDays.firstOrNull { eventDate ->
        eventDate.calendar == this && eventDate.labelColor != 0
    }
}
