package com.applandeo.materialcalendarsampleapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.applandeo.materialcalendarview.CalendarUtils
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.builders.DatePickerBuilder
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener
import java.util.*

class MainActivity : AppCompatActivity(), OnSelectDateListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val openCalendarButton = findViewById<View>(R.id.openCalendarButton) as Button

        openCalendarButton.setOnClickListener {
            val intent = Intent(this, CalendarActivity::class.java)
            startActivity(intent)
        }

        val openOneDayPicker = findViewById<View>(R.id.openOneDayPickerButton) as Button
        openOneDayPicker.setOnClickListener { startActivity(Intent(this, OneDayPickerActivity::class.java)) }

        val openManyDaysPicker = findViewById<View>(R.id.openManyDayPickerButton) as Button
        openManyDaysPicker.setOnClickListener { startActivity(Intent(this, ManyDaysPickerActivity::class.java)) }

        val openRangePicker = findViewById<View>(R.id.openRangePickerButton) as Button
        openRangePicker.setOnClickListener { startActivity(Intent(this, RangePickerActivity::class.java)) }

        val openOneDayPickerDialog = findViewById<View>(R.id.openOneDayPickerDialogButton) as Button
        openOneDayPickerDialog.setOnClickListener { openOneDayPicker() }

        val openManyDaysPickerDialog = findViewById<View>(R.id.openManyDaysPickerDialogButton) as Button
        openManyDaysPickerDialog.setOnClickListener { openManyDaysPicker() }

        val openRangePickerDialog = findViewById<View>(R.id.openRangePickerDialogButton) as Button
        openRangePickerDialog.setOnClickListener { openRangePicker() }
    }

    private fun openOneDayPicker() {
        val min = Calendar.getInstance()
        min.add(Calendar.MONTH, -5)

        val max = Calendar.getInstance()
        max.add(Calendar.DAY_OF_MONTH, 3)

        val oneDayBuilder = DatePickerBuilder(this, this)

        with(oneDayBuilder) {
            pickerType(CalendarView.ONE_DAY_PICKER)
            date(max)
            headerColor(R.color.colorPrimaryDark)
            headerLabelColor(R.color.currentMonthDayColor)
            selectionColor(R.color.daysLabelColor)
            todayLabelColor(R.color.colorAccent)
            dialogButtonsColor(android.R.color.holo_green_dark)
            disabledDaysLabelsColor(android.R.color.holo_purple)
            previousButtonSrc(R.drawable.ic_chevron_left_black_24dp)
            forwardButtonSrc(R.drawable.ic_chevron_right_black_24dp)
            minimumDate(min)
            maximumDate(max)
            disabledDays(disabledDays())
        }

        val oneDayPicker = oneDayBuilder.build()
        oneDayPicker.show()
    }

    private fun openManyDaysPicker() {
        val min = Calendar.getInstance()
        min.add(Calendar.DAY_OF_MONTH, -5)

        val max = Calendar.getInstance()
        max.add(Calendar.DAY_OF_MONTH, 3)

        val selectedDays = ArrayList(disabledDays())
        selectedDays.add(min)
        selectedDays.add(max)

        val manyDaysBuilder = DatePickerBuilder(this, this)
        with(manyDaysBuilder) {
            pickerType(CalendarView.MANY_DAYS_PICKER)
            headerColor(android.R.color.holo_green_dark)
            selectionColor(android.R.color.holo_green_dark)
            todayLabelColor(android.R.color.holo_green_dark)
            dialogButtonsColor(android.R.color.holo_green_dark)
            selectedDays(selectedDays)
            disabledDays(disabledDays())
        }

        val manyDaysPicker = manyDaysBuilder.build()
        manyDaysPicker.show()
    }

    private fun openRangePicker() {
        val min = Calendar.getInstance()
        min.add(Calendar.DAY_OF_MONTH, -5)

        val max = Calendar.getInstance()
        max.add(Calendar.DAY_OF_MONTH, 3)

        val selectedDays = ArrayList<Calendar>()
        selectedDays.add(min)
        selectedDays.addAll(CalendarUtils.getDatesRange(min, max))
        selectedDays.add(max)

        val rangeBuilder = DatePickerBuilder(this, this)
        with(rangeBuilder) {
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
        }

        val rangePicker = rangeBuilder.build()
        rangePicker.show()
    }


    private fun disabledDays(): List<Calendar> {
        /*Calendar firstDisabled = DateUtils.getCalendar();
        firstDisabled.add(Calendar.DAY_OF_MONTH, 2);

        Calendar secondDisabled = DateUtils.getCalendar();
        secondDisabled.add(Calendar.DAY_OF_MONTH, 1);

        Calendar thirdDisabled = DateUtils.getCalendar();
        thirdDisabled.add(Calendar.DAY_OF_MONTH, 18);*/

        //calendars.add(firstDisabled);
        //calendars.add(secondDisabled);
        //calendars.add(thirdDisabled);
        return ArrayList()
    }

    override fun onSelect(calendar: List<Calendar>) {
        calendar.forEach { day ->
            Toast.makeText(applicationContext,
                    day.time.toString(),
                    Toast.LENGTH_SHORT).show()
        }
    }
}


