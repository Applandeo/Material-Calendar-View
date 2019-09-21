@file:JvmName("DrawableUtils")

package com.applandeo.materialcalendarsampleapp.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.InsetDrawable
import android.graphics.drawable.LayerDrawable
import androidx.core.content.ContextCompat

import com.applandeo.materialcalendarsampleapp.R
import com.applandeo.materialcalendarview.getDrawableText

/**
 * Created by Applandeo Team.
 */

fun Context.getCircleDrawableWithText(string: String): Drawable {
    val background = ContextCompat.getDrawable(this, R.drawable.sample_circle)
    val text = this.getDrawableText(string, null, android.R.color.white, 12)

    return LayerDrawable(arrayOf(background, text))
}

fun Context.getThreeDots(): Drawable {
    val drawable = ContextCompat.getDrawable(this, R.drawable.sample_three_icons)

    //Add padding to too large icon
    return InsetDrawable(drawable, 100, 0, 100, 0)
}

