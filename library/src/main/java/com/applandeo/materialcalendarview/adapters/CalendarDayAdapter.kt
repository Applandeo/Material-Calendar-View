package com.applandeo.materialcalendarview.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.R
import com.applandeo.materialcalendarview.utils.*
import java.util.*

/**
 * This class is responsible for loading a one day cell.
 *
 *
 * Created by Mateusz Kornakiewicz on 24.05.2017.
 */

internal class CalendarDayAdapter(
        private val calendarPageAdapter: CalendarPageAdapter,
        context: Context,
        private val calendarProperties: CalendarProperties,
        dates: ArrayList<Date>,
        pageMonth: Int
) : ArrayAdapter<Date>(context, calendarProperties.itemLayoutResource, dates) {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    private val pageMonth: Int = if (pageMonth < 0) 11 else pageMonth
    private val today = getMidnightCalendar

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        return layoutInflater.inflate(calendarProperties.itemLayoutResource, parent, false)
                .apply {
                    val day = GregorianCalendar()
                    day.time = getItem(position)

                    findViewById<TextView>(R.id.dayLabel)?.let { textView ->
                        setLabelColors(textView, day)
                        textView.text = day.get(Calendar.DAY_OF_MONTH).toString()
                    }

                    findViewById<ImageView>(R.id.dayIcon)?.let { imageView ->
                        // Loading an image of the event
                        loadIcon(imageView, day)
                    }
                }
    }

    private fun setLabelColors(dayLabel: TextView, day: Calendar) {
        // Setting not current month day color
        if (isCurrentMonthDay(day).not()) {
            dayLabel.setDayColors(calendarProperties.anotherMonthsDaysLabelsColor,
                    Typeface.NORMAL, R.drawable.background_transparent)
            return
        }

        // Set view for all SelectedDays
        if (isSelectedDay(day)) {
            calendarPageAdapter.selectedDays
                    .first { selectedDay -> selectedDay.calendar == day }
                    .run { this.view = dayLabel }

            dayLabel.setSelectedDayColors(calendarProperties)
            return
        }

        // Setting disabled days color
        if (isActiveDay(day).not()) {
            dayLabel.setDayColors(calendarProperties.disabledDaysLabelsColor,
                    Typeface.NORMAL, R.drawable.background_transparent)
            return
        }

        // Setting current month day color
        day.setCurrentMonthDayColors(today, dayLabel, calendarProperties)
    }

    private fun isSelectedDay(day: Calendar) =
            (calendarProperties.calendarType != CalendarView.CLASSIC
                    && day.get(Calendar.MONTH) == pageMonth
                    && calendarPageAdapter.selectedDays.contains(SelectedDay(day)))

    private fun isCurrentMonthDay(day: Calendar) =
            day.get(Calendar.MONTH) == pageMonth
                    && (calendarProperties.minimumDate != null
                    && day.before(calendarProperties.minimumDate)
                    || calendarProperties.maximumDate != null
                    && day.after(calendarProperties.maximumDate)).not()

    private fun isActiveDay(day: Calendar) =
            calendarProperties.disabledDays.contains(day).not()

    private fun loadIcon(dayIcon: ImageView, day: Calendar) {
        if (calendarProperties.eventsEnabled.not()) {
            dayIcon.visibility = View.GONE
            return
        }

        calendarProperties.eventDays
                .find { eventDate -> eventDate.calendar == day }
                ?.let {
                    dayIcon.loadImage(it.imageDrawable)

                    // If a day doesn't belong to current month then image is transparent
                    if (!isCurrentMonthDay(day) || !isActiveDay(day)) {
                        dayIcon.alpha = 0.12f
                    }
                }
    }
}
