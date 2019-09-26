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

import java.util.Calendar

/**
 * Created by Mateusz Kornakiewicz on 12.10.2017.
 */
class DatePickerBuilder(private val mContext: Context, onSelectDateListener: OnSelectDateListener) {
    private val mCalendarProperties: CalendarProperties = CalendarProperties(mContext)

    init {
        mCalendarProperties.calendarType = CalendarView.ONE_DAY_PICKER
        mCalendarProperties.onSelectDateListener = onSelectDateListener
    }

    fun build(): DatePicker {
        return DatePicker(mContext, mCalendarProperties)
    }

    fun pickerType(calendarType: Int): DatePickerBuilder {
        mCalendarProperties.calendarType = calendarType
        return this
    }

    fun date(calendar: Calendar): DatePickerBuilder {
        mCalendarProperties.calendar = calendar
        return this
    }

    fun headerColor(@ColorRes color: Int): DatePickerBuilder {
        mCalendarProperties.headerColor = color
        return this
    }

    fun headerVisibility(visibility: Int): DatePickerBuilder {
        mCalendarProperties.headerVisibility = visibility
        return this
    }

    fun abbreviationsBarVisibility(visibility: Int): DatePickerBuilder {
        mCalendarProperties.abbreviationsBarVisibility = visibility
        return this
    }

    fun headerLabelColor(@ColorRes color: Int): DatePickerBuilder {
        mCalendarProperties.headerLabelColor = color
        return this
    }

    fun previousButtonSrc(@DrawableRes drawable: Int): DatePickerBuilder {
        mCalendarProperties.previousButtonSrc = ContextCompat.getDrawable(mContext, drawable)
        return this
    }

    fun forwardButtonSrc(@DrawableRes drawable: Int): DatePickerBuilder {
        mCalendarProperties.forwardButtonSrc = ContextCompat.getDrawable(mContext, drawable)
        return this
    }

    fun selectionColor(@ColorRes color: Int): DatePickerBuilder {
        mCalendarProperties.selectionColor = ContextCompat.getColor(mContext, color)
        return this
    }

    fun todayLabelColor(@ColorRes color: Int): DatePickerBuilder {
        mCalendarProperties.todayLabelColor = ContextCompat.getColor(mContext, color)
        return this
    }

    fun highlightedDaysLabelsColor(@ColorRes color: Int): DatePickerBuilder {
        mCalendarProperties.highlightedDaysLabelsColor = ContextCompat.getColor(mContext, color)
        return this
    }

    fun dialogButtonsColor(@ColorRes color: Int): DatePickerBuilder {
        mCalendarProperties.dialogButtonsColor = color
        return this
    }

    fun minimumDate(calendar: Calendar): DatePickerBuilder {
        mCalendarProperties.minimumDate = calendar
        return this
    }

    fun maximumDate(calendar: Calendar): DatePickerBuilder {
        mCalendarProperties.maximumDate = calendar
        return this
    }

    fun disabledDays(disabledDays: List<Calendar>): DatePickerBuilder {
        mCalendarProperties.disabledDays = disabledDays
        return this
    }

    fun highlightedDays(highlightedDays: List<Calendar>): DatePickerBuilder {
        mCalendarProperties.highlightedDays = highlightedDays
        return this
    }

    fun previousPageChangeListener(listener: OnCalendarPageChangeListener): DatePickerBuilder {
        mCalendarProperties.onPreviousPageChangeListener = listener
        return this
    }

    fun forwardPageChangeListener(listener: OnCalendarPageChangeListener): DatePickerBuilder {
        mCalendarProperties.onForwardPageChangeListener = listener
        return this
    }

    fun disabledDaysLabelsColor(@ColorRes color: Int): DatePickerBuilder {
        mCalendarProperties.disabledDaysLabelsColor = ContextCompat.getColor(mContext, color)
        return this
    }

    fun abbreviationsBarColor(@ColorRes color: Int): DatePickerBuilder {
        mCalendarProperties.abbreviationsBarColor = ContextCompat.getColor(mContext, color)
        return this
    }

    fun pagesColor(@ColorRes color: Int): DatePickerBuilder {
        mCalendarProperties.pagesColor = ContextCompat.getColor(mContext, color)
        return this
    }

    fun abbreviationsLabelsColor(@ColorRes color: Int): DatePickerBuilder {
        mCalendarProperties.abbreviationsLabelsColor = ContextCompat.getColor(mContext, color)
        return this
    }

    fun daysLabelsColor(color: Int): DatePickerBuilder {
        mCalendarProperties.daysLabelsColor = ContextCompat.getColor(mContext, color)
        return this
    }

    fun selectionLabelColor(color: Int): DatePickerBuilder {
        mCalendarProperties.selectionLabelColor = ContextCompat.getColor(mContext, color)
        return this
    }

    fun anotherMonthsDaysLabelsColor(color: Int): DatePickerBuilder {
        mCalendarProperties.anotherMonthsDaysLabelsColor = ContextCompat.getColor(mContext, color)
        return this
    }

    fun selectedDays(selectedDays: List<Calendar>): DatePickerBuilder {
        mCalendarProperties.selectDays(selectedDays)
        return this
    }
}
