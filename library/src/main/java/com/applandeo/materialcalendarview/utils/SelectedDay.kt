package com.applandeo.materialcalendarview.utils

import android.view.View
import java.util.*

/**
 * This helper class represent a selected day when calendar is in a picker date mode.
 * It is used to remember a selected calendar cell.
 *
 * @param calendar Calendar instance representing selected cell date
 * @param view     View representing selected calendar cell
 *
 * Created by Applandeo Team.
 */

data class SelectedDay @JvmOverloads constructor(
        val calendar: Calendar,
        var view: View? = null
) {

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is SelectedDay -> calendar == other.calendar
            is Calendar -> calendar == other
            else -> super.equals(other)
        }
    }
}

