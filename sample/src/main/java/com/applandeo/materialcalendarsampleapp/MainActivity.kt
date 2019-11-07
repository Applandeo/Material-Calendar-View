package com.applandeo.materialcalendarsampleapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.builders.DatePickerBuilder
import com.applandeo.materialcalendarview.getDatesRange
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener
import com.applandeo.materialcalendarview.utils.getMidnightCalendar
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), OnSelectDateListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openCalendarButton.setOnClickListener {
            val intent = Intent(this, CalendarActivity::class.java)
            startActivity(intent)
        }

        openOneDayPickerButton.setOnClickListener { startActivity(Intent(this, OneDayPickerActivity::class.java)) }
        openManyDayPickerButton.setOnClickListener { startActivity(Intent(this, ManyDaysPickerActivity::class.java)) }
        openRangePickerButton.setOnClickListener { startActivity(Intent(this, RangePickerActivity::class.java)) }
        openOneDayPickerDialogButton.setOnClickListener { openOneDayPicker() }
        openManyDaysPickerDialogButton.setOnClickListener { openManyDaysPicker() }
        openRangePickerDialogButton.setOnClickListener { openRangePicker() }
    }

    private fun openOneDayPicker() {
        DatePickerBuilder(this, this).apply {
            pickerType(CalendarView.ONE_DAY_PICKER)
            date(Calendar.getInstance().apply { add(Calendar.DAY_OF_MONTH, 3) })
            headerColor(R.color.colorPrimaryDark)
            headerLabelColor(R.color.currentMonthDayColor)
            selectionColor(R.color.daysLabelColor)
            todayLabelColor(R.color.colorAccent)
            dialogButtonsColor(android.R.color.holo_green_dark)
            disabledDaysLabelsColor(android.R.color.holo_purple)
            previousButtonSrc(R.drawable.ic_chevron_left_black_24dp)
            forwardButtonSrc(R.drawable.ic_chevron_right_black_24dp)
            minimumDate(Calendar.getInstance().apply { add(Calendar.MONTH, -5) })
            maximumDate(Calendar.getInstance().apply { add(Calendar.DAY_OF_MONTH, 3) })
            disabledDays(disabledDays())
        }.build().show()
    }

    private fun openManyDaysPicker() {
        val selectedDays = mutableListOf<Calendar>().apply { addAll(disabledDays()) }
        selectedDays.add(Calendar.getInstance().apply { add(Calendar.DAY_OF_MONTH, -5) })
        selectedDays.add(Calendar.getInstance().apply { add(Calendar.DAY_OF_MONTH, 3) })

        DatePickerBuilder(this, this).apply {
            pickerType(CalendarView.MANY_DAYS_PICKER)
            headerColor(android.R.color.holo_green_dark)
            selectionColor(android.R.color.holo_green_dark)
            todayLabelColor(android.R.color.holo_green_dark)
            dialogButtonsColor(android.R.color.holo_green_dark)
            selectedDays(selectedDays)
            disabledDays(disabledDays())
        }.build().show()
    }

    private fun openRangePicker() {
        val min = Calendar.getInstance().apply { add(Calendar.DAY_OF_MONTH, -5) }
        val max = Calendar.getInstance().apply { add(Calendar.DAY_OF_MONTH, 3) }

        val selectedDays = mutableListOf<Calendar>()
        selectedDays.add(min)
        selectedDays.addAll(min.getDatesRange(max))
        selectedDays.add(max)

        DatePickerBuilder(this, this).apply {
            pickerType(CalendarView.RANGE_PICKER)
            headerColor(R.color.sampleDark)
            abbreviationsBarColor(R.color.sampleLight)
            abbreviationsLabelsColor(android.R.color.white)
            pagesColor(R.color.sampleLighter)
            selectionColor(android.R.color.white)
            selectionLabelColor(R.color.sampleDark)
            todayLabelColor(R.color.dialogAccent)
            dialogButtonsColor(android.R.color.white)
            daysLabelsColor(android.R.color.white)
            anotherMonthsDaysLabelsColor(R.color.sampleLighter)
            selectedDays(selectedDays)
            disabledDays(disabledDays())
        }.build().show()
    }


    private fun disabledDays(): List<Calendar> {
        val disabledDays = mutableListOf<Calendar>()

        disabledDays.add(getMidnightCalendar.apply { add(Calendar.DAY_OF_MONTH, 2) })
        disabledDays.add(getMidnightCalendar.apply { add(Calendar.DAY_OF_MONTH, 1) })
        disabledDays.add(getMidnightCalendar.apply { add(Calendar.DAY_OF_MONTH, 18) })
        return disabledDays
    }

    override fun onSelect(calendar: List<Calendar>) {
        calendar.forEach { day ->
            Toast.makeText(applicationContext, day.time.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}


