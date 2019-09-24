package com.applandeo.materialcalendarview.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat

import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.EventDay
import com.applandeo.materialcalendarview.R
import com.applandeo.materialcalendarview.exceptions.ErrorsMessages
import com.applandeo.materialcalendarview.exceptions.UnsupportedMethodsException
import com.applandeo.materialcalendarview.listeners.OnCalendarPageChangeListener
import com.applandeo.materialcalendarview.listeners.OnDayClickListener
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener
import com.applandeo.materialcalendarview.listeners.OnSelectionAbilityListener

import java.util.ArrayList
import java.util.Calendar

/**
 * This class contains all properties of the calendar
 *
 *
 * Created by Mateusz Kornakiewicz on 30.10.2017.
 */

class CalendarProperties(private val mContext: Context) {

    var calendarType: Int = 0

    var headerColor: Int = 0
        get() = if (field <= 0) {
            field
        } else ContextCompat.getColor(mContext, field)

    var headerLabelColor: Int = 0
        get() = if (field <= 0) {
            field
        } else ContextCompat.getColor(mContext, field)

    var selectionColor: Int = 0
        get() = if (field == 0) {
            ContextCompat.getColor(mContext, R.color.defaultColor)
        } else field

    var todayLabelColor: Int = 0
        get() = if (field == 0) {
            ContextCompat.getColor(mContext, R.color.defaultColor)
        } else field

    var dialogButtonsColor: Int = 0

    var itemLayoutResource: Int = 0

    var disabledDaysLabelsColor: Int = 0
        get() = if (field == 0) {
            ContextCompat.getColor(mContext, R.color.nextMonthDayColor)
        } else field

    var highlightedDaysLabelsColor: Int = 0
        get() = if (field == 0) {
            ContextCompat.getColor(mContext, R.color.nextMonthDayColor)
        } else field

    var pagesColor: Int = 0

    var abbreviationsBarColor: Int = 0

    var abbreviationsLabelsColor: Int = 0

    var daysLabelsColor: Int = 0
        get() = if (field == 0) {
            ContextCompat.getColor(mContext, R.color.currentMonthDayColor)
        } else field

    var selectionLabelColor: Int = 0
        get() = if (field == 0) {
            ContextCompat.getColor(mContext, android.R.color.white)
        } else field

    var anotherMonthsDaysLabelsColor: Int = 0
        get() = if (field == 0) {
            ContextCompat.getColor(mContext, R.color.nextMonthDayColor)
        } else field

    var headerVisibility: Int = 0

    var abbreviationsBarVisibility: Int = 0

    var eventsEnabled: Boolean = false

    var swipeEnabled: Boolean = false

    var previousButtonSrc: Drawable? = null

    var forwardButtonSrc: Drawable? = null

    val firstPageCalendarDate: Calendar? = DateUtils.calendar

    var calendar: Calendar? = null

    var minimumDate: Calendar? = null

    var maximumDate: Calendar? = null

    var onDayClickListener: OnDayClickListener? = null

    var onSelectDateListener: OnSelectDateListener? = null

    var onSelectionAbilityListener: OnSelectionAbilityListener? = null

    var onPreviousPageChangeListener: OnCalendarPageChangeListener? = null

    var onForwardPageChangeListener: OnCalendarPageChangeListener? = null

    var eventDays: List<EventDay> = ArrayList()

    var disabledDays: List<Calendar> = ArrayList()
        set(disabledDays) {
            selectedDays = selectedDays.filter {
                disabledDays.contains(it.calendar).not()
            }.toMutableList()

            field = disabledDays
                    .map { calendar ->
                        DateUtils.setMidnight(calendar)
                        calendar
                    }.toList()
        }

    var highlightedDays: List<Calendar> = ArrayList()
        set(highlightedDays) {
            field = highlightedDays
                    .map { calendar ->
                        DateUtils.setMidnight(calendar)
                        calendar
                    }.toList()
        }

    var selectedDays = mutableListOf<SelectedDay>()

    fun setSelectedDay(calendar: Calendar) = setSelectedDay(SelectedDay(calendar))

    fun setSelectedDay(selectedDay: SelectedDay) {
        selectedDays.clear()
        selectedDays.add(selectedDay)
    }

    fun selectDays(days: List<Calendar>) {
        if (calendarType == CalendarView.ONE_DAY_PICKER) {
            throw UnsupportedMethodsException(ErrorsMessages.ONE_DAY_PICKER_MULTIPLE_SELECTION)
        }

        if (calendarType == CalendarView.RANGE_PICKER && !DateUtils.isFullDatesRange(days)) {
            throw UnsupportedMethodsException(ErrorsMessages.RANGE_PICKER_NOT_RANGE)
        }

        selectedDays = days
                .map { day ->
                    DateUtils.setMidnight(day)
                    SelectedDay(day)
                }
                .filterNot {
                    disabledDays.all { calendar -> calendar != it.calendar }
                }
                .toMutableList()

    }

    companion object {
        /**
         * A number of months (pages) in the calendar
         * 2401 months means 1200 months (100 years) before and 1200 months after the current month
         */
        val CALENDAR_SIZE = 2401
        val FIRST_VISIBLE_PAGE = CALENDAR_SIZE / 2
    }
}
