package com.applandeo.materialcalendarview

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.applandeo.materialcalendarview.listeners.OnSelectionAbilityListener

import com.applandeo.materialcalendarview.utils.CalendarProperties
import com.applandeo.materialcalendarview.utils.DateUtils

/**
 * This class is responsible for creating DatePicker dialog.
 *
 *
 * Created by Mateusz Kornakiewicz on 27.07.2017.
 */

class DatePicker(private val mContext: Context, private val mCalendarProperties: CalendarProperties) {
    private var mCancelButton: AppCompatButton? = null
    private var mOkButton: AppCompatButton? = null
    private var mTodayButton: AppCompatButton? = null

    fun show(): DatePicker {
        val layoutInflater = LayoutInflater.from(mContext)
        val view = layoutInflater.inflate(R.layout.date_picker_dialog, null)

        if (mCalendarProperties.pagesColor != 0) {
            view.setBackgroundColor(mCalendarProperties.pagesColor)
        }

        mCancelButton = view.findViewById(R.id.negative_button)
        mOkButton = view.findViewById(R.id.positive_button)
        mTodayButton = view.findViewById(R.id.today_button)

        setTodayButtonVisibility()
        setDialogButtonsColors()
        setOkButtonState(mCalendarProperties.calendarType == CalendarView.ONE_DAY_PICKER)

        mCalendarProperties.onSelectionAbilityListener = object : OnSelectionAbilityListener {
            override fun onChange(enabled: Boolean) {
                setOkButtonState(enabled)
            }
        }

        val calendarView = CalendarView(mContext, mCalendarProperties)

        val calendarContainer = view.findViewById(R.id.calendarContainer) as FrameLayout
        calendarContainer.addView(calendarView)

        mCalendarProperties.calendar?.apply {
            calendarView.setDate(this)
        }

        val alertBuilder = AlertDialog.Builder(mContext)
        val alertDialog = alertBuilder.create().apply {
            setView(view)
        }

        mCancelButton?.setOnClickListener { _ -> alertDialog.cancel() }

        mOkButton?.setOnClickListener { _ ->
            alertDialog.cancel()
            mCalendarProperties.onSelectDateListener!!.onSelect(calendarView.selectedDates)
        }

        mTodayButton?.setOnClickListener { _ -> calendarView.showCurrentMonthPage() }

        alertDialog.show()

        return this
    }

    private fun setDialogButtonsColors() {
        if (mCalendarProperties.dialogButtonsColor != 0) {
            mCancelButton?.setTextColor(ContextCompat.getColor(mContext, mCalendarProperties.dialogButtonsColor))
            mTodayButton?.setTextColor(ContextCompat.getColor(mContext, mCalendarProperties.dialogButtonsColor))
        }
    }

    private fun setOkButtonState(enabled: Boolean) {
        mOkButton?.isEnabled = enabled

        if (mCalendarProperties.dialogButtonsColor != 0) {
            mOkButton!!.setTextColor(ContextCompat.getColor(mContext, if (enabled)
                mCalendarProperties.dialogButtonsColor
            else
                R.color.disabledDialogButtonColor))
        }
    }

    private fun setTodayButtonVisibility() {
        if (DateUtils.isMonthAfter(mCalendarProperties.maximumDate, DateUtils.calendar) || DateUtils.isMonthBefore(mCalendarProperties.minimumDate, DateUtils.calendar)) {
            mTodayButton!!.visibility = View.GONE
        }
    }
}
