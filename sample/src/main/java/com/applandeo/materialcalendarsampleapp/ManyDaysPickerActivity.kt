package com.applandeo.materialcalendarsampleapp

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.applandeo.materialcalendarview.EventDay
import com.applandeo.materialcalendarview.listeners.OnCalendarPageChangeListener
import com.applandeo.materialcalendarview.utils.midnightCalendar
import kotlinx.android.synthetic.main.many_days_picker_activity.*
import java.util.*

/**
 * Created by Applandeo Team.
 */

class ManyDaysPickerActivity : AppCompatActivity() {

    private val selectedDays: List<Calendar>
        get() {
            val calendars = mutableListOf<Calendar>()

            for (i in 0..9) {
                val calendar = midnightCalendar
                calendar.add(Calendar.DAY_OF_MONTH, i)
                calendars.add(calendar)
            }

            return calendars
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.many_days_picker_activity)


        calendarView.setOnForwardPageChangeListener(object : OnCalendarPageChangeListener {
            override fun onChange() {
                Toast.makeText(applicationContext, "Forward", Toast.LENGTH_SHORT).show()
            }
        })

        calendarView.setOnPreviousPageChangeListener(object : OnCalendarPageChangeListener {
            override fun onChange() {
                Toast.makeText(applicationContext, "Previous", Toast.LENGTH_SHORT).show()
            }
        })
        calendarView.selectedDates = selectedDays

        val events = mutableListOf<EventDay>()

        val cal = Calendar.getInstance().apply { add(Calendar.DAY_OF_MONTH, 7) }
        events.add(EventDay(cal, drawableRes = R.drawable.sample_four_icons, labelColor = Color.parseColor("#228B22")))

        calendarView.setEvents(events)

        getDateButton.setOnClickListener {
            for (calendar in calendarView.selectedDates) {
                println(calendar.time.toString())

                Toast.makeText(applicationContext, calendar.time.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}