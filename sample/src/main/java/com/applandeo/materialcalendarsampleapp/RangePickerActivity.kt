package com.applandeo.materialcalendarsampleapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.applandeo.materialcalendarview.CalendarView

/**
 * Created by Mateusz Kornakiewicz on 23.10.2017.
 */

class RangePickerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.range_picker_activity)

        val calendarView = findViewById<View>(R.id.calendarView) as CalendarView

        val getDateButton = findViewById<View>(R.id.getDateButton) as Button
        getDateButton.setOnClickListener {
            for (calendar in calendarView.selectedDates) {
                println(calendar.time.toString())

                Toast.makeText(applicationContext,
                        calendar.time.toString(),
                        Toast.LENGTH_SHORT).show()
            }
        }
    }
}