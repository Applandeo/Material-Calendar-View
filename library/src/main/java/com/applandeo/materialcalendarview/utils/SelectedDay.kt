package com.applandeo.materialcalendarview.utils

import android.widget.TextView
import java.util.*

/**
 * This helper class represent a selected day when calendar is in a picker date mode.
 * It is used to remember a selected calendar cell.
 *
 * @param view     View representing selected calendar cell
 * @param calendar Calendar instance representing selected cell date
 *
 * @return View representing selected calendar cell
 *
 * Created by Applandeo Team.
 */

data class SelectedDay @JvmOverloads constructor(
        val calendar: Calendar,
        var view: TextView? = null
) {

    override fun equals(other: Any?) =
            when (other) {
                is SelectedDay -> calendar == other.calendar
                is Calendar -> calendar == other
                else -> super.equals(other)
            }
}

