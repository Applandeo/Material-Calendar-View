package com.applandeo.materialcalendarview.utils

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes

/**
 * These classes hold different types of event images
 */

sealed class EventImage {
    object EmptyEventImage : EventImage()
    data class EventImageResource(@DrawableRes val drawableRes: Int) : EventImage()
    data class EventImageDrawable(val drawable: Drawable) : EventImage()
}