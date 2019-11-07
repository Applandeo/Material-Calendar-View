package com.applandeo.materialcalendarview.listeners

import java.util.*

/**
 * This interface is used to inform DatePicker that user selected any days
 *
 * Created by Mateusz Kornakiewicz on 27.07.2017.
 */

interface OnSelectDateListener {
    fun onSelect(calendar: List<Calendar>)
}