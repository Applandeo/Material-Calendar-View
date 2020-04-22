package com.applandeo.materialcalendarview

import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import java.util.*

/*
In one of feature versions, the idea is to merge and replace EventDay and SelectedDay with this object
 */
class CalendarDay(val calendar: Calendar) {
    @ColorRes
    var labelColor: Int? = null

    @DrawableRes
    var backgroundResource: Int? = null

    var backgroundDrawable: Drawable? = null

    @ColorRes
    var selectedLabelColor: Int? = null

    @DrawableRes
    var selectedBackgroundResource: Int? = null

    var selectedBackgroundDrawable: Drawable? = null
}