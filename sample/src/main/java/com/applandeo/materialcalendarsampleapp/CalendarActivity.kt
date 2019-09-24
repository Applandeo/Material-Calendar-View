package com.applandeo.materialcalendarsampleapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast

import com.applandeo.materialcalendarsampleapp.utils.DrawableUtils
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.EventDay
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException
import com.applandeo.materialcalendarview.listeners.OnDayClickListener
import com.applandeo.materialcalendarview.utils.DateUtils

import java.util.ArrayList
import java.util.Calendar
import java.util.Random

/**
 * Created by Mateusz Kornakiewicz on 26.05.2017.
 */

class CalendarActivity : AppCompatActivity() {

    private val disabledDays: List<Calendar>
        get() {
            val firstDisabled = DateUtils.calendar
            firstDisabled.add(Calendar.DAY_OF_MONTH, 2)

            val secondDisabled = DateUtils.calendar
            secondDisabled.add(Calendar.DAY_OF_MONTH, 1)

            val thirdDisabled = DateUtils.calendar
            thirdDisabled.add(Calendar.DAY_OF_MONTH, 18)

            val calendars = ArrayList<Calendar>()
            calendars.add(firstDisabled)
            calendars.add(secondDisabled)
            calendars.add(thirdDisabled)
            return calendars
        }

    private val randomCalendar: Calendar
        get() {
            val random = Random()

            val calendar = Calendar.getInstance()
            calendar.add(Calendar.MONTH, random.nextInt(99))

            return calendar
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendar_activity)

        val events = ArrayList<EventDay>()

        val calendar = Calendar.getInstance()
        events.add(EventDay(calendar, DrawableUtils.getCircleDrawableWithText(this, "M")))

        val calendar1 = Calendar.getInstance()
        calendar1.add(Calendar.DAY_OF_MONTH, 2)
        events.add(EventDay(calendar1, R.drawable.sample_icon_2))

        val calendar2 = Calendar.getInstance()
        calendar2.add(Calendar.DAY_OF_MONTH, 5)
        events.add(EventDay(calendar2, R.drawable.sample_icon_3))

        val calendar3 = Calendar.getInstance()
        calendar3.add(Calendar.DAY_OF_MONTH, 7)
        events.add(EventDay(calendar3, R.drawable.sample_four_icons))

        val calendar4 = Calendar.getInstance()
        calendar4.add(Calendar.DAY_OF_MONTH, 13)
        events.add(EventDay(calendar4, DrawableUtils.getThreeDots(this)))

        val calendarView = findViewById<View>(R.id.calendarView) as CalendarView

        val min = Calendar.getInstance()
        min.add(Calendar.MONTH, -2)

        val max = Calendar.getInstance()
        max.add(Calendar.MONTH, 2)

        calendarView.setMinimumDate(min)
        calendarView.setMaximumDate(max)

        calendarView.setEvents(events)

        calendarView.setDisabledDays(disabledDays)

        calendarView.setOnDayClickListener(OnDayClickListener {
            Toast.makeText(applicationContext,
                    it.calendar?.time.toString() + " "
                            + it.isEnabled,
                    Toast.LENGTH_SHORT).show()
        })

        val setDateButton = findViewById<View>(R.id.setDateButton) as Button
        setDateButton.setOnClickListener { v ->
            try {
                val randomCalendar = randomCalendar
                val text = randomCalendar.time.toString()
                Toast.makeText(applicationContext, text, Toast.LENGTH_LONG).show()
                calendarView.setDate(randomCalendar)
            } catch (exception: OutOfDateRangeException) {
                exception.printStackTrace()

                Toast.makeText(applicationContext,
                        "Date is out of range",
                        Toast.LENGTH_LONG).show()
            }
        }
    }
}
