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
import com.applandeo.materialcalendarview.utils.CalendarProperties;
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

    private CalendarProperties mCalendarProperties;

    public DayRowClickListener(CalendarPageAdapter calendarPageAdapter, Context context,
                               CalendarProperties calendarProperties) {
        mCalendarPageAdapter = calendarPageAdapter;
        mContext = context;
        mCalendarProperties = calendarProperties;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Calendar day = new GregorianCalendar();
        day.setTime((Date) adapterView.getItemAtPosition(position));

        switch (mCalendarProperties.getCalendarType()) {
            case CalendarView.ONE_DAY_PICKER:
                selectOneDay(view, day);
                break;

            case CalendarView.MANY_DAYS_PICKER:
                selectManyDays(view, day);
                break;

            case CalendarView.RANGE_PICKER:
                selectRange(view, day);
                break;

            case CalendarView.CLASSIC:
                if (mCalendarProperties.getOnDayClickListener() != null) {
                    mCalendarPageAdapter.setSelectedDay(new SelectedDay(view, day));
                    onClick(day);
                }
        }
    }

    private void selectOneDay(View view, Calendar day) {
        SelectedDay previousSelectedDay = mCalendarPageAdapter.getSelectedDay();

        TextView dayLabel = (TextView) view.findViewById(R.id.dayLabel);

        if (isAnotherDaySelected(previousSelectedDay, dayLabel, day)) {
            selectDay(dayLabel, day);
            reverseUnselectedColor(previousSelectedDay);
        }
    }

    private void selectManyDays(View view, Calendar day) {
        TextView dayLabel = (TextView) view.findViewById(R.id.dayLabel);

        if (isCurrentMonthLabel(dayLabel)) {
            SelectedDay selectedDay = new SelectedDay(dayLabel, day);

            if (!mCalendarPageAdapter.getSelectedDays().contains(selectedDay)) {
                DayColorsUtils.setSelectedDayColors(mContext, dayLabel, mCalendarProperties.getSelectionColor());
            } else {
                reverseUnselectedColor(selectedDay);
            }

            mCalendarPageAdapter.addSelectedDay(selectedDay);
        }
    }

    private void selectRange(View view, Calendar day) {
        TextView dayLabel = (TextView) view.findViewById(R.id.dayLabel);

        if (!isCurrentMonthLabel(dayLabel)) {
            return;
        }

        List<SelectedDay> selectedDays = mCalendarPageAdapter.getSelectedDays();

        if (selectedDays.size() > 1) {
            clearAndSelectOne(dayLabel, day);
        }

        if (selectedDays.size() == 1) {
            selectOneAndRange(dayLabel, day);
        }

        if (selectedDays.isEmpty()) {
            selectDay(dayLabel, day);
        }
    }

    private void clearAndSelectOne(TextView dayLabel, Calendar day) {
        Stream.of(mCalendarPageAdapter.getSelectedDays()).forEach(this::reverseUnselectedColor);
        selectDay(dayLabel, day);
    }

    private void selectOneAndRange(TextView dayLabel, Calendar day) {
        SelectedDay previousSelectedDay = mCalendarPageAdapter.getSelectedDay();

        Stream.of(DateUtils.getDatesRange(previousSelectedDay.getCalendar(), day))
                .forEach(calendar -> mCalendarPageAdapter.addSelectedDay(new SelectedDay(calendar)));

        DayColorsUtils.setSelectedDayColors(mContext, dayLabel, mCalendarProperties.getSelectionColor());

        mCalendarPageAdapter.addSelectedDay(new SelectedDay(dayLabel, day));
        mCalendarPageAdapter.notifyDataSetChanged();
    }

    private void selectDay(TextView dayLabel, Calendar day) {
        DayColorsUtils.setSelectedDayColors(mContext, dayLabel, mCalendarProperties.getSelectionColor());
        mCalendarPageAdapter.setSelectedDay(new SelectedDay(dayLabel, day));
    }

    private void reverseUnselectedColor(SelectedDay selectedDay) {
        DayColorsUtils.setCurrentMonthDayColors(mContext, selectedDay.getCalendar(),
                DateUtils.getCalendar(), (TextView) selectedDay.getView(), mCalendarProperties.getTodayLabelColor());
    }

    private boolean isCurrentMonthLabel(TextView dayLabel) {
        return dayLabel.getCurrentTextColor() != ContextCompat.getColor(mContext, R.color.nextMonthDayColor);
    }

    private boolean isAnotherDaySelected(SelectedDay selectedDay, TextView dayLabel, Calendar day) {
        return selectedDay != null && !day.equals(selectedDay.getCalendar()) && isCurrentMonthLabel(dayLabel);
    }

    private void onClick(Calendar day) {
        if (mCalendarProperties.getEventDays() == null) {
            mCalendarProperties.getOnDayClickListener().onDayClick(new EventDay(day));
            return;
        }

        Stream.of(mCalendarProperties.getEventDays())
                .filter(eventDate -> eventDate.getCalendar().equals(day))
                .findFirst()
                .ifPresentOrElse(
                        calendarEventDay -> mCalendarProperties.getOnDayClickListener().onDayClick(calendarEventDay),
                        () -> mCalendarProperties.getOnDayClickListener().onDayClick(new EventDay(day)));
    }
}
