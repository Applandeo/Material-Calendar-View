package com.applandeo.materialcalendarview.builders

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.DatePicker
import com.applandeo.materialcalendarview.listeners.OnCalendarPageChangeListener
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener
import com.applandeo.materialcalendarview.utils.CalendarProperties
import java.util.*

/**
 * Created by Mateusz Kornakiewicz on 12.10.2017.
 */
class DatePickerBuilder(
        private val context: Context,
        onSelectDateListener: OnSelectDateListener) {
    private val calendarProperties: CalendarProperties = CalendarProperties(context)

    init {
        calendarProperties.calendarType = CalendarView.ONE_DAY_PICKER
        calendarProperties.onSelectDateListener = onSelectDateListener
    }

    fun build() = DatePicker(context, calendarProperties)

    fun pickerType(calendarType: Int) {
        calendarProperties.calendarType = calendarType
    }

    fun date(calendar: Calendar) {
        calendarProperties.calendar = calendar
    }

    fun headerColor(@ColorRes color: Int) {
        calendarProperties.headerColor = color
    }

    fun headerVisibility(visibility: Int) {
        calendarProperties.headerVisibility = visibility
    }

    fun abbreviationsBarVisibility(visibility: Int) {
        calendarProperties.abbreviationsBarVisibility = visibility
    }

    fun headerLabelColor(@ColorRes color: Int) {
        calendarProperties.headerLabelColor = color
    }

    fun previousButtonSrc(@DrawableRes drawable: Int) {
        calendarProperties.previousButtonSrc = ContextCompat.getDrawable(context, drawable)
    }

    fun forwardButtonSrc(@DrawableRes drawable: Int) {
        calendarProperties.forwardButtonSrc = ContextCompat.getDrawable(context, drawable)
    }

    fun selectionColor(@ColorRes color: Int) {
        calendarProperties.selectionColor = ContextCompat.getColor(context, color)
    }

    fun todayLabelColor(@ColorRes color: Int) {
        calendarProperties.todayLabelColor = ContextCompat.getColor(context, color)
    }

    fun highlightedDaysLabelsColor(@ColorRes color: Int) {
        calendarProperties.highlightedDaysLabelsColor = ContextCompat.getColor(context, color)
    }

    fun dialogButtonsColor(@ColorRes color: Int) {
        calendarProperties.dialogButtonsColor = color
    }

    fun minimumDate(calendar: Calendar) {
        calendarProperties.minimumDate = calendar
    }

    fun maximumDate(calendar: Calendar) {
        calendarProperties.maximumDate = calendar
    }

    fun disabledDays(disabledDays: List<Calendar>) {
        calendarProperties.disabledDays = disabledDays
    }

    fun highlightedDays(highlightedDays: List<Calendar>) {
        calendarProperties.highlightedDays = highlightedDays
    }

    fun previousPageChangeListener(listener: OnCalendarPageChangeListener) {
        calendarProperties.onPreviousPageChangeListener = listener
    }

    fun forwardPageChangeListener(listener: OnCalendarPageChangeListener) {
        calendarProperties.onForwardPageChangeListener = listener
    }

    fun disabledDaysLabelsColor(@ColorRes color: Int) {
        calendarProperties.disabledDaysLabelsColor = ContextCompat.getColor(context, color)
    }

    fun abbreviationsBarColor(@ColorRes color: Int) {
        calendarProperties.abbreviationsBarColor = ContextCompat.getColor(context, color)
    }

    fun pagesColor(@ColorRes color: Int) {
        calendarProperties.pagesColor = ContextCompat.getColor(context, color)
    }

    fun abbreviationsLabelsColor(@ColorRes color: Int) {
        calendarProperties.abbreviationsLabelsColor = ContextCompat.getColor(context, color)
    }

    fun daysLabelsColor(color: Int) {
        calendarProperties.daysLabelsColor = ContextCompat.getColor(context, color)
    }

    fun selectionLabelColor(color: Int) {
        calendarProperties.selectionLabelColor = ContextCompat.getColor(context, color)
    }

    fun anotherMonthsDaysLabelsColor(color: Int) {
        calendarProperties.anotherMonthsDaysLabelsColor = ContextCompat.getColor(context, color)
    }

    fun selectedDays(selectedDays: List<Calendar>) {
        calendarProperties.selectDays(selectedDays)
    }
}
