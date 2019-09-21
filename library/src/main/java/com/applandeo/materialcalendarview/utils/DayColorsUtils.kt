@file:JvmName("DayColorsUtils")

package com.applandeo.materialcalendarview.utils

import android.graphics.Typeface
import android.widget.TextView
import com.applandeo.materialcalendarview.R
import java.util.*

/**
 * This class is used to set a style of calendar cells.
 *
 * Created by Applandeo Team.
 */

/**
 * This is general method which sets a color of the text, font type and a background of a TextView object.
 * It is used to set day cell (numbers) style.
 *
 * @param this   TextView containing a day number
 * @param textColor  A resource of a color of the day number
 * @param typeface   A type of text style, can be set as NORMAL or BOLD
 * @param background A resource of a background drawable
 */
fun TextView.setDayColors(textColor: Int, typeface: Int, background: Int) =
        this.apply {
            setTypeface(null, typeface)
            setTextColor(textColor)
            setBackgroundResource(background)
        }

/**
 * This method sets a color of the text, font type and a background of a TextView object.
 * It is used to set day cell (numbers) style in the case of selected day (when calendar is in
 * the picker mode). It also colors a background of the selection.
 *
 * @param this           TextView containing a day number
 * @param calendarProperties A resource of a selection background color
 */
fun TextView.setSelectedDayColors(calendarProperties: CalendarProperties) {
    this.setDayColors(calendarProperties.selectionLabelColor, Typeface.NORMAL,
            R.drawable.background_color_circle_selector)

    setDayBackgroundColor(this, calendarProperties.selectionColor)
}

/**
 * This method is used to set a color of texts, font types and backgrounds of TextView objects
 * in a current visible month. Visible day labels from previous and forward months are set using
 * setDayColors() method. It also checks if a day number is a day number of today and set it
 * a different color and bold face type.
 *
 * @param this                A calendar instance representing day date
 * @param today              A calendar instance representing today date
 * @param dayLabel           TextView containing a day numberx
 * @param calendarProperties A resource of a color used to mark today day
 */
fun Calendar.setCurrentMonthDayColors(today: Calendar, dayLabel: TextView?,
                                      calendarProperties: CalendarProperties) =
        dayLabel?.let {
            when {
                today == this -> dayLabel.setDayColors(
                        calendarProperties.todayLabelColor,
                        Typeface.BOLD,
                        R.drawable.background_transparent
                )
                calendarProperties.highlightedDays.contains(this) -> dayLabel.setDayColors(
                        calendarProperties.highlightedDaysLabelsColor,
                        Typeface.NORMAL,
                        R.drawable.background_transparent
                )
                else -> dayLabel.setDayColors(
                        calendarProperties.daysLabelsColor,
                        Typeface.NORMAL,
                        R.drawable.background_transparent
                )
            }
        }

private fun setTodayColors(dayLabel: TextView, calendarProperties: CalendarProperties) {
    dayLabel.setDayColors(calendarProperties.todayLabelColor, Typeface.BOLD,
            R.drawable.background_transparent)

    // Sets custom background color for present
    if (calendarProperties.todayLabelColor != 0) {
        dayLabel.setDayColors(calendarProperties.selectionLabelColor, Typeface.NORMAL,
                R.drawable.background_color_circle_selector)
        setDayBackgroundColor(dayLabel, calendarProperties.todayLabelColor)
    }
}

private fun setEventDayColors(day: Calendar, dayLabel: TextView, calendarProperties: CalendarProperties) =
        day.getEventDayWithLabelColor(calendarProperties)?.let { eventDay ->
            dayLabel.setDayColors(eventDay.labelColor,
                    Typeface.NORMAL, R.drawable.background_transparent)
        }

private fun setHighlightedDayColors(dayLabel: TextView, calendarProperties: CalendarProperties) =
        dayLabel.setDayColors(calendarProperties.highlightedDaysLabelsColor,
                Typeface.NORMAL, R.drawable.background_transparent)

private fun setNormalDayColors(dayLabel: TextView, calendarProperties: CalendarProperties) =
        dayLabel.setDayColors(calendarProperties.daysLabelsColor, Typeface.NORMAL,
                R.drawable.background_transparent)

private fun setDayBackgroundColor(dayLabel: TextView, color: Int) =
        dayLabel.background.setColorFilter(color, android.graphics.PorterDuff.Mode.MULTIPLY)
