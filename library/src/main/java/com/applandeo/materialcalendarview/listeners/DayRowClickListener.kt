package com.applandeo.materialcalendarview.listeners

import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import com.applandeo.materialcalendarview.*
import com.applandeo.materialcalendarview.adapters.CalendarPageAdapter
import com.applandeo.materialcalendarview.utils.*
import java.util.*

/**
 * This class is responsible for handle click events
 *
 *
 * Created by Mateusz Kornakiewicz on 24.05.2017.
 */

class DayRowClickListener(
        private val calendarPageAdapter: CalendarPageAdapter,
        private val calendarProperties: CalendarProperties,
        pageMonth: Int
) : AdapterView.OnItemClickListener {
    private val mPageMonth = if (pageMonth < 0) 11 else pageMonth

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
            CalendarView.CLASSIC -> calendarPageAdapter.selectedDay = SelectedDay(day, view)
        }
    }

    private fun selectOneDay(view: View, day: Calendar) {
        val previousSelectedDay = calendarPageAdapter.selectedDay

        val dayLabel = view.findViewById<View>(R.id.dayLabel) as TextView

        if (isAnotherDaySelected(previousSelectedDay, day)) {
            selectDay(dayLabel, day)
            reverseUnselectedColor(previousSelectedDay)
        }
    }

    private fun selectManyDays(view: View, day: Calendar) {
        val dayLabel = view.findViewById<View>(R.id.dayLabel) as TextView

        if (isCurrentMonthDay(day) && isActiveDay(day)) {
            val selectedDay = SelectedDay(day, dayLabel)

            if (!calendarPageAdapter.selectedDays.contains(selectedDay)) {
                dayLabel.setSelectedDayColors(calendarProperties)
            } else {
                reverseUnselectedColor(selectedDay)
            }

            calendarPageAdapter.addSelectedDay(selectedDay)
        }
    }

    private fun selectRange(view: View, day: Calendar) {
        val dayLabel = view.findViewById<View>(R.id.dayLabel) as TextView

        if (!isCurrentMonthDay(day) || !isActiveDay(day)) {
            return
        }

        val selectedDays = calendarPageAdapter.selectedDays

        if (selectedDays.size > 1) {
            clearAndSelectOne(dayLabel, day)
        }

        if (selectedDays.size == 1) {
            selectOneAndRange(dayLabel, day)
        }

        if (selectedDays.isEmpty()) {
            selectDay(dayLabel, day)
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
        calendarPageAdapter.selectedDay = SelectedDay(day,dayLabel)
    }

    private fun reverseUnselectedColor(selectedDay: SelectedDay) {
        selectedDay.calendar?.setCurrentMonthDayColors(getMidnightCalendar, selectedDay.view as TextView, calendarProperties)
    }

    private fun isCurrentMonthDay(day: Calendar) = day.get(Calendar.MONTH) == mPageMonth && isBetweenMinAndMax(day)

    private fun isActiveDay(day: Calendar) = calendarProperties.disabledDays.contains(day).not()

    private fun isBetweenMinAndMax(day: Calendar?): Boolean {
        if (day == null) { return false }

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

    private fun createEmptyEventDay(day: Calendar) {
        EventDay(day).run {
            callOnClickListener(this)
        }
    }

    private fun callOnClickListener(eventDay: EventDay) {
        calendarProperties.disabledDays.contains(eventDay.calendar) || !isBetweenMinAndMax(eventDay.calendar).apply {
            eventDay.isEnabled = this
            calendarProperties.onDayClickListener?.onDayClick(eventDay)
        }
    }
}
