package com.applandeo.materialcalendarview.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import com.applandeo.materialcalendarview.CalendarDay
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.CalendarView.Companion.CLASSIC
import com.applandeo.materialcalendarview.EventDay
import com.applandeo.materialcalendarview.R
import com.applandeo.materialcalendarview.exceptions.ErrorsMessages
import com.applandeo.materialcalendarview.exceptions.UnsupportedMethodsException
import com.applandeo.materialcalendarview.listeners.OnCalendarPageChangeListener
import com.applandeo.materialcalendarview.listeners.OnDayClickListener
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener
import com.applandeo.materialcalendarview.listeners.OnSelectionAbilityListener
import java.util.*

/**
 * This class contains all properties of the calendar
 *
 * Created by Applandeo Team.
 */

class CalendarProperties(private val context: Context) {

    var calendarType: Int = CLASSIC

    var headerColor: Int = 0
        get() = if (field <= 0) field else context.parseColor(field)

    var headerLabelColor: Int = 0
        get() = if (field <= 0) field else context.parseColor(field)

    var selectionColor: Int = 0
        get() = if (field == 0) context.parseColor(R.color.defaultColor) else field

    var todayLabelColor: Int = 0
        get() = if (field == 0) context.parseColor(R.color.defaultColor) else field

    var todayColor: Int = 0
        get() = if (field <= 0) field else context.parseColor(field)

    var dialogButtonsColor: Int = 0

    var itemLayoutResource: Int = R.layout.calendar_view_day

    var disabledDaysLabelsColor: Int = 0
        get() = if (field == 0) context.parseColor(R.color.nextMonthDayColor) else field

    var highlightedDaysLabelsColor: Int = 0
        get() = if (field == 0) context.parseColor(R.color.nextMonthDayColor) else field

    var pagesColor: Int = 0

    var abbreviationsBarColor: Int = 0

    var abbreviationsLabelsColor: Int = 0

    var daysLabelsColor: Int = 0
        get() = if (field == 0) context.parseColor(R.color.currentMonthDayColor) else field

    var selectionLabelColor: Int = 0
        get() = if (field == 0) context.parseColor(android.R.color.white) else field

    var anotherMonthsDaysLabelsColor: Int = 0
        get() = if (field == 0) context.parseColor(R.color.nextMonthDayColor) else field

    var headerVisibility: Int = View.VISIBLE

    var abbreviationsBarVisibility: Int = View.VISIBLE

    var navigationVisibility: Int = View.VISIBLE

    var eventsEnabled: Boolean = false

    var swipeEnabled: Boolean = true

    var selectionDisabled: Boolean = false

    var previousButtonSrc: Drawable? = null

    var forwardButtonSrc: Drawable? = null

    val firstPageCalendarDate: Calendar = midnightCalendar

    var calendar: Calendar? = null

    var minimumDate: Calendar? = null

    var maximumDate: Calendar? = null

    var maximumDaysRange: Int = 0

    var onDayClickListener: OnDayClickListener? = null

    var onSelectDateListener: OnSelectDateListener? = null

    var onSelectionAbilityListener: OnSelectionAbilityListener? = null

    var onPreviousPageChangeListener: OnCalendarPageChangeListener? = null

    var onForwardPageChangeListener: OnCalendarPageChangeListener? = null

    var eventDays: List<EventDay> = mutableListOf()

    var calendarDayProperties: List<CalendarDay> = mutableListOf()

    var disabledDays: List<Calendar> = mutableListOf()
        set(disabledDays) {
            selectedDays = selectedDays.filter {
                !disabledDays.contains(it.calendar)
            }.toMutableList()

            field = disabledDays.map { it.setMidnight() }.toList()
        }

    var highlightedDays: List<Calendar> = mutableListOf()
        set(highlightedDays) {
            field = highlightedDays.map { it.setMidnight() }.toList()
        }

    var selectedDays = mutableListOf<SelectedDay>()
        private set

    fun setSelectedDay(calendar: Calendar) = setSelectedDay(SelectedDay(calendar))

    fun setSelectedDay(selectedDay: SelectedDay) {
        selectedDays.clear()
        selectedDays.add(selectedDay)
    }

    @Throws(UnsupportedMethodsException::class)
    fun setSelectDays(days: List<Calendar>) {
        if (calendarType == CalendarView.ONE_DAY_PICKER) {
            throw UnsupportedMethodsException(ErrorsMessages.ONE_DAY_PICKER_MULTIPLE_SELECTION)
        }

        if (calendarType == CalendarView.RANGE_PICKER && !days.isFullDatesRange()) {
            throw UnsupportedMethodsException(ErrorsMessages.RANGE_PICKER_NOT_RANGE)
        }

        selectedDays = days
                .map { SelectedDay(it.setMidnight()) }
                .filterNot { it.calendar in disabledDays }
                .toMutableList()
    }

    fun findDayProperties(calendar: Calendar): CalendarDay? =
            calendarDayProperties.find { it.calendar.isEqual(calendar) }

    companion object {
        /**
         * A number of months (pages) in the calendar
         * 2401 months means 1200 months (100 years) before and 1200 months after the current month
         */
        const val CALENDAR_SIZE = 2401
        const val FIRST_VISIBLE_PAGE = CALENDAR_SIZE / 2
    }
}
