package com.applandeo.materialcalendarview.listeners

import com.applandeo.materialcalendarview.EventDay

/**
 * This interface is used to handle clicks on calendar cells
 *
 *
 * Created by Mateusz Kornakiewicz on 23.05.2017.
 */

interface OnDayClickListener {
    fun onDayClick(eventDay: EventDay)
}
