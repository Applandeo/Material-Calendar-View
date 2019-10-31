package com.applandeo.materialcalendarview.utils

import android.graphics.drawable.Drawable

import androidx.constraintlayout.widget.ConstraintLayout

import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView

import com.applandeo.materialcalendarview.R

import java.util.ArrayList

/**
 * Created by Mateusz Kornakiewicz on 04.01.2018.
 */

object AppearanceUtils {

    fun setAbbreviationsLabels(view: View, color: Int, firstDayOfWeek: Int) {
        val labels = ArrayList<TextView>()
        labels.apply {
            add(view.findViewById<View>(R.id.mondayLabel) as TextView)
            add(view.findViewById<View>(R.id.tuesdayLabel) as TextView)
            add(view.findViewById<View>(R.id.wednesdayLabel) as TextView)
            add(view.findViewById<View>(R.id.thursdayLabel) as TextView)
            add(view.findViewById<View>(R.id.fridayLabel) as TextView)
            add(view.findViewById<View>(R.id.saturdayLabel) as TextView)
            add(view.findViewById<View>(R.id.sundayLabel) as TextView)
        }

        val abbreviations = view.context.resources.getStringArray(R.array.material_calendar_day_abbreviations_array)
        for (i in 0..6) {
            val label = labels[i]
            label.text = abbreviations[(i + firstDayOfWeek - 1) % 7]

            if (color != 0) {
                label.setTextColor(color)
            }
        }
    }

    fun setHeaderColor(view: View, color: Int) {
        if (color == 0) return

        (view.findViewById<View>(R.id.calendarHeader) as ConstraintLayout).setBackgroundColor(color)
    }

    fun setHeaderLabelColor(view: View, color: Int) {
        if (color == 0) return

        (view.findViewById<View>(R.id.currentDateLabel) as TextView).setTextColor(color)
    }

    fun setAbbreviationsBarColor(view: View, color: Int) {
        if (color == 0) return

        view.findViewById<View>(R.id.abbreviationsBar).setBackgroundColor(color)
    }

    fun setPagesColor(view: View, color: Int) {
        if (color == 0) return

        view.findViewById<View>(R.id.calendarViewPager).setBackgroundColor(color)
    }

    fun setPreviousButtonImage(view: View, drawable: Drawable?) {
        if (drawable == null) return

        (view.findViewById<View>(R.id.previousButton) as ImageButton).setImageDrawable(drawable)
    }

    fun setForwardButtonImage(view: View, drawable: Drawable?) {
        if (drawable == null) return

        (view.findViewById<View>(R.id.forwardButton) as ImageButton).setImageDrawable(drawable)
    }

    fun setHeaderVisibility(view: View, visibility: Int) {
        view.findViewById<ConstraintLayout>(R.id.calendarHeader).visibility = visibility
    }

    fun setAbbreviationsBarVisibility(view: View, visibility: Int) {
        view.findViewById<LinearLayout>(R.id.abbreviationsBar).visibility = visibility
    }
}
