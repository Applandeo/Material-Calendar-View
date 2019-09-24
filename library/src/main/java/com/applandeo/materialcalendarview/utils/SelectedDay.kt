package com.applandeo.materialcalendarview.utils

import android.view.View

import java.util.Calendar

/**
 * This helper class represent a selected day when calendar is in a picker date mode.
 * It is used to remember a selected calendar cell.
 *
 *
 * Created by Mateusz Kornakiewicz on 23.05.2017.
 */

class SelectedDay {
    /**
     * @return View representing selected calendar cell
     */
    var view: View? = null
    /**
     * @return Calendar instance representing selected cell date
     */
    var calendar: Calendar? = null
        private set

    constructor(calendar: Calendar) {
        this.calendar = calendar
    }

    /**
     * @param view     View representing selected calendar cell
     * @param calendar Calendar instance representing selected cell date
     */
    constructor(view: View, calendar: Calendar) {
        this.view = view
        this.calendar = calendar
    }

    override fun equals(obj: Any?): Boolean {
        if (obj is SelectedDay) {
            return calendar == obj.calendar
        }

        return if (obj is Calendar) {
            calendar == obj
        } else super.equals(obj)

    }
}

