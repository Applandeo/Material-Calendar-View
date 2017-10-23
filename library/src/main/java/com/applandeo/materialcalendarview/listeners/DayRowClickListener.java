package com.applandeo.materialcalendarview.listeners;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.annimon.stream.Stream;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.R;
import com.applandeo.materialcalendarview.adapters.CalendarPageAdapter;
import com.applandeo.materialcalendarview.utils.DateUtils;
import com.applandeo.materialcalendarview.utils.DayColorsUtils;
import com.applandeo.materialcalendarview.utils.SelectedDay;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * This class is responsible for handle click events
 * <p>
 * Created by Mateusz Kornakiewicz on 24.05.2017.
 */

public class DayRowClickListener implements AdapterView.OnItemClickListener {
    private CalendarPageAdapter mCalendarPageAdapter;
    private Context mContext;
    private List<EventDay> mEventDays;
    private OnDayClickListener mOnDayClickListener;
    private int mCalendarType;
    private int mTodayLabelColor;
    private int mSelectionColor;


    public DayRowClickListener(CalendarPageAdapter calendarPageAdapter, Context context,
                               List<EventDay> eventDays, OnDayClickListener onDayClickListener,
                               int calendarType, int todayLabelColor, int selectionColor) {
        mCalendarPageAdapter = calendarPageAdapter;
        mContext = context;
        mEventDays = eventDays;
        mOnDayClickListener = onDayClickListener;
        mCalendarType = calendarType;
        mTodayLabelColor = todayLabelColor;
        mSelectionColor = selectionColor;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Calendar day = new GregorianCalendar();
        day.setTime((Date) adapterView.getItemAtPosition(position));

        // If calendar is in picker mode than day is selected
        if (mCalendarType == CalendarView.ONE_DAY_PICKER) {
            selectOneDay(view, day);
            return;
        }

        if (mCalendarType == CalendarView.MULTIPLE_DAY_PICKER) {
            selectMultipleDay(view, day);
            return;
        }

        if (mCalendarType == CalendarView.RANGE_DAY_PICKER) {
            selectRange(view, day);
            return;
        }

        // If calendar is not in the picker mode than onClick method is called
        if (mOnDayClickListener != null) {
            mCalendarPageAdapter.setSelectedDay(new SelectedDay(view, day));
            onClick(day);
        }
    }

    private void selectOneDay(View view, Calendar day) {
        // Getting previous selected day
        SelectedDay previousSelectedDay = mCalendarPageAdapter.getSelectedDay();

        if (previousSelectedDay != null && !day.equals(previousSelectedDay.getCalendar())) {
            TextView dayLabel = (TextView) view.findViewById(R.id.dayLabel);

            // Checking if current month day is selecting
            if (isCurrentMonthLabel(dayLabel)) {
                // Coloring selected day
                DayColorsUtils.setSelectedDayColors(mContext, dayLabel, mSelectionColor);

                TextView previousDayLabel =
                        (TextView) previousSelectedDay.getView().findViewById(R.id.dayLabel);

                // Reverse selected day to previous colors
                DayColorsUtils.setCurrentMonthDayColors(mContext, previousSelectedDay.getCalendar(),
                        DateUtils.getCalendar(), previousDayLabel, mTodayLabelColor);

                mCalendarPageAdapter.setSelectedDay(new SelectedDay(dayLabel, day));
            }
        }
    }

    private void selectMultipleDay(View view, Calendar day) {
        TextView dayLabel = (TextView) view.findViewById(R.id.dayLabel);

        // Checking if current month day is selecting
        if (isCurrentMonthLabel(dayLabel)) {
            SelectedDay selectedDay = new SelectedDay(dayLabel, day);

            if (!mCalendarPageAdapter.getSelectedDays().contains(selectedDay)) {
                // Coloring selected day
                DayColorsUtils.setSelectedDayColors(mContext, dayLabel, mSelectionColor);
            } else {
                TextView previousDayLabel =
                        (TextView) selectedDay.getView().findViewById(R.id.dayLabel);

                // Reverse selected day to previous colors
                DayColorsUtils.setCurrentMonthDayColors(mContext, selectedDay.getCalendar(),
                        DateUtils.getCalendar(), previousDayLabel, mTodayLabelColor);
            }

            mCalendarPageAdapter.addSelectedDay(selectedDay);
        }
    }

    private void selectRange(View view, Calendar day) {
        // jesli jest zaznaczony jeden dzien to po kliknieciu na drugi pobiera posrednie i zaznacza
        // jesli sa zaznaczone 2 lub wiecej dni to po kliknieciu na kolejny odznacza wczesniejsze i zaznacza ten kolejny
        // jesli nie ma zadnych zaznaczonych to po kliknieciu zaznacza jeden

        List<SelectedDay> selectedDays = mCalendarPageAdapter.getSelectedDays();

        if (selectedDays.size() > 1) {
            Stream.of(mCalendarPageAdapter.getSelectedDays())
                    .forEach(selectedDay -> {
                        // Reverse selected day to previous colors

                        System.out.println("VIEW : " + selectedDay.getView());

                        DayColorsUtils.setCurrentMonthDayColors(mContext, selectedDay.getCalendar(),
                                DateUtils.getCalendar(), (TextView) selectedDay.getView(), mTodayLabelColor);
                    });

            TextView dayLabel = (TextView) view.findViewById(R.id.dayLabel);

            // Checking if current month day is selecting
            if (isCurrentMonthLabel(dayLabel)) {
                // Coloring selected day
                DayColorsUtils.setSelectedDayColors(mContext, dayLabel, mSelectionColor);
                mCalendarPageAdapter.setSelectedDay(new SelectedDay(dayLabel, day));
            }
        }

        if (selectedDays.size() == 1) { // I tu ma zaznaczac jeszcze dni posrodku
            TextView dayLabel = (TextView) view.findViewById(R.id.dayLabel);

            // Checking if current month day is selecting
            if (isCurrentMonthLabel(dayLabel)) {
                // Getting previous selected day
                SelectedDay previousSelectedDay = mCalendarPageAdapter.getSelectedDay();

                Stream.of(DateUtils.getDatesRange(previousSelectedDay.getCalendar(), day))
                        .forEach(calendar -> mCalendarPageAdapter.addSelectedDay(new SelectedDay(calendar)));

                SelectedDay selectedDay = new SelectedDay(dayLabel, day);

                // Coloring selected day
                DayColorsUtils.setSelectedDayColors(mContext, dayLabel, mSelectionColor);

                mCalendarPageAdapter.addSelectedDay(selectedDay);
            }
        }

        if (selectedDays.isEmpty()) {
            TextView dayLabel = (TextView) view.findViewById(R.id.dayLabel);

            // Checking if current month day is selecting
            if (isCurrentMonthLabel(dayLabel)) {
                // Coloring selected day
                DayColorsUtils.setSelectedDayColors(mContext, dayLabel, mSelectionColor);
                mCalendarPageAdapter.setSelectedDay(new SelectedDay(dayLabel, day));
            }
        }
    }

    private boolean isCurrentMonthLabel(TextView dayLabel) {
        return dayLabel.getCurrentTextColor() != ContextCompat.getColor(mContext, R.color.nextMonthDayColor);
    }

    private void onClick(Calendar day) {
        if (mEventDays == null) {
            mOnDayClickListener.onDayClick(new EventDay(day));
            return;
        }

        Stream.of(mEventDays).filter(eventDate ->
                eventDate.getCalendar().equals(day)).findFirst().ifPresentOrElse(
                calendarEventDay -> mOnDayClickListener.onDayClick(calendarEventDay),
                () -> mOnDayClickListener.onDayClick(new EventDay(day)));
    }
}
