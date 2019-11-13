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
 * Created by Applandeo Team.
 */
class DatePickerBuilder(
        private val context: Context,
        onSelectDateListener: OnSelectDateListener
) {
    private val calendarProperties: CalendarProperties = CalendarProperties(context)

    init {
        calendarProperties.calendarType = CalendarView.ONE_DAY_PICKER
        calendarProperties.onSelectDateListener = onSelectDateListener
    }

    fun build() = DatePicker(context, calendarProperties)

    /**
     * Sets the calendar type of the date picker
     *
     * @param calendarType The calendar type to use
     */
    fun pickerType(calendarType: Int) {
        calendarProperties.calendarType = calendarType
    }

    /**
     * Sets the initially selected date of the date picker
     *
     * @param calendar The initially selected date as a {@link Calendar} object
     */
    fun date(calendar: Calendar) {
        calendarProperties.calendar = calendar
    }

    /**
     * Sets the header color of the date picker dialog's header
     *
     * @param color The header color as a color resource
     */
    fun headerColor(@ColorRes color: Int) {
        calendarProperties.headerColor = color
    }

    /**
     * Sets whether the date picker dialog's header should be visible
     *
     * @param visibility The visibility
     */
    fun headerVisibility(visibility: Int) {
        calendarProperties.headerVisibility = visibility
    }

    /**
     * Sets whether the date picker abbreviations bar should be visible
     *
     * @param visibility The visibility
     */
    fun abbreviationsBarVisibility(visibility: Int) {
        calendarProperties.abbreviationsBarVisibility = visibility
    }

    /**
     * Sets the date picker dialog header's label color
     *
     * @param color The header label color as a color resource
     */
    fun headerLabelColor(@ColorRes color: Int) {
        calendarProperties.headerLabelColor = color
    }

    /**
     * Sets the drawable to use for the previous button in the date picker dialog
     *
     * @param drawable The drawable to use as a drawable resource
     */
    fun previousButtonSrc(@DrawableRes drawable: Int) {
        calendarProperties.previousButtonSrc = ContextCompat.getDrawable(context, drawable)
    }

    /**
     * Sets the drawable to use for the forward button in the date picker dialog
     *
     * @param drawable The drawable to use as a drawable resource
     */
    fun forwardButtonSrc(@DrawableRes drawable: Int) {
        calendarProperties.forwardButtonSrc = ContextCompat.getDrawable(context, drawable)
    }

    /**
     * Sets the color of the selection circle in the date picker dialog
     *
     * @param color The color to use as a color resource
     */
    fun selectionColor(@ColorRes color: Int) {
        calendarProperties.selectionColor = ContextCompat.getColor(context, color)
    }

    /**
     * Sets the color of the today number in the date picker dialog
     *
     * @param color The color to use as a color resource
     */
    fun todayLabelColor(@ColorRes color: Int) {
        calendarProperties.todayLabelColor = ContextCompat.getColor(context, color)
    }

    /**
     * Sets the color of the highlighted days label
     *
     * @param color The color to use as a color resource
     */
    fun highlightedDaysLabelsColor(@ColorRes color: Int) {
        calendarProperties.highlightedDaysLabelsColor = ContextCompat.getColor(context, color)
    }

    /**
     * Sets the color of the dialog buttons in the date picker dialog
     *
     * @param color The color to use as a color resource
     */
    fun dialogButtonsColor(@ColorRes color: Int) {
        calendarProperties.dialogButtonsColor = color
    }

    /**
     * Sets the minimum available date of the date picker dialog
     *
     * @param calendar The minimum available date as a {@link Calendar} object
     */
    fun minimumDate(calendar: Calendar) {
        calendarProperties.minimumDate = calendar
    }

    /**
     * Sets the maximum available date of the date picker dialog
     *
     * @param calendar The maximum available date as a {@link Calendar} object
     */
    fun maximumDate(calendar: Calendar) {
        calendarProperties.maximumDate = calendar
    }

    /**
     * Sets the list of disabled days in the date picker dialog
     *
     * @param disabledDays The list of disabled days as a {@link List<Calendar>}
     */
    fun disabledDays(disabledDays: List<Calendar>) {
        calendarProperties.disabledDays = disabledDays
    }

    /**
     * Sets the list of highlighted days in the date picker dialog
     *
     * @param disabledDays The list of disabled days as a {@link List<Calendar>}
     */
    fun highlightedDays(highlightedDays: List<Calendar>) {
        calendarProperties.highlightedDays = highlightedDays
    }

    /**
     * Sets the previous page change listener which is called when scrolling to the previous page
     *
     * @param listener The previous page change listener to use
     */
    fun previousPageChangeListener(listener: OnCalendarPageChangeListener) {
        calendarProperties.onPreviousPageChangeListener = listener
    }

    /**
     * Sets the forward page change listener which is called when scrolling to the next page
     *
     * @param listener The forward page change listener to use
     */
    fun forwardPageChangeListener(listener: OnCalendarPageChangeListener) {
        calendarProperties.onForwardPageChangeListener = listener
    }

    /**
     * Sets the color of the disabled days numbers
     *
     * @param color The color to use as a color resource
     */
    fun disabledDaysLabelsColor(@ColorRes color: Int) {
        calendarProperties.disabledDaysLabelsColor = ContextCompat.getColor(context, color)
    }

    /**
     * Sets the color of the bar with the day symbols
     *
     * @param color The color to use as a color resource
     */
    fun abbreviationsBarColor(@ColorRes color: Int) {
        calendarProperties.abbreviationsBarColor = ContextCompat.getColor(context, color)
    }

    /**
     * Sets the color of the calendar background
     *
     * @param color The color to use as a color resource
     */
    fun pagesColor(@ColorRes color: Int) {
        calendarProperties.pagesColor = ContextCompat.getColor(context, color)
    }

    /**
     * Sets the color of the symbol labels
     *
     * @param color The color to use as a color resource
     */
    fun abbreviationsLabelsColor(@ColorRes color: Int) {
        calendarProperties.abbreviationsLabelsColor = ContextCompat.getColor(context, color)
    }

    /**
     * Sets the color of the day numbers
     *
     * @param color The color to use as a color resource
     */
    fun daysLabelsColor(color: Int) {
        calendarProperties.daysLabelsColor = ContextCompat.getColor(context, color)
    }

    /**
     * Sets the color of the label in the selection circle
     *
     * @param color The color to use as a color resource
     */
    fun selectionLabelColor(color: Int) {
        calendarProperties.selectionLabelColor = ContextCompat.getColor(context, color)
    }

    /**
     * Sets the color of visible day numbers from the previous to the next month pages
     *
     * @param color The color to use as a color resource
     */
    fun anotherMonthsDaysLabelsColor(color: Int) {
        calendarProperties.anotherMonthsDaysLabelsColor = ContextCompat.getColor(context, color)
    }

    /**
     * Sets the list of selected days in the calendar
     *
     * @param selectedDays The list of selected days as a {@link List<Calendar>}
     */
    fun selectedDays(selectedDays: List<Calendar>) {
        calendarProperties.selectDays(selectedDays)
    }
}
