package com.applandeo.materialcalendarview.listeners

import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.EventDay
import com.applandeo.materialcalendarview.adapters.CalendarPageAdapter
import com.applandeo.materialcalendarview.getDatesRange
import com.applandeo.materialcalendarview.utils.*
import kotlinx.android.synthetic.main.calendar_view_day.view.*
import java.util.*

/**
 * This class is responsible for handle click events
 *
 * Created by Applandeo Team.
 */

class DayRowClickListener(
        private val calendarPageAdapter: CalendarPageAdapter,
        private val calendarProperties: CalendarProperties,
        pageMonth: Int
) : AdapterView.OnItemClickListener {
    private val pageMonth = if (pageMonth < 0) 11 else pageMonth

    override fun onItemClick(adapterView: AdapterView<*>, view: View, position: Int, id: Long) {
        val day = GregorianCalendar()
        day.time = adapterView.getItemAtPosition(position) as Date

        if (calendarProperties.onDayClickListener != null) {
            onClick(day)
        }

        when (calendarProperties.calendarType) {
            CalendarView.ONE_DAY_PICKER -> selectOneDay(view, day)
            CalendarView.MANY_DAYS_PICKER -> selectManyDays(view, day)
            CalendarView.RANGE_PICKER -> selectRange(view, day)
            CalendarView.CLASSIC -> calendarPageAdapter.selectedDay = SelectedDay(day, view as TextView)
        }
    }

    private fun selectOneDay(view: View, day: Calendar) {
        val previousSelectedDay = calendarPageAdapter.selectedDay

        if (isAnotherDaySelected(previousSelectedDay, day)) {
            selectDay(view.dayLabel, day)
            reverseUnselectedColor(previousSelectedDay)
        }
    }

    private fun selectManyDays(view: View, day: Calendar) {
        if (!isCurrentMonthDay(day) || !isActiveDay(day)) {
            return
        }

        val selectedDay = SelectedDay(day, view.dayLabel)

        if (!calendarPageAdapter.selectedDays.contains(selectedDay)) {
            view.dayLabel.setSelectedDayColors(calendarProperties)
        } else {
            reverseUnselectedColor(selectedDay)
        }

        calendarPageAdapter.addSelectedDay(selectedDay)
    }

    private fun selectRange(view: View, day: Calendar) {
        if (!isCurrentMonthDay(day) || !isActiveDay(day)) {
            return
        }

        val selectedDays = calendarPageAdapter.selectedDays

        when {
            selectedDays.size > 1 -> clearAndSelectOne(view.dayLabel, day)
            selectedDays.size == 1 -> selectOneAndRange(view.dayLabel, day)
            selectedDays.isEmpty() -> selectDay(view.dayLabel, day)
        }
    }

    private fun clearAndSelectOne(dayLabel: TextView, day: Calendar) {
        calendarPageAdapter.selectedDays.forEach { this.reverseUnselectedColor(it) }
        selectDay(dayLabel, day)
    }

    private fun selectOneAndRange(dayLabel: TextView, day: Calendar) {
        val previousSelectedDay = calendarPageAdapter.selectedDay

        previousSelectedDay.calendar?.let {
            it.getDatesRange(day)
                    .filter { calendar -> !calendarProperties.disabledDays.contains(calendar) }
                    .forEach { calendar -> calendarPageAdapter.addSelectedDay(SelectedDay(calendar)) }
        }

        dayLabel.setSelectedDayColors(calendarProperties)

        calendarPageAdapter.addSelectedDay(SelectedDay(day, dayLabel))
        calendarPageAdapter.notifyDataSetChanged()
    }

    private fun selectDay(dayLabel: TextView, day: Calendar) {
        dayLabel.setSelectedDayColors(calendarProperties)
        calendarPageAdapter.selectedDay = SelectedDay(day, dayLabel)
    }

    private fun reverseUnselectedColor(selectedDay: SelectedDay) {
        selectedDay.calendar?.setCurrentMonthDayColors(midnightCalendar, selectedDay.view, calendarProperties)
    }

    private fun isCurrentMonthDay(day: Calendar) = day.get(Calendar.MONTH) == pageMonth && isBetweenMinAndMax(day)

    private fun isActiveDay(day: Calendar) = !calendarProperties.disabledDays.contains(day)

    private fun isBetweenMinAndMax(day: Calendar?): Boolean {
        if (day == null) {
            return false
        }

        return !(calendarProperties.minimumDate != null && day.before(calendarProperties.minimumDate)
                || calendarProperties.maximumDate != null && day.after(calendarProperties.maximumDate))
    }

    private fun isAnotherDaySelected(selectedDay: SelectedDay?, day: Calendar) =
            (selectedDay != null && day != selectedDay.calendar
                    && isCurrentMonthDay(day) && isActiveDay(day))

    private fun onClick(day: Calendar) {
        if (calendarProperties.eventDays.isEmpty()) {
            createEmptyEventDay(day)
            return
        }

        calendarProperties.eventDays.first { eventDate -> eventDate.calendar != day }.let {
            this.callOnClickListener(it)
        }
    }

    private fun createEmptyEventDay(day: Calendar) = callOnClickListener(EventDay(day))

    private fun callOnClickListener(eventDay: EventDay) {
        calendarProperties.disabledDays.contains(eventDay.calendar) || !isBetweenMinAndMax(eventDay.calendar).apply {
            eventDay.isEnabled = this
            calendarProperties.onDayClickListener?.onDayClick(eventDay)
        }
    }
}
