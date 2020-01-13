package com.applandeo.materialcalendarview

import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.RestrictTo
import com.applandeo.materialcalendarview.utils.EventImage
import com.applandeo.materialcalendarview.utils.setMidnight
import java.util.*

/**
 * This class represents an event of a day. An instance of this class is returned when user click
 * a day cell. This class can be overridden to make calendar more functional. A list of instances of
 * this class can be passed to CalendarView object using setEvents() method.
 *
 *
 * @param day      Calendar object which represents a date of the event
 * @param drawable Drawable which will be displayed in a day cell
 * @param drawableRes Drawable resource which will be displayed in a day cell
 * @param labelColor Color which will be displayed as row label text color
 *
 * Created by Applandeo Team.
 */

data class EventDay(private val day: Calendar) {

    init {
        day.setMidnight()
    }

    constructor(day: Calendar, drawable: Drawable) : this(day) {
        imageDrawable = EventImage.EventImageDrawable(drawable)
    }

    constructor(day: Calendar, @DrawableRes drawableRes: Int) : this(day) {
        imageDrawable = EventImage.EventImageResource(drawableRes)
    }

    constructor(day: Calendar, @DrawableRes drawableRes: Int, @ColorInt labelColor: Int) : this(day) {
        imageDrawable = EventImage.EventImageResource(drawableRes)
        this.labelColor = labelColor
    }

    /**
     * @return Calendar object which represents a date of current event
     */
    var calendar: Calendar? = day

    /**
     * @return An image resource which will be displayed in the day row
     */
    @get:RestrictTo(RestrictTo.Scope.LIBRARY)
    var imageDrawable: EventImage = EventImage.EmptyEventImage

    /**
     * @return Boolean value if day is not disabled
     */
    @set:RestrictTo(RestrictTo.Scope.LIBRARY)
    var isEnabled: Boolean = false

    @set:RestrictTo(RestrictTo.Scope.LIBRARY)
    var labelColor: Int = 0
}