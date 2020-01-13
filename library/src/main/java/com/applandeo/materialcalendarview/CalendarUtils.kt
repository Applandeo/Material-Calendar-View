@file:JvmName("CalendarUtils")

package com.applandeo.materialcalendarview

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by Applandeo Team.
 */

/**
 *Utils method to create drawable containing text
 */
fun Context.getDrawableText(text: String, typeface: Typeface?, color: Int, size: Int): Drawable {
    val bitmap = Bitmap.createBitmap(48, 48, Bitmap.Config.ARGB_8888)

    val canvas = Canvas(bitmap)
    val scale = this.resources.displayMetrics.density

    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        this.typeface = typeface ?: Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        this.color = ContextCompat.getColor(this@getDrawableText, color)
        this.textSize = (size * scale).toInt().toFloat()
    }

    val bounds = Rect()
    paint.getTextBounds(text, 0, text.length, bounds)
    val x = (bitmap.width - bounds.width()) / 2
    val y = (bitmap.height + bounds.height()) / 2
    canvas.drawText(text, x.toFloat(), y.toFloat(), paint)

    return BitmapDrawable(this.resources, bitmap)
}

/**
 * This method returns a list of calendar objects between two dates
 * @param this representing a first selected date
 * @param toCalendar Calendar representing a last selected date
 * @return List of selected dates between two dates
 */
fun Calendar.getDatesRange(toCalendar: Calendar): List<Calendar> =
        if (toCalendar.before(this)) {
            getCalendarsBetweenDates(toCalendar.time, this.time)
        } else {
            getCalendarsBetweenDates(this.time, toCalendar.time)
        }

private fun getCalendarsBetweenDates(dateFrom: Date, dateTo: Date): List<Calendar> {
    val calendars = mutableListOf<Calendar>()

    val calendarFrom = Calendar.getInstance().apply { time = dateFrom }
    val calendarTo = Calendar.getInstance().apply { time = dateTo }

    val daysBetweenDates = TimeUnit.MILLISECONDS.toDays(
            calendarTo.timeInMillis - calendarFrom.timeInMillis)

    (1 until daysBetweenDates).forEach {
        val calendar = calendarFrom.clone() as Calendar
        calendars.add(calendar)
        calendar.add(Calendar.DATE, it.toInt())
    }
    return calendars
}
