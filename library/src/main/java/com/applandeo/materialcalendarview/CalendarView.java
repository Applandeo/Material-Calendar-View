package com.applandeo.materialcalendarview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.applandeo.materialcalendarview.adapters.CalendarPageAdapter;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.applandeo.materialcalendarview.listeners.OnNavigationButtonClickListener;
import com.applandeo.materialcalendarview.utils.DateUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * This class represents a view, displays to user as calendar. It allows to work in date picker
 * mode or like a normal calendar. In a normal calendar mode it can displays an image under the day
 * number. In both modes it marks today day. It also provides click on day events using
 * OnDayClickListener which returns an EventDay object.
 *
 * @see EventDay
 * @see OnDayClickListener
 * <p>
 * <p>
 * XML attributes:
 * - Set calendar as date picker: datePicker="true"
 * - Set calendar header color: headerColor="@color/[color]"
 * - Set calendar header label color: headerLabelColor="@color/[color]"
 * - Set previous button resource: previousButtonSrc="@drawable/[drawable]"
 * - Ser forward button resource: forwardButtonSrc="@drawable/[drawable]"
 * - Set today label color: todayLabelColor="@color/[color]"
 * - Set selection color: selectionColor="@color/[color]"
 * <p>
 * Created by Mateusz Kornakiewicz on 23.05.2017.
 */

public class CalendarView extends LinearLayout {

    /**
     * A number of months in the calendar
     * 2401 months mean 1200 months (100 years) before and 1200 months after the current month
     */
    public static final int CALENDAR_SIZE = 2401;

    // The middle page of the calendar
    private static final int MIDDLE_PAGE = CALENDAR_SIZE / 2;

    private Context mContext;
    private CalendarPageAdapter mCalendarPageAdapter;

    private Calendar mCurrentDate = DateUtils.getCalendar();
    private Calendar mSelectedDate = DateUtils.getCalendar();

    private ImageButton mPreviousButton, mForwardButton;
    private TextView mCurrentMonthLabel;
    private int mCurrentPage;
    private ViewPager mViewPager;

    private boolean mIsDatePicker;

    private int mItemLayoutResource;
    private int mTodayLabelColor;
    private int mSelectionColor;
    private String[] mMonthsNames;

    private int mHeaderColor;
    private int mHeaderLabelColor;
    private int mPreviousButtonSrc;
    private int mForwardButtonSrc;
    private int mDaysNames;
    private OnNavigationButtonClickListener mOnPreviousButtonClickListener;
    private OnNavigationButtonClickListener mOnForwardButtonClickListener;

    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initControl(context, attrs);
        initCalendar();
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initControl(context, attrs);
        initCalendar();
    }

    //private constructor to create CalendarView using CalendarView.CalendarBuilder
    public CalendarView(Context context, boolean isDatePicker, int headerColor, int headerLabelColor,
                        int previousButtonSrc, int forwardButtonSrc, int selectionColor,
                        int todayLabelColor, String[] monthsNames, int daysNames) {
        super(context);
        mContext = context;
        mIsDatePicker = isDatePicker;
        mHeaderColor = headerColor;
        mHeaderLabelColor = headerLabelColor;
        mPreviousButtonSrc = previousButtonSrc;
        mForwardButtonSrc = forwardButtonSrc;
        mSelectionColor = selectionColor;
        mTodayLabelColor = todayLabelColor;
        mMonthsNames = monthsNames;
        mDaysNames = daysNames;
    }

    private void initControl(Context context, AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.calendar_view, this);

        initUiElements();
        setAttributes(attrs);
        initCalendar();
    }

    public CalendarView create() {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.calendar_view, this);

        initUiElements();
        initAttributes();
        initCalendar();
        return this;
    }

    /**
     * This method set xml values for calendar elements
     *
     * @param attrs A set of xml attributes
     */
    private void setAttributes(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CalendarView);

        try {
            // Calendar header color
            int headerColor = typedArray.getColor(R.styleable.CalendarView_headerColor, 0);

            if (headerColor != 0) {
                ConstraintLayout mCalendarHeader = (ConstraintLayout) findViewById(R.id.calendarHeader);
                mCalendarHeader.setBackgroundColor(headerColor);
            }

            // Calendar header label (month and year) color
            int headerLabelColor = typedArray.getColor(R.styleable.CalendarView_headerLabelColor, 0);

            if (headerLabelColor != 0) {
                mCurrentMonthLabel.setTextColor(headerLabelColor);
            }

            // Today number color
            mTodayLabelColor = typedArray.getColor(R.styleable.CalendarView_todayLabelColor,
                    ContextCompat.getColor(mContext, R.color.defaultColor));

            // Selection circle color
            mSelectionColor = typedArray.getColor(R.styleable.CalendarView_selectionColor,
                    ContextCompat.getColor(mContext, R.color.defaultColor));

            // Previous arrow resource
            Drawable previousButtonScr = typedArray.getDrawable(R.styleable.CalendarView_previousButtonSrc);

            if (previousButtonScr != null) {
                mPreviousButton.setImageDrawable(previousButtonScr);
            }

            // Forward arrow resource
            Drawable forwardButtonScr = typedArray.getDrawable(R.styleable.CalendarView_forwardButtonSrc);

            if (forwardButtonScr != null) {
                mForwardButton.setImageDrawable(forwardButtonScr);
            }

            // Set picker mode
            mIsDatePicker = typedArray.getBoolean(R.styleable.CalendarView_datePicker, false);

            // Sets layout for date picker or normal calendar
            if (mIsDatePicker) {
                mItemLayoutResource = R.layout.calendar_view_picker_day;
            } else {
                mItemLayoutResource = R.layout.calendar_view_day;
            }

            // Sets translations for months names
            int namesArray = typedArray.getResourceId(R.styleable.CalendarView_monthsNames, R.array.months_array);
            mMonthsNames = getResources().getStringArray(namesArray);

            // Checks if array has 12 elements, if not then set a english names
            if (mMonthsNames.length < 12) {
                mMonthsNames = getResources().getStringArray(R.array.months_array);
            }

            // Sets translations for day names symbols
            int symbolArray = typedArray.getResourceId(R.styleable.CalendarView_daysNames, 0);
            if (symbolArray != 0) {
                setDaysSymbols(symbolArray);
            }

        } finally {
            typedArray.recycle();
        }
    }

    //This method set CalendarView attributes when the view is creating using CalendarBuilder
    private void initAttributes() {
        if (mIsDatePicker) {
            mItemLayoutResource = R.layout.calendar_view_picker_day;
        } else {
            mItemLayoutResource = R.layout.calendar_view_day;
        }

        if (mHeaderColor != 0) {
            ConstraintLayout mCalendarHeader = (ConstraintLayout) findViewById(R.id.calendarHeader);
            mCalendarHeader.setBackgroundColor(ContextCompat.getColor(mContext, mHeaderColor));
        }

        if (mHeaderLabelColor != 0) {
            mCurrentMonthLabel.setTextColor(ContextCompat.getColor(mContext, mHeaderLabelColor));
        }

        if (mPreviousButtonSrc != 0) {
            mPreviousButton.setImageResource(mPreviousButtonSrc);
        }

        if (mForwardButtonSrc != 0) {
            mForwardButton.setImageResource(mForwardButtonSrc);
        }

        if (mSelectionColor != 0) {
            mSelectionColor = ContextCompat.getColor(mContext, mSelectionColor);
        } else {
            mSelectionColor = ContextCompat.getColor(mContext, R.color.defaultColor);
        }

        if (mTodayLabelColor != 0) {
            mTodayLabelColor = ContextCompat.getColor(mContext, mTodayLabelColor);
        } else {
            mTodayLabelColor = ContextCompat.getColor(mContext, R.color.defaultColor);
        }

        if (mMonthsNames == null || mMonthsNames.length < 12) {
            mMonthsNames = getResources().getStringArray(R.array.months_array);
        }

        if (mDaysNames != 0) {
            setDaysSymbols(mDaysNames);
        }
    }

    //This method sets days symbols
    private void setDaysSymbols(int array) {
        String[] daysSymbols = getResources().getStringArray(array);

        if (daysSymbols.length == 7) {
            ((TextView) findViewById(R.id.mondayLabel)).setText(daysSymbols[0]);
            ((TextView) findViewById(R.id.tuesdayLabel)).setText(daysSymbols[1]);
            ((TextView) findViewById(R.id.wednesdayLabel)).setText(daysSymbols[2]);
            ((TextView) findViewById(R.id.thursdayLabel)).setText(daysSymbols[3]);
            ((TextView) findViewById(R.id.fridayLabel)).setText(daysSymbols[4]);
            ((TextView) findViewById(R.id.saturdayLabel)).setText(daysSymbols[5]);
            ((TextView) findViewById(R.id.sundayLabel)).setText(daysSymbols[6]);
        }
    }

    private void initUiElements() {
        // This line subtracts a half of all calendar months to set calendar
        // in the correct position (in the middle)
        mCurrentDate.add(Calendar.MONTH, -MIDDLE_PAGE);

        mForwardButton = (ImageButton) findViewById(R.id.forwardButton);
        mForwardButton.setOnClickListener(onNextClickListener);

        mPreviousButton = (ImageButton) findViewById(R.id.previousButton);
        mPreviousButton.setOnClickListener(onPreviousClickListener);

        mCurrentMonthLabel = (TextView) findViewById(R.id.currentDateLabel);

        mViewPager = (ViewPager) findViewById(R.id.calendarViewPager);
    }

    private void initCalendar() {
        mCalendarPageAdapter = new CalendarPageAdapter(mContext, mCurrentDate, mIsDatePicker,
                mSelectedDate, mItemLayoutResource, mTodayLabelColor, mSelectionColor);

        mViewPager.setAdapter(mCalendarPageAdapter);
        mViewPager.addOnPageChangeListener(onPageChangeListener);

        // This line move calendar to the middle page
        mViewPager.setCurrentItem(MIDDLE_PAGE);
    }

    public void setOnPreviousButtonClickListener(OnNavigationButtonClickListener onPreviousButtonClickListener) {
        mOnPreviousButtonClickListener = onPreviousButtonClickListener;
    }

    public void setOnForwardButtonClickListener(OnNavigationButtonClickListener onForwardButtonClickListener) {
        mOnForwardButtonClickListener = onForwardButtonClickListener;
    }

    private final OnClickListener onNextClickListener =
            v -> mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);

    private final OnClickListener onPreviousClickListener =
            v -> mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);

    private final ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        /**
         * This method set calendar header label
         *
         * @param position Current ViewPager position
         * @see ViewPager.OnPageChangeListener
         */
        @Override
        public void onPageSelected(int position) {
            Calendar calendar = (Calendar) mCurrentDate.clone();
            calendar.add(Calendar.MONTH, position);
            mCurrentMonthLabel.setText(DateUtils.getMonthAndYearDate(mMonthsNames, calendar));
            callNavigationListeners(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

    // This method calls navigation button listeners after swipe calendar or click arrow buttons
    private void callNavigationListeners(int position) {
        if (position > mCurrentPage && mOnForwardButtonClickListener != null) {
            mOnForwardButtonClickListener.onClick();
        }

        if (position < mCurrentPage && mOnPreviousButtonClickListener != null) {
            mOnPreviousButtonClickListener.onClick();
        }

        mCurrentPage = position;
    }


    /**
     * @param onDayClickListener OnDayClickListener interface responsible for handle clicks on calendar cells
     * @see OnDayClickListener
     */
    public void setOnDayClickListener(OnDayClickListener onDayClickListener) {
        mCalendarPageAdapter.setOnDayClickListener(onDayClickListener);
        mCalendarPageAdapter.notifyDataSetChanged();
    }

    /**
     * This method set a current and selected date of the calendar using Calendar object.
     *
     * @param date A Calendar object representing a date to which the calendar will be set
     */
    public void setDate(Calendar date) {
        DateUtils.setMidnight(date);

        mSelectedDate.setTime(date.getTime());
        mCalendarPageAdapter.setSelectedDate(mSelectedDate);

        mCurrentDate.setTime(date.getTime());
        mCurrentDate.add(Calendar.MONTH, -MIDDLE_PAGE);
        mCurrentMonthLabel.setText(DateUtils.getMonthAndYearDate(mMonthsNames, date));

        mViewPager.setCurrentItem(MIDDLE_PAGE);
        mCalendarPageAdapter.notifyDataSetChanged();
    }

    /**
     * This method set a current and selected date of the calendar using Date object.
     *
     * @param currentDate A date to which the calendar will be set
     */
    public void setDate(Date currentDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        setDate(calendar);
    }

    /**
     * This method is used to set a list of events displayed in calendar cells,
     * visible as images under the day number.
     *
     * @param eventDays List of EventDay objects
     * @see EventDay
     */
    public void setEvents(List<EventDay> eventDays) {
        if (!mIsDatePicker) {
            mCalendarPageAdapter.setEvents(eventDays);
        }
    }

    /**
     * @return Calendar object representing a selected date
     */
    public Calendar getSelectedDate() {
        return mCalendarPageAdapter.getSelectedDate();
    }

    /**
     * @return Calendar object representing a date of current calendar page
     */
    public Calendar getCurrentPageDate() {
        Calendar calendar = (Calendar) mCurrentDate.clone();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, mViewPager.getCurrentItem());
        return calendar;
    }
}
