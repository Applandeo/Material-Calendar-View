package com.applandeo.materialcalendarview.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.annimon.stream.Stream;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.R;
import com.applandeo.materialcalendarview.utils.DateUtils;
import com.applandeo.materialcalendarview.utils.DayColorsUtils;
import com.applandeo.materialcalendarview.utils.ImageUtils;
import com.applandeo.materialcalendarview.utils.SelectedDay;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * This class is responsible for loading a one day cell.
 * <p>
 * Created by Mateusz Kornakiewicz on 24.05.2017.
 */

class CalendarDayAdapter extends ArrayAdapter<Date> {
    private CalendarPageAdapter mCalendarPageAdapter;
    private Context mContext;
    private List<EventDay> mEventDays;
    private LayoutInflater mLayoutInflater;
    private int mItemLayoutResource;
    private int mMonth;
    private Calendar mToday = DateUtils.getCalendar();
    private boolean mIsDatePicker;
    private int mTodayLabelColor;
    private int mSelectionColor;

    CalendarDayAdapter(CalendarPageAdapter calendarPageAdapter, Context context, int itemLayoutResource,
                       ArrayList<Date> dates, List<EventDay> eventDays, int month, boolean isDatePicker,
                       int todayLabelColor, int selectionColor) {
        super(context, itemLayoutResource, dates);

        mCalendarPageAdapter = calendarPageAdapter;
        mContext = context;
        mEventDays = eventDays;
        mMonth = month < 0 ? 11 : month;
        mLayoutInflater = LayoutInflater.from(context);
        mItemLayoutResource = itemLayoutResource;
        mIsDatePicker = isDatePicker;
        mTodayLabelColor = todayLabelColor;
        mSelectionColor = selectionColor;
    }

    @NonNull
    @Override
    public View getView(int position, View view, @NonNull ViewGroup parent) {
        if (view == null) {
            view = mLayoutInflater.inflate(mItemLayoutResource, parent, false);
        }

        TextView dayLabel = (TextView) view.findViewById(R.id.dayLabel);
        ImageView dayIcon = (ImageView) view.findViewById(R.id.dayIcon);

        Calendar day = new GregorianCalendar();
        day.setTime(getItem(position));

        // Loading an image of the event
        if (dayIcon != null) {
            loadIcon(dayIcon, day);
        }

        if (mIsDatePicker && day.equals(mCalendarPageAdapter.getSelectedDate())
                && day.get(Calendar.MONTH) == mMonth) {

            // Setting selected day color
            mCalendarPageAdapter.setSelectedDay(new SelectedDay(dayLabel, day));
            DayColorsUtils.setSelectedDayColors(mContext, dayLabel, mSelectionColor);

        } else {
            if (day.get(Calendar.MONTH) == mMonth) { // Setting current month day color
                DayColorsUtils.setCurrentMonthDayColors(mContext, day, mToday, dayLabel, mTodayLabelColor);
            } else { // Setting not current month day color
                DayColorsUtils.setDayColors(dayLabel, ContextCompat.getColor(mContext,
                        R.color.nextMonthDayColor), Typeface.NORMAL, R.drawable.background_transparent);
            }
        }

        dayLabel.setText(String.valueOf(day.get(Calendar.DAY_OF_MONTH)));
        return view;
    }

    private void loadIcon(ImageView dayIcon, Calendar day) {
        if (mEventDays == null || mIsDatePicker) {
            dayIcon.setVisibility(View.GONE);
            return;
        }

        Stream.of(mEventDays).filter(eventDate ->
                eventDate.getCalendar().equals(day)).findFirst().executeIfPresent(eventDay -> {

            ImageUtils.loadResource(dayIcon, eventDay.getImageResource());

            // If a day doesn't belong to current month then image is transparent
            if (day.get(Calendar.MONTH) != mMonth) {
                dayIcon.setAlpha(0.2f);
            }

        });
    }
}
