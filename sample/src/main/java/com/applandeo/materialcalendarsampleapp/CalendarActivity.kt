package com.applandeo.materialcalendarsampleapp

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.applandeo.materialcalendarsampleapp.utils.DrawableUtils
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.EventDay
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException
import com.applandeo.materialcalendarview.listeners.OnDayClickListener
import com.applandeo.materialcalendarview.utils.DateUtils
import java.util.*

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

    private fun getRandomCalendar(): Calendar {
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
        events.add(EventDay(calendar, DrawableUtils.getCircleDrawableWithText(this, "M"), Color.parseColor("#228B22")))

        val calendar1 = Calendar.getInstance()
        val calendar2 = Calendar.getInstance()
        val calendar3 = Calendar.getInstance()
        val calendar4 = Calendar.getInstance()

        calendar1.add(Calendar.DAY_OF_MONTH, 2)
        calendar2.add(Calendar.DAY_OF_MONTH, 5)
        calendar3.add(Calendar.DAY_OF_MONTH, 7)
        calendar4.add(Calendar.DAY_OF_MONTH, 13)

        events.apply {
            add(EventDay(calendar1, R.drawable.sample_icon_2, Color.parseColor("#228B22")))
            add(EventDay(calendar2, R.drawable.sample_icon_3, Color.parseColor("#228B22")))
            add(EventDay(calendar3, R.drawable.sample_four_icons, Color.parseColor("#228B22")))
            add(EventDay(calendar4, DrawableUtils.getThreeDots(this@CalendarActivity), Color.parseColor("#228B22")))
        }


        val calendarView = findViewById<View>(R.id.calendarView) as CalendarView

        val min = Calendar.getInstance()
        min.add(Calendar.MONTH, -2)

        val max = Calendar.getInstance()
        max.add(Calendar.MONTH, 2)

        calendarView.apply {
            setMinimumDate(min)
            setMaximumDate(max)
            setEvents(events)
            setDisabledDays(disabledDays)
            setOnDayClickListener(object : OnDayClickListener {
                override fun onDayClick(eventDay: EventDay) {
                    Toast.makeText(applicationContext, getTitle(eventDay),
                            Toast.LENGTH_SHORT).show()
                }
            })
        }

        val setDateButton = findViewById<View>(R.id.setDateButton) as Button
        setDateButton.setOnClickListener {
            try {
                val randomCalendar = getRandomCalendar()
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

    private fun getTitle(eventDay: EventDay) =
            "${eventDay.calendar?.time.toString()} ${eventDay.isEnabled}"
}
