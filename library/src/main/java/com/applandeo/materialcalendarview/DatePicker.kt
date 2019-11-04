package com.applandeo.materialcalendarview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.applandeo.materialcalendarview.listeners.OnSelectionAbilityListener
import com.applandeo.materialcalendarview.utils.CalendarProperties
import com.applandeo.materialcalendarview.utils.DateUtils
import kotlinx.android.synthetic.main.date_picker_dialog.view.*

/**
 * This class is responsible for creating DatePicker dialog.
 *
 *
 * Created by Mateusz Kornakiewicz on 27.07.2017.
 */

class DatePicker(
        private val context: Context,
        private val calendarProperties: CalendarProperties
) {

    fun show(): DatePicker {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.date_picker_dialog, null)

        if (calendarProperties.pagesColor != 0) {
            view.setBackgroundColor(calendarProperties.pagesColor)
        }

        setTodayButtonVisibility(view.todayButton)
        setDialogButtonsColors(view.negativeButton, view.todayButton)
        setOkButtonState(calendarProperties.calendarType == CalendarView.ONE_DAY_PICKER, view.positiveButton)

        calendarProperties.onSelectionAbilityListener = object : OnSelectionAbilityListener {
            override fun onChange(enabled: Boolean) {
                setOkButtonState(enabled, view.todayButton)
            }
        }

        val calendarView = CalendarView(context, calendarProperties)

        val calendarContainer = view.findViewById(R.id.calendarContainer) as FrameLayout
        calendarContainer.addView(calendarView)

        calendarProperties.calendar?.apply {
            calendarView.setDate(this)
        }

        val alertBuilder = AlertDialog.Builder(context)
        val alertDialog = alertBuilder.create().apply {
            setView(view)
        }

        view.negativeButton?.setOnClickListener { _ -> alertDialog.cancel() }

        view.positiveButton?.setOnClickListener { _ ->
            alertDialog.cancel()
            calendarProperties.onSelectDateListener?.onSelect(calendarView.selectedDates)
        }

        view.todayButton?.setOnClickListener { _ -> calendarView.showCurrentMonthPage() }

        alertDialog.show()

        return this
    }

    private fun setDialogButtonsColors(negativeButton: AppCompatButton?, todayButton: AppCompatButton?) {
        if (calendarProperties.dialogButtonsColor != 0) {
            negativeButton?.setTextColor(ContextCompat.getColor(context, calendarProperties.dialogButtonsColor))
            todayButton?.setTextColor(ContextCompat.getColor(context, calendarProperties.dialogButtonsColor))
        }
    }

    private fun setOkButtonState(enabled: Boolean, okButton: AppCompatButton?) {
        okButton?.isEnabled = enabled

        if (calendarProperties.dialogButtonsColor != 0) {
            val stateResource = if (enabled)
                calendarProperties.dialogButtonsColor
            else
                R.color.disabledDialogButtonColor
            okButton?.setTextColor(ContextCompat.getColor(context, stateResource))
        }
    }

    private fun setTodayButtonVisibility(todayButton: AppCompatButton?) {
        if (DateUtils.isMonthAfter(calendarProperties.maximumDate, DateUtils.calendar) || DateUtils.isMonthBefore(calendarProperties.minimumDate, DateUtils.calendar)) {
            todayButton?.visibility = View.GONE
        }
    }
}
