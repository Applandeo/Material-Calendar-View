package com.applandeo.materialcalendarsampleapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast

import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.EventDay
import com.applandeo.materialcalendarview.listeners.OnCalendarPageChangeListener
import com.applandeo.materialcalendarview.utils.DateUtils

import java.util.ArrayList
import java.util.Calendar

/**
 * Created by Mateusz Kornakiewicz on 23.10.2017.
 */

class ManyDaysPickerActivity : AppCompatActivity() {

    private val selectedDays: List<Calendar>
        get() {
            val calendars = ArrayList<Calendar>()

            for (i in 0..9) {
                val calendar = DateUtils.calendar
                calendar.add(Calendar.DAY_OF_MONTH, i)
                calendars.add(calendar)
            }

            return calendars
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.many_days_picker_activity)

        val calendarView = findViewById<View>(R.id.calendarView) as CalendarView

        calendarView.setOnForwardPageChangeListener(OnCalendarPageChangeListener {
            Toast.makeText(applicationContext, "Forward", Toast.LENGTH_SHORT).show()
        })

        calendarView.setOnPreviousPageChangeListener(OnCalendarPageChangeListener {
            Toast.makeText(applicationContext, "Previous", Toast.LENGTH_SHORT).show()
        })
        calendarView.selectedDates = selectedDays

        val events = ArrayList<EventDay>()

        val cal = Calendar.getInstance()
        cal.add(Calendar.DAY_OF_MONTH, 7)
        events.add(EventDay(cal, R.drawable.sample_four_icons))

        calendarView.setEvents(events)

        val getDateButton = findViewById<View>(R.id.getDateButton) as Button
        getDateButton.setOnClickListener { v ->
            for (calendar in calendarView.selectedDates) {
                println(calendar.time.toString())

                Toast.makeText(applicationContext,
                        calendar.time.toString(),
                        Toast.LENGTH_SHORT).show()
            }
        }
    }
}