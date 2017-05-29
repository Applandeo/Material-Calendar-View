package com.applandeo.materialcalendarview.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.applandeo.materialcalendarview.R;
import com.applandeo.materialcalendarview.listeners.DayRowClickListener;
import com.applandeo.materialcalendarview.utils.SelectedDay;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.applandeo.materialcalendarview.CalendarView.CALENDAR_SIZE;

/**
 * This class is responsible for loading a calendar page content.
 * <p>
 * Created by Mateusz Kornakiewicz on 24.05.2017.
 */

public class CalendarPageAdapter extends PagerAdapter {
    private Context mContext;
    private List<EventDay> mEventDays = new ArrayList<>();
    private boolean mIsDatePicker;
    private GridView mCalendarGridView;
    private Calendar mCurrentDate;
    private Calendar mSelectedDate;
    private int mItemLayoutResource;
    private int mTodayLabelColor;
    private int mSelectionColor;
    private OnDayClickListener mOnDayClickListener = null;
    private SelectedDay mSelectedDay;

    public CalendarPageAdapter(Context context, Calendar currentDate, boolean isDatePicker,
                               Calendar selectedDate, int itemLayoutResource, int todayLabelColor,
                               int selectionColor) {
        mContext = context;
        mCurrentDate = currentDate;
        mIsDatePicker = isDatePicker;
        mSelectedDate = selectedDate;
        mItemLayoutResource = itemLayoutResource;
        mTodayLabelColor = todayLabelColor;
        mSelectionColor = selectionColor;
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
        View viewLayout = inflater.inflate(R.layout.calendar_view_grid, container, false);

        mCalendarGridView = (GridView) viewLayout.findViewById(R.id.calendarGridView);
        mCalendarGridView.setOnItemClickListener(new DayRowClickListener(this, mContext, mEventDays,
                mOnDayClickListener, mIsDatePicker, mTodayLabelColor, mSelectionColor));

        loadMonth(position);

        container.addView(viewLayout);
        return viewLayout;
    }

    public void setOnDayClickListener(OnDayClickListener onDayClickListener) {
        mOnDayClickListener = onDayClickListener;
    }

    public void setEvents(List<EventDay> eventDays) {
        mEventDays = eventDays;
        notifyDataSetChanged();
    }

    public void setSelectedDate(Calendar selectedDate) {
        mSelectedDate = selectedDate;
    }

    public Calendar getSelectedDate() {
        return mSelectedDate;
    }

    public void setSelectedDay(SelectedDay selectedDay) {
        mSelectedDay = selectedDay;
    }

    public SelectedDay getSelectedDay() {
        return mSelectedDay;
    }

    /**
     * This method fill calendar GridView with days
     * @param position Position of current page in ViewPager
     */
    private void loadMonth(int position) {
        ArrayList<Date> days = new ArrayList<>();

        // Get Calendar object instance
        Calendar calendar = (Calendar) mCurrentDate.clone();

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

        CalendarDayAdapter calendarDayAdapter = new CalendarDayAdapter(this, mContext,
                mItemLayoutResource, days, mEventDays, calendar.get(Calendar.MONTH) - 1,
                mIsDatePicker, mTodayLabelColor, mSelectionColor);

        mCalendarGridView.setAdapter(calendarDayAdapter);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
