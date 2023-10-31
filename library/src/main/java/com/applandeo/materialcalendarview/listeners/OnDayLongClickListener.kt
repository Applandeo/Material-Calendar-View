package com.applandeo.materialcalendarview.listeners

import com.applandeo.materialcalendarview.EventDay

/**
 * This interface is used to handle long clicks on calendar cells
 *
 * Created by Applandeo Team.
 */

@Deprecated("Use OnCalendarDayLongClickListener instead")
interface OnDayLongClickListener {
    fun onDayLongClick(eventDay: EventDay)
}