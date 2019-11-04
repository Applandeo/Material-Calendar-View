@file:JvmName("ImageUtils")

package com.applandeo.materialcalendarview.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.ContextCompat

/**
 * This class is used to load event image in a day cell
 *
 *
 * Created by Mateusz Kornakiewicz on 23.05.2017.
 */

fun ImageView.loadImage(image: Any?) {
    if (image == null) {
        return
    }

    var drawable: Drawable? = null
    if (image is Drawable) {
        drawable = image
    } else if (image is Int) {
        drawable = ContextCompat.getDrawable(this.context, image)
    }

    drawable?.let { this.setImageDrawable(it) }
}
