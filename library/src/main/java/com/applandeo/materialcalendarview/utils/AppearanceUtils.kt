@file:JvmName("AppearanceUtils")

package com.applandeo.materialcalendarview.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.TextView
import com.applandeo.materialcalendarview.R
import kotlinx.android.synthetic.main.calendar_view.view.*

/**
 * Created by Applandeo Team.
 */

fun View.setAbbreviationsLabels(color: Int, firstDayOfWeek: Int) {
    val labels = listOf<TextView>(
            mondayLabel,
            tuesdayLabel,
            wednesdayLabel,
            thursdayLabel,
            fridayLabel,
            saturdayLabel,
            sundayLabel)

    val abbreviations = this.context.resources.getStringArray(R.array.material_calendar_day_abbreviations_array)

    labels.forEachIndexed { index, label ->
        label.text = abbreviations[(index + firstDayOfWeek - 1) % 7]
        label.setTextColor(color)
    }
}

fun View.setHeaderColor(color: Int) {
    if (color == 0) return
    this.calendarHeader.setBackgroundColor(color)
}

fun View.setHeaderLabelColor(color: Int) {
    if (color == 0) return
    this.currentDateLabel.setTextColor(color)
}

fun View.setAbbreviationsBarColor(color: Int) {
    if (color == 0) return
    this.abbreviationsBar.setBackgroundColor(color)
}

fun View.setPagesColor(color: Int) {
    if (color == 0) {
        return
    }
    this.calendarViewPager.setBackgroundColor(color)
}

fun View.setPreviousButtonImage(drawable: Drawable?) {
    if (drawable == null) return
    this.previousButton.setImageDrawable(drawable)
}

fun View.setForwardButtonImage(drawable: Drawable?) {
    if (drawable == null) return
    this.forwardButton.setImageDrawable(drawable)
}

fun View.setHeaderVisibility(visibility: Int) {
    this.calendarHeader.visibility = visibility
}

fun View.setAbbreviationsBarVisibility(visibility: Int) {
    this.abbreviationsBar.visibility = visibility
}
