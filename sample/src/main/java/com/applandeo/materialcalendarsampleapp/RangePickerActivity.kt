package com.applandeo.materialcalendarsampleapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.range_picker_activity.*

/**
 * Created by Mateusz Kornakiewicz on 23.10.2017.
 */

class RangePickerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.range_picker_activity)

        getDateButton.setOnClickListener {
            for (calendar in calendarView.selectedDates) {
                println(calendar.time.toString())

                Toast.makeText(applicationContext, calendar.time.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}