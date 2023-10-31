package com.applandeo.materialcalendarview.listeners

import com.applandeo.materialcalendarview.EventDay

/**
 * This interface is used to handle clicks on calendar cells
 *
 * Created by Applandeo Team.
 */

@Deprecated("Use OnCalendarDayClickListener instead")
interface OnDayClickListener {
    fun onDayClick(eventDay: EventDay)
}
