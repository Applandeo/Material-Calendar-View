package com.applandeo.materialcalendarview.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.R;
import com.applandeo.materialcalendarview.extensions.CalendarGridView;
import com.applandeo.materialcalendarview.listeners.DayRowClickListener;
import com.applandeo.materialcalendarview.utils.CalendarProperties;
import com.applandeo.materialcalendarview.utils.SelectedDay;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * This class is responsible for loading a calendar page content.
 * <p>
 * Created by Mateusz Kornakiewicz on 24.05.2017.
 */

public class CalendarPageAdapter extends PagerAdapter {

    /**
     * A number of months (pages) in the calendar
     * 2401 months means 1200 months (100 years) before and 1200 months after the current month
     */
    public static final int CALENDAR_SIZE = 2401;

    private Context mContext;
    private CalendarGridView mCalendarGridView;

    private List<SelectedDay> mSelectedDays = new ArrayList<>();

    private CalendarProperties mCalendarProperties;

    private int mPageMonth;

    public CalendarPageAdapter(Context context, CalendarProperties calendarProperties) {
        mContext = context;
        mCalendarProperties = calendarProperties;

        if (mCalendarProperties.getCalendarType() == CalendarView.ONE_DAY_PICKER) {
            addSelectedDay(new SelectedDay(calendarProperties.getSelectedDate()));
        }
    }

    @Override
    public int getCount() {
        return CALENDAR_SIZE;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mCalendarGridView = (CalendarGridView) inflater.inflate(R.layout.calendar_view_grid, null);

        loadMonth(position);

        mCalendarGridView.setOnItemClickListener(new DayRowClickListener(this,
                mCalendarProperties, mPageMonth));

        container.addView(mCalendarGridView);
        return mCalendarGridView;
    }

    public void addSelectedDay(SelectedDay selectedDay) {
        if (!mSelectedDays.contains(selectedDay)) {
            mSelectedDays.add(selectedDay);
            informDatePicker();
            return;
        }

        mSelectedDays.remove(selectedDay);
        informDatePicker();
    }

    public List<SelectedDay> getSelectedDays() {
        return mSelectedDays;
    }

    public SelectedDay getSelectedDay() {
        return mSelectedDays.get(0);
    }

    public void setSelectedDay(SelectedDay selectedDay) {
        mSelectedDays.clear();
        mSelectedDays.add(selectedDay);
        informDatePicker();
    }

    /**
     * This method inform DatePicker about ability to return selected days
     */
    private void informDatePicker() {
        if (mCalendarProperties.getOnSelectionAbilityListener() != null) {
            mCalendarProperties.getOnSelectionAbilityListener().onChange(mSelectedDays.size() > 0);
        }
    }

    /**
     * This method fill calendar GridView with days
     *
     * @param position Position of current page in ViewPager
     */
    private void loadMonth(int position) {
        ArrayList<Date> days = new ArrayList<>();

        // Get Calendar object instance
        Calendar calendar = (Calendar) mCalendarProperties.getCurrentDate().clone();

        // Add months to Calendar (a number of months depends on ViewPager position)
        calendar.add(Calendar.MONTH, position);

        // Set day of month as 1
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        // Get a number of the first day of the week
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        // Count when month is beginning
        int monthBeginningCell = dayOfWeek + (dayOfWeek == 1 ? 5 : -2);

        // Subtract a number of beginning days, it will let to load a part of a previous month
        calendar.add(Calendar.DAY_OF_MONTH, -monthBeginningCell);

        /*
        Get all days of one page (42 is a number of all possible cells in one page
        (a part of previous month, current month and a part of next month))
         */
        while (days.size() < 42) {
            days.add(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        mPageMonth = calendar.get(Calendar.MONTH) - 1;
        CalendarDayAdapter calendarDayAdapter = new CalendarDayAdapter(this, mContext,
                mCalendarProperties, days, mPageMonth);

        mCalendarGridView.setAdapter(calendarDayAdapter);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
