package com.applandeo.materialcalendarview

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.RestrictTo
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

data class EventDay @JvmOverloads constructor(
        private val day: Calendar,
        private val drawable: Drawable? = null,
        @DrawableRes private val drawableRes: Int = 0,
        val labelColor: Int = 0
) {

    /**
     * @return Calendar object which represents a date of current event
     */
    var calendar: Calendar? = day

    /**
     * @return An image resource which will be displayed in the day row
     */
    @get:RestrictTo(RestrictTo.Scope.LIBRARY)
    var imageDrawable: Any? = null

    /**
     * @return Boolean value if day is not disabled
     */
    @set:RestrictTo(RestrictTo.Scope.LIBRARY)
    var isEnabled: Boolean = false

    init {
        day.setMidnight()
        drawable?.let { imageDrawable = drawable }
        if (drawableRes != 0) {
            imageDrawable = drawableRes
        }
    }
}
