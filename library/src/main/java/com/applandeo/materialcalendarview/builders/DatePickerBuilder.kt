package com.applandeo.materialcalendarview.builders

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.DatePicker
import com.applandeo.materialcalendarview.EventDay
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

    @Deprecated("Use setPickerType", ReplaceWith("setPickerType(calendarType)"))
    fun pickerType(calendarType: Int) = setPickerType(calendarType)

    fun setPickerType(calendarType: Int): DatePickerBuilder {
        calendarProperties.calendarType = calendarType
        return this
    }

    /**
     * Sets the initially selected date of the date picker
     *
     * @param calendar The initially selected date as a {@link Calendar} object
     */
    @Deprecated("Use setDate", ReplaceWith("setDate(calendar)"))
    fun date(calendar: Calendar) = setDate(calendar)

    fun setDate(calendar: Calendar): DatePickerBuilder {
        calendarProperties.calendar = calendar
        return this
    }

    /**
     * Sets the header color of the date picker dialog's header
     *
     * @param color The header color as a color resource
     */
    @Deprecated("Use setHeaderColor", ReplaceWith("setHeaderColor(color)"))
    fun headerColor(@ColorRes color: Int) = setHeaderColor(color)

    fun setHeaderColor(@ColorRes color: Int): DatePickerBuilder {
        calendarProperties.headerColor = color
        return this
    }

    /**
     * Sets whether the date picker dialog's header should be visible
     *
     * @param visibility The visibility
     */
    @Deprecated("Use setHeaderVisibility", ReplaceWith("setHeaderVisibility(visibility)"))
    fun headerVisibility(visibility: Int) = setHeaderVisibility(visibility)

    fun setHeaderVisibility(visibility: Int): DatePickerBuilder {
        calendarProperties.headerVisibility = visibility
        return this
    }

    /**
     * Sets whether the date picker abbreviations bar should be visible
     *
     * @param visibility The visibility
     */
    @Deprecated("Use setAbbreviationsBarVisibility", ReplaceWith("setAbbreviationsBarVisibility(visibility)"))
    fun abbreviationsBarVisibility(visibility: Int) = setAbbreviationsBarVisibility(visibility)

    fun setAbbreviationsBarVisibility(visibility: Int): DatePickerBuilder {
        calendarProperties.abbreviationsBarVisibility = visibility
        return this
    }

    /**
     * Sets the date picker dialog header's label color
     *
     * @param color The header label color as a color resource
     */
    @Deprecated("Use setHeaderLabelColor", ReplaceWith("setHeaderLabelColor(color)"))
    fun headerLabelColor(@ColorRes color: Int) = setHeaderLabelColor(color)

    fun setHeaderLabelColor(@ColorRes color: Int): DatePickerBuilder {
        calendarProperties.headerLabelColor = color
        return this
    }

    /**
     * Sets the drawable to use for the previous button in the date picker dialog
     *
     * @param drawable The drawable to use as a drawable resource
     */
    @Deprecated("Use setPreviousButtonSrc", ReplaceWith("setPreviousButtonSrc(drawable)"))
    fun previousButtonSrc(@DrawableRes drawable: Int) = setPreviousButtonSrc(drawable)

    fun setPreviousButtonSrc(@DrawableRes drawable: Int): DatePickerBuilder {
        calendarProperties.previousButtonSrc = ContextCompat.getDrawable(context, drawable)
        return this
    }

    /**
     * Sets the drawable to use for the forward button in the date picker dialog
     *
     * @param drawable The drawable to use as a drawable resource
     */
    @Deprecated("Use setForwardButtonSrc", ReplaceWith("setForwardButtonSrc(drawable)"))
    fun forwardButtonSrc(@DrawableRes drawable: Int) = setForwardButtonSrc(drawable)

    fun setForwardButtonSrc(@DrawableRes drawable: Int): DatePickerBuilder {
        calendarProperties.forwardButtonSrc = ContextCompat.getDrawable(context, drawable)
        return this
    }

    /**
     * Sets the color of the selection circle in the date picker dialog
     *
     * @param color The color to use as a color resource
     */
    @Deprecated("Use setSelectionColor", ReplaceWith("setSelectionColor(color)"))
    fun selectionColor(@ColorRes color: Int) = setSelectionColor(color)

    fun setSelectionColor(@ColorRes color: Int): DatePickerBuilder {
        calendarProperties.selectionColor = ContextCompat.getColor(context, color)
        return this
    }

    /**
     * Sets the color of the today number in the date picker dialog
     *
     * @param color The color to use as a color resource
     */
    @Deprecated("Use setTodayLabelColor", ReplaceWith("setTodayLabelColor(color)"))
    fun todayLabelColor(@ColorRes color: Int) = setTodayLabelColor(color)

    fun setTodayLabelColor(@ColorRes color: Int): DatePickerBuilder {
        calendarProperties.todayLabelColor = ContextCompat.getColor(context, color)
        return this
    }

    /**
     * Sets the color of the highlighted days label
     *
     * @param color The color to use as a color resource
     */
    @Deprecated("Use setHighlightedDaysLabelsColor", ReplaceWith("setHighlightedDaysLabelsColor(color)"))
    fun highlightedDaysLabelsColor(@ColorRes color: Int) = setHighlightedDaysLabelsColor(color)

    fun setHighlightedDaysLabelsColor(@ColorRes color: Int): DatePickerBuilder {
        calendarProperties.highlightedDaysLabelsColor = ContextCompat.getColor(context, color)
        return this
    }

    /**
     * Sets the color of the dialog buttons in the date picker dialog
     *
     * @param color The color to use as a color resource
     */
    @Deprecated("Use setDialogButtonsColor", ReplaceWith("setDialogButtonsColor(color)"))
    fun dialogButtonsColor(@ColorRes color: Int) = setDialogButtonsColor(color)

    fun setDialogButtonsColor(@ColorRes color: Int): DatePickerBuilder {
        calendarProperties.dialogButtonsColor = color
        return this
    }

    /**
     * Sets the minimum available date of the date picker dialog
     *
     * @param calendar The minimum available date as a {@link Calendar} object
     */
    @Deprecated("Use setMinimumDate", ReplaceWith("setMinimumDate(calendar)"))
    fun minimumDate(calendar: Calendar) = setMinimumDate(calendar)

    fun setMinimumDate(calendar: Calendar): DatePickerBuilder {
        calendarProperties.minimumDate = calendar
        return this
    }

    /**
     * Sets the maximum available date of the date picker dialog
     *
     * @param calendar The maximum available date as a {@link Calendar} object
     */
    @Deprecated("Use setMaximumDate", ReplaceWith("setMaximumDate(calendar)"))
    fun maximumDate(calendar: Calendar) = setMaximumDate(calendar)

    fun setMaximumDate(calendar: Calendar): DatePickerBuilder {
        calendarProperties.maximumDate = calendar
        return this
    }

    /**
     * Sets the list of disabled days in the date picker dialog
     *
     * @param disabledDays The list of disabled days as a {@link List<Calendar>}
     */
    @Deprecated("Use setDisabledDays", ReplaceWith("setDisabledDays(disabledDays)"))
    fun disabledDays(disabledDays: List<Calendar>) = setDisabledDays(disabledDays)

    fun setDisabledDays(disabledDays: List<Calendar>): DatePickerBuilder {
        calendarProperties.disabledDays = disabledDays
        return this
    }

    /**
     * Sets the list of highlighted days in the date picker dialog
     *
     * @param disabledDays The list of disabled days as a {@link List<Calendar>}
     */
    @Deprecated("Use setHighlightedDays", ReplaceWith("setHighlightedDays(highlightedDays)"))
    fun highlightedDays(highlightedDays: List<Calendar>) = setHighlightedDays(highlightedDays)

    fun setHighlightedDays(highlightedDays: List<Calendar>): DatePickerBuilder {
        calendarProperties.highlightedDays = highlightedDays
        return this
    }

    /**
     * Sets the previous page change listener which is called when scrolling to the previous page
     *
     * @param listener The previous page change listener to use
     */
    @Deprecated("Use setPreviousPageChangeListener", ReplaceWith("setPreviousPageChangeListener(listener)"))
    fun previousPageChangeListener(listener: OnCalendarPageChangeListener) = setPreviousPageChangeListener(listener)

    fun setPreviousPageChangeListener(listener: OnCalendarPageChangeListener): DatePickerBuilder {
        calendarProperties.onPreviousPageChangeListener = listener
        return this
    }

    /**
     * Sets the forward page change listener which is called when scrolling to the next page
     *
     * @param listener The forward page change listener to use
     */
    @Deprecated("Use setForwardPageChangeListener", ReplaceWith("setForwardPageChangeListener(listener)"))
    fun forwardPageChangeListener(listener: OnCalendarPageChangeListener) = setForwardPageChangeListener(listener)

    fun setForwardPageChangeListener(listener: OnCalendarPageChangeListener): DatePickerBuilder {
        calendarProperties.onForwardPageChangeListener = listener
        return this
    }

    /**
     * Sets the color of the disabled days numbers
     *
     * @param color The color to use as a color resource
     */
    @Deprecated("Use setDisabledDaysLabelsColor", ReplaceWith("setDisabledDaysLabelsColor(color)"))
    fun disabledDaysLabelsColor(@ColorRes color: Int) = setDisabledDaysLabelsColor(color)

    fun setDisabledDaysLabelsColor(@ColorRes color: Int): DatePickerBuilder {
        calendarProperties.disabledDaysLabelsColor = ContextCompat.getColor(context, color)
        return this
    }

    /**
     * Sets the color of the bar with the day symbols
     *
     * @param color The color to use as a color resource
     */
    @Deprecated("Use setAbbreviationsBarColor", ReplaceWith("setAbbreviationsBarColor(color)"))
    fun abbreviationsBarColor(@ColorRes color: Int) = setAbbreviationsBarColor(color)

    fun setAbbreviationsBarColor(@ColorRes color: Int): DatePickerBuilder {
        calendarProperties.abbreviationsBarColor = ContextCompat.getColor(context, color)
        return this
    }

    /**
     * Sets the color of the calendar background
     *
     * @param color The color to use as a color resource
     */
    @Deprecated("Use setPagesColor", ReplaceWith("setPagesColor(color)"))
    fun pagesColor(@ColorRes color: Int) = setPagesColor(color)

    fun setPagesColor(@ColorRes color: Int): DatePickerBuilder {
        calendarProperties.pagesColor = ContextCompat.getColor(context, color)
        return this
    }

    /**
     * Sets the color of the symbol labels
     *
     * @param color The color to use as a color resource
     */
    @Deprecated("Use setAbbreviationsLabelsColor", ReplaceWith("setAbbreviationsLabelsColor(color)"))
    fun abbreviationsLabelsColor(@ColorRes color: Int) = setAbbreviationsLabelsColor(color)

    fun setAbbreviationsLabelsColor(@ColorRes color: Int): DatePickerBuilder {
        calendarProperties.abbreviationsLabelsColor = ContextCompat.getColor(context, color)
        return this
    }

    /**
     * Sets the color of the day numbers
     *
     * @param color The color to use as a color resource
     */
    @Deprecated("Use setDaysLabelsColor", ReplaceWith("setDaysLabelsColor(color)"))
    fun daysLabelsColor(color: Int) = setDaysLabelsColor(color)

    fun setDaysLabelsColor(color: Int): DatePickerBuilder {
        calendarProperties.daysLabelsColor = ContextCompat.getColor(context, color)
        return this
    }

    /**
     * Sets the color of the label in the selection circle
     *
     * @param color The color to use as a color resource
     */
    @Deprecated("Use setSelectionLabelColor", ReplaceWith("setSelectionLabelColor(color)"))
    fun selectionLabelColor(color: Int) = setSelectionLabelColor(color)

    fun setSelectionLabelColor(color: Int): DatePickerBuilder {
        calendarProperties.selectionLabelColor = ContextCompat.getColor(context, color)
        return this
    }

    /**
     * Sets the color of visible day numbers from the previous to the next month pages
     *
     * @param color The color to use as a color resource
     */
    @Deprecated("Use setAnotherMonthsDaysLabelsColor", ReplaceWith("setAnotherMonthsDaysLabelsColor(color)"))
    fun anotherMonthsDaysLabelsColor(color: Int) = setAnotherMonthsDaysLabelsColor(color)

    fun setAnotherMonthsDaysLabelsColor(color: Int): DatePickerBuilder {
        calendarProperties.anotherMonthsDaysLabelsColor = ContextCompat.getColor(context, color)
        return this
    }

    /**
     * Sets the list of selected days in the calendar
     *
     * @param selectedDays The list of selected days as a {@link List<Calendar>}
     */
    @Deprecated("Use setSelectedDays", ReplaceWith("setSelectedDays(selectedDays)"))
    fun selectedDays(selectedDays: List<Calendar>) = setSelectedDays(selectedDays)

    fun setSelectedDays(selectedDays: List<Calendar>): DatePickerBuilder {
        calendarProperties.setSelectDays(selectedDays)
        return this
    }

    /**
     * Sets the maximum number of selectable days in range
     *
     * @param maximumDaysRange The number of maximum selectable days in range
     * @return The builder object to allow for chaining of methods
     */
    fun setMaximumDaysRange(maximumDaysRange: Int): DatePickerBuilder {
        calendarProperties.maximumDaysRange = maximumDaysRange
        return this
    }

    /**
     * Sets the maximum number of selectable days in range
     *
     * @param todayColor The number of maximum selectable days in range
     * @return The builder object to allow for chaining of methods
     */
    fun setTodayColor(todayColor: Int): DatePickerBuilder {
        calendarProperties.todayColor = todayColor
        return this
    }

    /**
     * Sets whether the date picker navigation buttons should be visible
     *
     * @param visibility The visibility
     * @return The builder object to allow for chaining of methods
     */
    fun setNavigationVisibility(visibility: Int): DatePickerBuilder {
        calendarProperties.navigationVisibility = visibility
        return this
    }

    /**
     * Disables day selection
     *
     * @param isDisabled
     * @return The builder object to allow for chaining of methods
     */
    fun setSelectionDisabled(isDisabled: Boolean): DatePickerBuilder {
        calendarProperties.selectionDisabled = isDisabled
        return this
    }


    /**
     * This method is used to set a list of events displayed in calendar cells,
     * visible as images under the day number.
     *
     * @param eventDays List of EventDay objects
     * @see EventDay
     *
     * @return The builder object to allow for chaining of methods
     */
    fun setEvents(eventDays: List<EventDay>): DatePickerBuilder {
        if (eventDays.isNotEmpty()) {
            calendarProperties.eventsEnabled = true
            calendarProperties.eventDays = eventDays
        }
        return this
    }
}
