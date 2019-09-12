package com.applandeo.materialcalendarview.builders;

import android.content.Context;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.DatePicker;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnCalendarPageChangeListener;
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener;
import com.applandeo.materialcalendarview.utils.CalendarProperties;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Mateusz Kornakiewicz on 12.10.2017.
 */
public class DatePickerBuilder {
    private Context mContext;
    private CalendarProperties mCalendarProperties;

    /**
     * Creates a new date picker builder
     *
     * @param context              The context
     * @param onSelectDateListener A listener when a date is selected
     */
    public DatePickerBuilder(Context context, OnSelectDateListener onSelectDateListener) {
        mContext = context;
        mCalendarProperties = new CalendarProperties(context);
        mCalendarProperties.setCalendarType(CalendarView.ONE_DAY_PICKER);
        mCalendarProperties.setOnSelectDateListener(onSelectDateListener);
    }

    /**
     * Returns the built date picker
     *
     * @return The built date picker
     */
    public DatePicker build() {
        return new DatePicker(mContext, mCalendarProperties);
    }

    /**
     * Sets the calendar type of the date picker
     *
     * @param calendarType The calendar type to use
     * @return The builder object to allow for chaining of methods
     * @deprecated Use {@link DatePickerBuilder#setPickerType(int)}
     */
    @Deprecated
    public DatePickerBuilder pickerType(int calendarType) {
        return setPickerType(calendarType);
    }

    /**
     * Sets the calendar type of the date picker
     *
     * @param calendarType The calendar type to use
     * @return The builder object to allow for chaining of methods
     */
    public DatePickerBuilder setPickerType(int calendarType) {
        mCalendarProperties.setCalendarType(calendarType);
        return this;
    }

    /**
     * Sets the initially selected date of the date picker
     *
     * @param calendar The initially selected date as a {@link Calendar} object
     * @return The builder object to allow for chaining of methods
     * @deprecated Use {@link DatePickerBuilder#setDate(Calendar)}
     */
    @Deprecated
    public DatePickerBuilder date(Calendar calendar) {
        return setDate(calendar);
    }

    /**
     * Sets the initially selected date of the date picker
     *
     * @param calendar The initially selected date as a {@link Calendar} object
     * @return The builder object to allow for chaining of methods
     */
    public DatePickerBuilder setDate(Calendar calendar) {
        mCalendarProperties.setCalendar(calendar);
        return this;
    }

    /**
     * Sets the header color of the date picker dialog's header
     *
     * @param color The header color as a color resource
     * @return The builder object to allow for chaining of methods
     * @deprecated Use {@link DatePickerBuilder#setHeaderColor(int)}
     */
    @Deprecated
    public DatePickerBuilder headerColor(@ColorRes int color) {
        return setHeaderColor(color);
    }

    /**
     * Sets the header color of the date picker dialog's header
     *
     * @param color The header color as a color resource
     * @return The builder object to allow for chaining of methods
     */
    public DatePickerBuilder setHeaderColor(@ColorRes int color) {
        mCalendarProperties.setHeaderColor(color);
        return this;
    }

    /**
     * Sets whether the date picker dialog's header should be visible
     *
     * @param visibility The visibility
     * @return The builder object to allow for chaining of methods
     * @deprecated Use {@link DatePickerBuilder#setHeaderVisibility(int)}
     */
    @Deprecated
    public DatePickerBuilder headerVisibility(int visibility) {
        return setHeaderVisibility(visibility);
    }

    /**
     * Sets whether the date picker dialog's header should be visible
     *
     * @param visibility The visibility
     * @return The builder object to allow for chaining of methods
     */
    public DatePickerBuilder setHeaderVisibility(int visibility) {
        mCalendarProperties.setHeaderVisibility(visibility);
        return this;
    }

    /**
     * Sets whether the date picker dialog's abbreviations bar should be visible
     *
     * @param visibility The visibility
     * @return The builder object to allow for chaining of methods
     */
    public DatePickerBuilder abbreviationsBarVisibility(int visibility) {
        mCalendarProperties.setAbbreviationsBarVisibility(visibility);
        return this;
    }

    /**
     * Sets the date picker dialog header's label color
     *
     * @param color The header label color as a color resource
     * @return The builder object to allow for chaining of methods
     * @deprecated Use {@link DatePickerBuilder#setHeaderLabelColor(int)}
     */
    @Deprecated
    public DatePickerBuilder headerLabelColor(@ColorRes int color) {
        return setHeaderLabelColor(color);
    }

    /**
     * Sets the date picker dialog header's label color
     *
     * @param color The header label color as a color resource
     * @return The builder object to allow for chaining of methods
     */
    public DatePickerBuilder setHeaderLabelColor(@ColorRes int color) {
        mCalendarProperties.setHeaderLabelColor(color);
        return this;
    }

    /**
     * Sets the drawable to use for the previous button in the date picker dialog
     *
     * @param drawable The drawable to use as a drawable resource
     * @return The builder object to allow for chaining of methods
     * @deprecated Use {@link DatePickerBuilder#setPreviousButtonSrc(int)}
     */
    @Deprecated
    public DatePickerBuilder previousButtonSrc(@DrawableRes int drawable) {
        return setPreviousButtonSrc(drawable);
    }

    /**
     * Sets the drawable to use for the previous button in the date picker dialog
     *
     * @param drawable The drawable to use as a drawable resource
     * @return The builder object to allow for chaining of methods
     */
    public DatePickerBuilder setPreviousButtonSrc(@DrawableRes int drawable) {
        mCalendarProperties.setPreviousButtonSrc(ContextCompat.getDrawable(mContext, drawable));
        return this;
    }

    /**
     * Sets the drawable to use for the forward button in the date picker dialog
     *
     * @param drawable The drawable to use as a drawable resource
     * @return The builder object to allow for chaining of methods
     * @deprecated Use {@link DatePickerBuilder#setForwardButtonSrc(int)}
     */
    @Deprecated
    public DatePickerBuilder forwardButtonSrc(@DrawableRes int drawable) {
        return setForwardButtonSrc(drawable);
    }

    /**
     * Sets the drawable to use for the forward button in the date picker dialog
     *
     * @param drawable The drawable to use as a drawable resource
     * @return The builder object to allow for chaining of methods
     */
    public DatePickerBuilder setForwardButtonSrc(@DrawableRes int drawable) {
        mCalendarProperties.setForwardButtonSrc(ContextCompat.getDrawable(mContext, drawable));
        return this;
    }

    /**
     * Sets the color of the selection circle in the date picker dialog
     *
     * @param color The color to use as a color resource
     * @return The builder object to allow for chaining of methods
     * @deprecated Use {@link DatePickerBuilder#setSelectionColor(int)}
     */
    @Deprecated
    public DatePickerBuilder selectionColor(@ColorRes int color) {
        return setSelectionColor(color);
    }

    /**
     * Sets the color of the selection circle in the date picker dialog
     *
     * @param color The color to use as a color resource
     * @return The builder object to allow for chaining of methods
     */
    public DatePickerBuilder setSelectionColor(@ColorRes int color) {
        mCalendarProperties.setSelectionColor(ContextCompat.getColor(mContext, color));
        return this;
    }

    /**
     * Sets the color of the today background
     *
     * @param color The color to use as a color resource
     * @return The builder object to allow for chaining of methods
     * @deprecated Use {@link DatePickerBuilder#setTodayLabelColor(int)}
     */
    @Deprecated
    public DatePickerBuilder todayLabelColor(@ColorRes int color) {
        return setTodayLabelColor(color);
    }

    /**
     * Sets the color of the today number in the date picker dialog
     *
     * @param color The color to use as a color resource
     * @return The builder object to allow for chaining of methods
     */
    public DatePickerBuilder setTodayLabelColor(@ColorRes int color) {
        mCalendarProperties.setTodayLabelColor(ContextCompat.getColor(mContext, color));
        return this;
    }

    /**
     * Sets the color of the highlighted days in the date picker dialog
     *
     * @param color The color to use as a color resource
     * @return The builder object to allow for chaining of methods
     */
    public DatePickerBuilder highlightedDaysLabelsColor(@ColorRes int color) {
        mCalendarProperties.setHighlightedDaysLabelsColor(ContextCompat.getColor(mContext, color));
        return this;
    }

    /**
     * Sets the color of the dialog buttons in the date picker dialog
     *
     * @param color The color to use as a color resource
     * @return The builder object to allow for chaining of methods
     * @deprecated Use {@link DatePickerBuilder#setDialogButtonsColor(int)}
     */
    @Deprecated
    public DatePickerBuilder dialogButtonsColor(@ColorRes int color) {
        return setDialogButtonsColor(color);
    }

    /**
     * Sets the color of the dialog buttons in the date picker dialog
     *
     * @param color The color to use as a color resource
     * @return The builder object to allow for chaining of methods
     */
    public DatePickerBuilder setDialogButtonsColor(@ColorRes int color) {
        mCalendarProperties.setDialogButtonsColor(color);
        return this;
    }

    /**
     * Sets the minimum available date of the date picker dialog
     *
     * @param calendar The minimum available date as a {@link Calendar} object
     * @return The builder object to allow for chaining of methods
     * @deprecated Use {@link DatePickerBuilder#setMinimumDate(Calendar)}
     */
    @Deprecated
    public DatePickerBuilder minimumDate(Calendar calendar) {
        return setMinimumDate(calendar);
    }

    /**
     * Sets the minimum available date of the date picker dialog
     *
     * @param calendar The minimum available date as a {@link Calendar} object
     * @return The builder object to allow for chaining of methods
     */
    public DatePickerBuilder setMinimumDate(Calendar calendar) {
        mCalendarProperties.setMinimumDate(calendar);
        return this;
    }

    /**
     * Sets the maximum available date of the date picker dialog
     *
     * @param calendar The maximum available date as a {@link Calendar} object
     * @return The builder object to allow for chaining of methods
     * @deprecated Use {@link DatePickerBuilder#setMaximumDate(Calendar)}
     */
    @Deprecated
    public DatePickerBuilder maximumDate(Calendar calendar) {
        return setMaximumDate(calendar);
    }

    /**
     * Sets the maximum available date of the date picker dialog
     *
     * @param calendar The maximum available date as a {@link Calendar} object
     * @return The builder object to allow for chaining of methods
     */
    public DatePickerBuilder setMaximumDate(Calendar calendar) {
        mCalendarProperties.setMaximumDate(calendar);
        return this;
    }

    /**
     * Sets the list of disabled days in the date picker dialog
     *
     * @param disabledDays The list of disabled days as a {@link List<Calendar>}
     * @return The builder object to allow for chaining of methods
     * @deprecated Use {@link DatePickerBuilder#setDisabledDays(List)}
     */
    @Deprecated
    public DatePickerBuilder disabledDays(List<Calendar> disabledDays) {
        return setDisabledDays(disabledDays);
    }

    /**
     * Sets the list of disabled days in the date picker dialog
     *
     * @param disabledDays The list of disabled days as a {@link List<Calendar>}
     * @return The builder object to allow for chaining of methods
     */
    public DatePickerBuilder setDisabledDays(List<Calendar> disabledDays) {
        mCalendarProperties.setDisabledDays(disabledDays);
        return this;
    }

    public DatePickerBuilder highlightedDays(List<Calendar> highlightedDays) {
        mCalendarProperties.setHighlightedDays(highlightedDays);
        return this;
    }

    /**
     * Sets the previous page change listener which is called when scrolling to the previous page
     *
     * @param listener The previous page change listener to use
     * @return The builder object to allow for chaining of methods
     * @deprecated Use {@link DatePickerBuilder#setPreviousPageChangeListener(OnCalendarPageChangeListener)}
     */
    @Deprecated
    public DatePickerBuilder previousPageChangeListener(OnCalendarPageChangeListener listener) {
        return setPreviousPageChangeListener(listener);
    }

    /**
     * Sets the previous page change listener which is called when scrolling to the previous page
     *
     * @param listener The previous page change listener to use
     * @return The builder object to allow for chaining of methods
     */
    public DatePickerBuilder setPreviousPageChangeListener(OnCalendarPageChangeListener listener) {
        mCalendarProperties.setOnPreviousPageChangeListener(listener);
        return this;
    }

    /**
     * Sets the forward page change listener which is called when scrolling to the next page
     *
     * @param listener The forward page change listener to use
     * @return The builder object to allow for chaining of methods
     * @deprecated Use {@link DatePickerBuilder#setForwardPageChangeListener(OnCalendarPageChangeListener)}
     */
    @Deprecated
    public DatePickerBuilder forwardPageChangeListener(OnCalendarPageChangeListener listener) {
        return setForwardPageChangeListener(listener);
    }

    /**
     * Sets the forward page change listener which is called when scrolling to the next page
     *
     * @param listener The forward page change listener to use
     * @return The builder object to allow for chaining of methods
     */
    public DatePickerBuilder setForwardPageChangeListener(OnCalendarPageChangeListener listener) {
        mCalendarProperties.setOnForwardPageChangeListener(listener);
        return this;
    }

    /**
     * Sets the color of the disabled days numbers
     *
     * @param color The color to use as a color resource
     * @return The builder object to allow for chaining of methods
     * @deprecated Use {@link DatePickerBuilder#setDisabledDaysLabelsColor(int)}
     */
    @Deprecated
    public DatePickerBuilder disabledDaysLabelsColor(@ColorRes int color) {
        return setDisabledDaysLabelsColor(color);
    }

    /**
     * Sets the color of the disabled days numbers
     *
     * @param color The color to use as a color resource
     * @return The builder object to allow for chaining of methods
     */
    public DatePickerBuilder setDisabledDaysLabelsColor(@ColorRes int color) {
        mCalendarProperties.setDisabledDaysLabelsColor(ContextCompat.getColor(mContext, color));
        return this;
    }

    /**
     * Sets the color of the bar with the day symbols
     *
     * @param color The color to use as a color resource
     * @return The builder object to allow for chaining of methods
     * @deprecated Use {@link DatePickerBuilder#setAbbreviationsBarColor(int)}
     */
    @Deprecated
    public DatePickerBuilder abbreviationsBarColor(@ColorRes int color) {
        return setAbbreviationsBarColor(color);
    }

    /**
     * Sets the color of the bar with the day symbols
     *
     * @param color The color to use as a color resource
     * @return The builder object to allow for chaining of methods
     */
    public DatePickerBuilder setAbbreviationsBarColor(@ColorRes int color) {
        mCalendarProperties.setAbbreviationsBarColor(ContextCompat.getColor(mContext, color));
        return this;
    }

    /**
     * Sets the color of the calendar background
     *
     * @param color The color to use as a color resource
     * @return The builder object to allow for chaining of methods
     * @deprecated Use {@link DatePickerBuilder#setPagesColor(int)}
     */
    @Deprecated
    public DatePickerBuilder pagesColor(@ColorRes int color) {
        return setPagesColor(color);
    }

    /**
     * Sets the color of the calendar background
     *
     * @param color The color to use as a color resource
     * @return The builder object to allow for chaining of methods
     */
    public DatePickerBuilder setPagesColor(@ColorRes int color) {
        mCalendarProperties.setPagesColor(ContextCompat.getColor(mContext, color));
        return this;
    }

    /**
     * Sets the color of the symbol labels
     *
     * @param color The color to use as a color resource
     * @return The builder object to allow for chaining of methods
     * @deprecated Use {@link DatePickerBuilder#setAbbreviationsLabelsColor(int)}
     */
    @Deprecated
    public DatePickerBuilder abbreviationsLabelsColor(@ColorRes int color) {
        return setAbbreviationsLabelsColor(color);
    }

    /**
     * Sets the color of the symbol labels
     *
     * @param color The color to use as a color resource
     * @return The builder object to allow for chaining of methods
     */
    public DatePickerBuilder setAbbreviationsLabelsColor(@ColorRes int color) {
        mCalendarProperties.setAbbreviationsLabelsColor(ContextCompat.getColor(mContext, color));
        return this;
    }

    /**
     * Sets the color of the day numbers
     *
     * @param color The color to use as a color resource
     * @return The builder object to allow for chaining of methods
     * @deprecated Use {@link DatePickerBuilder#setDaysLabelsColor(int)}
     */
    @Deprecated
    public DatePickerBuilder daysLabelsColor(@ColorRes int color) {
        return setDaysLabelsColor(color);
    }

    /**
     * Sets the color of the day numbers
     *
     * @param color The color to use as a color resource
     * @return The builder object to allow for chaining of methods
     */
    public DatePickerBuilder setDaysLabelsColor(@ColorRes int color) {
        mCalendarProperties.setDaysLabelsColor(ContextCompat.getColor(mContext, color));
        return this;
    }

    /**
     * Sets the color of the label in the selection circle
     *
     * @param color The color to use as a color resource
     * @return The builder object to allow for chaining of methods
     * @deprecated Use {@link DatePickerBuilder#setSelectionLabelColor(int)}
     */
    @Deprecated
    public DatePickerBuilder selectionLabelColor(@ColorRes int color) {
        return setSelectionLabelColor(color);
    }

    /**
     * Sets the color of the label in the selection circle
     *
     * @param color The color to use as a color resource
     * @return The builder object to allow for chaining of methods
     */
    public DatePickerBuilder setSelectionLabelColor(@ColorRes int color) {
        mCalendarProperties.setSelectionLabelColor(ContextCompat.getColor(mContext, color));
        return this;
    }

    /**
     * Sets the color of visible day numbers from the previous to the next month pages
     *
     * @param color The color to use as a color resource
     * @return The builder object to allow for chaining of methods
     * @deprecated Use {@link DatePickerBuilder#setAnotherMonthsDaysLabelsColor(int)}
     */
    @Deprecated
    public DatePickerBuilder anotherMonthsDaysLabelsColor(@ColorRes int color) {
        return setAnotherMonthsDaysLabelsColor(color);
    }

    /**
     * Sets the color of visible day numbers from the previous to the next month pages
     *
     * @param color The color to use as a color resource
     * @return The builder object to allow for chaining of methods
     */
    public DatePickerBuilder setAnotherMonthsDaysLabelsColor(@ColorRes int color) {
        mCalendarProperties.setAnotherMonthsDaysLabelsColor(ContextCompat.getColor(mContext, color));
        return this;
    }

    /**
     * Sets the list of selected days in the calendar
     *
     * @param selectedDays The list of selected days as a {@link List<Calendar>}
     * @return The builder object to allow for chaining of methods
     * @deprecated Use {@link DatePickerBuilder#setSelectedDays(List)}
     */
    @Deprecated
    public DatePickerBuilder selectedDays(List<Calendar> selectedDays) {
        return setSelectedDays(selectedDays);
    }

    /**
     * Sets the list of selected days in the calendar
     *
     * @param selectedDays The list of selected days as a {@link List<Calendar>}
     * @return The builder object to allow for chaining of methods
     */
    public DatePickerBuilder setSelectedDays(List<Calendar> selectedDays) {
        mCalendarProperties.setSelectedDays(selectedDays);
        return this;
    }

    /**
     * Sets the maximum number of selectable days in range
     *
     * @param maximumDaysRange The number of maximum selectable days in range
     * @return The builder object to allow for chaining of methods
     */
    public DatePickerBuilder setMaximumDaysRange(int maximumDaysRange) {
        mCalendarProperties.setMaximumDaysRange(maximumDaysRange);
        return this;
    }

    /**
     * Sets the maximum number of selectable days in range
     *
     * @param todayColor The number of maximum selectable days in range
     * @return The builder object to allow for chaining of methods
     */
    public DatePickerBuilder setTodayColor(int todayColor) {
        mCalendarProperties.setTodayColor(todayColor);
        return this;
    }

    /**
     * Sets whether the date picker navigation buttons should be visible
     *
     * @param visibility The visibility
     * @return The builder object to allow for chaining of methods
     */
    public DatePickerBuilder setNavigationVisibility(int visibility) {
        mCalendarProperties.setNavigationVisibility(visibility);
        return this;
    }


    /**
     * This method is used to set a list of events displayed in calendar cells,
     * visible as images under the day number.
     *
     * @param eventDays List of EventDay objects
     * @see EventDay
     * @return The builder object to allow for chaining of methods
     */
    public DatePickerBuilder setEvents(List<EventDay> eventDays) {
      if (eventDays != null) {
        mCalendarProperties.setEventsEnabled(true);
        mCalendarProperties.setEventDays(eventDays);
      }

      return this;
    }
}
