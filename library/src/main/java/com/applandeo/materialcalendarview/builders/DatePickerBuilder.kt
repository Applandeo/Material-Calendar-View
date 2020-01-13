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
    private val calendarProperties = CalendarProperties(context)

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

    fun pickerType(calendarType: Int) = also { calendarProperties.calendarType = calendarType }

    @Deprecated("Use pickerType(calendarType)", ReplaceWith("pickerType(calendarType)"))
    fun setPickerType(calendarType: Int) = pickerType(calendarType)

    /**
     * Sets the initially selected date of the date picker
     *
     * @param calendar The initially selected date as a {@link Calendar} object
     */
    fun date(calendar: Calendar) = also { calendarProperties.calendar = calendar }

    @Deprecated("Use date(calendar)", ReplaceWith("date(calendar)"))
    fun setDate(calendar: Calendar) = date(calendar)

    /**
     * Sets the header color of the date picker dialog's header
     *
     * @param color The header color as a color resource
     */
    fun headerColor(@ColorRes color: Int) = also { calendarProperties.headerColor = color }

    @Deprecated("Use headerColor(color)", ReplaceWith("headerColor(color)"))
    fun setHeaderColor(@ColorRes color: Int) = headerColor(color)

    /**
     * Sets whether the date picker dialog's header should be visible
     *
     * @param visibility The visibility
     */
    fun headerVisibility(visibility: Int) = also { calendarProperties.headerVisibility = visibility }

    @Deprecated("Use headerVisibility(visibility)", ReplaceWith("headerVisibility(visibility)"))
    fun setHeaderVisibility(visibility: Int) = headerVisibility(visibility)

    /**
     * Sets whether the date picker abbreviations bar should be visible
     *
     * @param visibility The visibility
     */
    fun abbreviationsBarVisibility(visibility: Int) =
            also { calendarProperties.abbreviationsBarVisibility = visibility }

    @Deprecated("Use abbreviationsBarVisibility(visibility)", ReplaceWith("abbreviationsBarVisibility(visibility)"))
    fun setAbbreviationsBarVisibility(visibility: Int) = abbreviationsBarVisibility(visibility)

    /**
     * Sets the date picker dialog header's label color
     *
     * @param color The header label color as a color resource
     */
    fun headerLabelColor(@ColorRes color: Int) = also { calendarProperties.headerLabelColor = color }

    @Deprecated("Use headerLabelColor(color)", ReplaceWith("headerLabelColor(color)"))
    fun setHeaderLabelColor(@ColorRes color: Int) = headerLabelColor(color)

    /**
     * Sets the drawable to use for the previous button in the date picker dialog
     *
     * @param drawable The drawable to use as a drawable resource
     */
    fun previousButtonSrc(@DrawableRes drawable: Int) =
            also { calendarProperties.previousButtonSrc = ContextCompat.getDrawable(context, drawable) }

    @Deprecated("Use previousButtonSrc(drawable)", ReplaceWith("previousButtonSrc(drawable)"))
    fun setPreviousButtonSrc(@DrawableRes drawable: Int) = previousButtonSrc(drawable)

    /**
     * Sets the drawable to use for the forward button in the date picker dialog
     *
     * @param drawable The drawable to use as a drawable resource
     */
    fun forwardButtonSrc(@DrawableRes drawable: Int) =
            also { calendarProperties.forwardButtonSrc = ContextCompat.getDrawable(context, drawable) }

    @Deprecated("Use forwardButtonSrc(drawable)", ReplaceWith("forwardButtonSrc(drawable)"))
    fun setForwardButtonSrc(@DrawableRes drawable: Int) = forwardButtonSrc(drawable)

    /**
     * Sets the color of the selection circle in the date picker dialog
     *
     * @param color The color to use as a color resource
     */
    fun selectionColor(@ColorRes color: Int) =
            also { calendarProperties.selectionColor = ContextCompat.getColor(context, color) }

    @Deprecated("Use selectionColor(color)", ReplaceWith("selectionColor(color)"))
    fun setSelectionColor(@ColorRes color: Int) = selectionColor(color)

    /**
     * Sets the color of the today number in the date picker dialog
     *
     * @param color The color to use as a color resource
     */
    fun todayLabelColor(@ColorRes color: Int) =
            also { calendarProperties.todayLabelColor = ContextCompat.getColor(context, color) }

    @Deprecated("Use todayLabelColor(color)", ReplaceWith("todayLabelColor(color)"))
    fun setTodayLabelColor(@ColorRes color: Int) = todayLabelColor(color)

    /**
     * Sets the color of the highlighted days label
     *
     * @param color The color to use as a color resource
     */
    fun highlightedDaysLabelsColor(@ColorRes color: Int) =
            also { calendarProperties.highlightedDaysLabelsColor = ContextCompat.getColor(context, color) }

    @Deprecated("Use highlightedDaysLabelsColor(color)", ReplaceWith("highlightedDaysLabelsColor(color)"))
    fun setHighlightedDaysLabelsColor(@ColorRes color: Int) = highlightedDaysLabelsColor(color)

    /**
     * Sets the color of the dialog buttons in the date picker dialog
     *
     * @param color The color to use as a color resource
     */
    fun dialogButtonsColor(@ColorRes color: Int) = also { calendarProperties.dialogButtonsColor = color }

    @Deprecated("Use dialogButtonsColor(color)", ReplaceWith("dialogButtonsColor(color)"))
    fun setDialogButtonsColor(@ColorRes color: Int) = dialogButtonsColor(color)

    /**
     * Sets the minimum available date of the date picker dialog
     *
     * @param calendar The minimum available date as a {@link Calendar} object
     */
    fun minimumDate(calendar: Calendar) = also { calendarProperties.minimumDate = calendar }

    @Deprecated("Use minimumDate(calendar)", ReplaceWith("minimumDate(calendar)"))
    fun setMinimumDate(calendar: Calendar) = minimumDate(calendar)

    /**
     * Sets the maximum available date of the date picker dialog
     *
     * @param calendar The maximum available date as a {@link Calendar} object
     */
    fun maximumDate(calendar: Calendar) = also { calendarProperties.maximumDate = calendar }

    @Deprecated("Use maximumDate(calendar)", ReplaceWith("maximumDate(calendar)"))
    fun setMaximumDate(calendar: Calendar) = maximumDate(calendar)

    /**
     * Sets the list of disabled days in the date picker dialog
     *
     * @param disabledDays The list of disabled days as a {@link List<Calendar>}
     */
    fun disabledDays(disabledDays: List<Calendar>) = also { calendarProperties.disabledDays = disabledDays }

    @Deprecated("Use disabledDays(disabledDays)", ReplaceWith("disabledDays(disabledDays)"))
    fun setDisabledDays(disabledDays: List<Calendar>) = disabledDays(disabledDays)

    /**
     * Sets the list of highlighted days in the date picker dialog
     *
     * @param disabledDays The list of disabled days as a {@link List<Calendar>}
     */
    fun highlightedDays(highlightedDays: List<Calendar>) =
            also { calendarProperties.highlightedDays = highlightedDays }

    @Deprecated("Use highlightedDays(highlightedDays)", ReplaceWith("highlightedDays(highlightedDays)"))
    fun setHighlightedDays(highlightedDays: List<Calendar>) = highlightedDays(highlightedDays)

    /**
     * Sets the previous page change listener which is called when scrolling to the previous page
     *
     * @param listener The previous page change listener to use
     */
    fun previousPageChangeListener(listener: OnCalendarPageChangeListener) =
            also { calendarProperties.onPreviousPageChangeListener = listener }

    @Deprecated("Use previousPageChangeListener(listener)", ReplaceWith("previousPageChangeListener(listener)"))
    fun setPreviousPageChangeListener(listener: OnCalendarPageChangeListener) = previousPageChangeListener(listener)

    /**
     * Sets the forward page change listener which is called when scrolling to the next page
     *
     * @param listener The forward page change listener to use
     */
    fun forwardPageChangeListener(listener: OnCalendarPageChangeListener) =
            also { calendarProperties.onForwardPageChangeListener = listener }

    @Deprecated("Use forwardPageChangeListener(listener)", ReplaceWith("forwardPageChangeListener(listener)"))
    fun setForwardPageChangeListener(listener: OnCalendarPageChangeListener) = forwardPageChangeListener(listener)

    /**
     * Sets the color of the disabled days numbers
     *
     * @param color The color to use as a color resource
     */
    fun disabledDaysLabelsColor(@ColorRes color: Int) =
            also { calendarProperties.disabledDaysLabelsColor = ContextCompat.getColor(context, color) }

    @Deprecated("Use disabledDaysLabelsColor(color)", ReplaceWith("disabledDaysLabelsColor(color)"))
    fun setDisabledDaysLabelsColor(@ColorRes color: Int) = disabledDaysLabelsColor(color)

    /**
     * Sets the color of the bar with the day symbols
     *
     * @param color The color to use as a color resource
     */
    fun abbreviationsBarColor(@ColorRes color: Int) =
            also { calendarProperties.abbreviationsBarColor = ContextCompat.getColor(context, color) }

    @Deprecated("Use abbreviationsBarColor(color)", ReplaceWith("abbreviationsBarColor(color)"))
    fun setAbbreviationsBarColor(@ColorRes color: Int) = abbreviationsBarColor(color)

    /**
     * Sets the color of the calendar background
     *
     * @param color The color to use as a color resource
     */
    fun pagesColor(@ColorRes color: Int) =
            also { calendarProperties.pagesColor = ContextCompat.getColor(context, color) }

    @Deprecated("Use pagesColor(color)", ReplaceWith("pagesColor(color)"))
    fun setPagesColor(@ColorRes color: Int) = pagesColor(color)

    /**
     * Sets the color of the symbol labels
     *
     * @param color The color to use as a color resource
     */
    fun abbreviationsLabelsColor(@ColorRes color: Int) =
            also { calendarProperties.abbreviationsLabelsColor = ContextCompat.getColor(context, color) }

    @Deprecated("Use abbreviationsLabelsColor(color)", ReplaceWith("abbreviationsLabelsColor(color)"))
    fun setAbbreviationsLabelsColor(@ColorRes color: Int) = abbreviationsLabelsColor(color)

    /**
     * Sets the color of the day numbers
     *
     * @param color The color to use as a color resource
     */
    fun daysLabelsColor(color: Int) =
            also { calendarProperties.daysLabelsColor = ContextCompat.getColor(context, color) }

    @Deprecated("Use daysLabelsColor(color)", ReplaceWith("daysLabelsColor(color)"))
    fun setDaysLabelsColor(color: Int) = daysLabelsColor(color)

    /**
     * Sets the color of the label in the selection circle
     *
     * @param color The color to use as a color resource
     */
    fun selectionLabelColor(color: Int) =
            also { calendarProperties.selectionLabelColor = ContextCompat.getColor(context, color) }

    @Deprecated("Use selectionLabelColor(color)", ReplaceWith("selectionLabelColor(color)"))
    fun setSelectionLabelColor(color: Int) = selectionLabelColor(color)

    /**
     * Sets the color of visible day numbers from the previous to the next month pages
     *
     * @param color The color to use as a color resource
     */
    fun anotherMonthsDaysLabelsColor(color: Int) =
            also { calendarProperties.anotherMonthsDaysLabelsColor = ContextCompat.getColor(context, color) }

    @Deprecated("Use anotherMonthsDaysLabelsColor(color)", ReplaceWith("anotherMonthsDaysLabelsColor(color)"))
    fun setAnotherMonthsDaysLabelsColor(color: Int) = anotherMonthsDaysLabelsColor(color)

    /**
     * Sets the list of selected days in the calendar
     *
     * @param selectedDays The list of selected days as a {@link List<Calendar>}
     */
    fun selectedDays(selectedDays: List<Calendar>) = also { calendarProperties.setSelectDays(selectedDays) }

    @Deprecated("Use selectedDays(selectedDays)", ReplaceWith("selectedDays(selectedDays)"))
    fun setSelectedDays(selectedDays: List<Calendar>) = selectedDays(selectedDays)

    /**
     * Sets the maximum number of selectable days in range
     *
     * @param maximumDaysRange The number of maximum selectable days in range
     * @return The builder object to allow for chaining of methods
     */
    fun maximumDaysRange(maximumDaysRange: Int) = also { calendarProperties.maximumDaysRange = maximumDaysRange }

    @Deprecated("Use maximumDaysRange(maximumDaysRange)", ReplaceWith("maximumDaysRange(maximumDaysRange)"))
    fun setMaximumDaysRange(maximumDaysRange: Int) = maximumDaysRange(maximumDaysRange)

    /**
     * Sets the maximum number of selectable days in range
     *
     * @param todayColor The number of maximum selectable days in range
     * @return The builder object to allow for chaining of methods
     */
    fun todayColor(todayColor: Int) = also { calendarProperties.todayColor = todayColor }

    @Deprecated("Use todayColor(todayColor)", ReplaceWith("todayColor(todayColor)"))
    fun setTodayColor(todayColor: Int) = todayColor(todayColor)

    /**
     * Sets whether the date picker navigation buttons should be visible
     *
     * @param visibility The visibility
     * @return The builder object to allow for chaining of methods
     */
    fun navigationVisibility(visibility: Int) = also { calendarProperties.navigationVisibility = visibility }

    @Deprecated("Use navigationVisibility(visibility)", ReplaceWith("navigationVisibility(visibility)"))
    fun setNavigationVisibility(visibility: Int) = navigationVisibility(visibility)

    /**
     * Disables day selection
     *
     * @param isDisabled
     * @return The builder object to allow for chaining of methods
     */
    fun selectionDisabled(isDisabled: Boolean) = also { calendarProperties.selectionDisabled = isDisabled }

    @Deprecated("Use selectionDisabled(isDisabled)", ReplaceWith("selectionDisabled(isDisabled)"))
    fun setSelectionDisabled(isDisabled: Boolean) = selectionDisabled(isDisabled)

    /**
     * This method is used to set a list of events displayed in calendar cells,
     * visible as images under the day number.
     *
     * @param eventDays List of EventDay objects
     * @see EventDay
     *
     * @return The builder object to allow for chaining of methods
     */
    fun events(eventDays: List<EventDay>) = also {
        if (eventDays.isNotEmpty()) {
            calendarProperties.eventsEnabled = true
            calendarProperties.eventDays = eventDays
        }
    }

    @Deprecated("Use events(eventDays)", ReplaceWith("events(eventDays)"))
    fun setEvents(eventDays: List<EventDay>) = events(eventDays)
}
