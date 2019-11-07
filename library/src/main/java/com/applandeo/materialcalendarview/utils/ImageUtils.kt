@file:JvmName("ImageUtils")

package com.applandeo.materialcalendarview.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.ContextCompat

/**
 * This class is used to load event image in a day cell
 *
 * Created by Mateusz Kornakiewicz on 23.05.2017.
 */

fun ImageView.loadImage(image: Any?) =
        when (image) {
            is Drawable -> image
            is Int -> ContextCompat.getDrawable(this.context, image)
            else -> null
        }?.run { this@loadImage.setImageDrawable(this) } ?: Unit
