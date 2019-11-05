@file:JvmName("AppearanceUtils")

package com.applandeo.materialcalendarview.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.applandeo.materialcalendarview.R
import kotlinx.android.synthetic.main.calendar_view.view.*
import java.util.*

/**
 * Created by Mateusz Kornakiewicz on 04.01.2018.
 */

fun View.setAbbreviationsLabels(color: Int, firstDayOfWeek: Int) {
    val labels = mutableListOf<TextView>()
    labels.apply {
        add(mondayLabel)
        add(tuesdayLabel)
        add(wednesdayLabel)
        add(thursdayLabel)
        add(fridayLabel)
        add(saturdayLabel)
        add(sundayLabel)
    }

    val abbreviations = this.context.resources.getStringArray(R.array.material_calendar_day_abbreviations_array)
    for (i in 0..6) {
        val label = labels[i]
        label.text = abbreviations[(i + firstDayOfWeek - 1) % 7]

        if (color != 0) {
            label.setTextColor(color)
        }
    }
}

fun View.setHeaderColor(color: Int) {
    if (color == 0) return
    (this.findViewById<View>(R.id.calendarHeader) as ConstraintLayout).setBackgroundColor(color)
}

fun View.setHeaderLabelColor(color: Int) {
    if (color == 0) return
    (this.findViewById<View>(R.id.currentDateLabel) as TextView).setTextColor(color)
}

fun View.setAbbreviationsBarColor(color: Int) {
    if (color == 0) return
    this.findViewById<View>(R.id.abbreviationsBar).setBackgroundColor(color)
}

fun View.setPagesColor(color: Int) {
    if (color == 0) return
    this.findViewById<View>(R.id.calendarViewPager).setBackgroundColor(color)
}

fun View.setPreviousButtonImage(drawable: Drawable?) {
    if (drawable == null) return
    (this.findViewById<View>(R.id.previousButton) as ImageButton).setImageDrawable(drawable)
}

fun View.setForwardButtonImage(drawable: Drawable?) {
    if (drawable == null) return
    (this.findViewById<View>(R.id.forwardButton) as ImageButton).setImageDrawable(drawable)
}

fun View.setHeaderVisibility(visibility: Int) {
    this.findViewById<ConstraintLayout>(R.id.calendarHeader).visibility = visibility
}

fun View.setAbbreviationsBarVisibility(visibility: Int) {
    this.findViewById<LinearLayout>(R.id.abbreviationsBar).visibility = visibility
}
