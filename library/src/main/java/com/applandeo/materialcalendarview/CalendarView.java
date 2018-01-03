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

import com.annimon.stream.Stream;
import com.applandeo.materialcalendarview.adapters.CalendarPageAdapter;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.applandeo.materialcalendarview.extensions.CalendarViewPager;
import com.applandeo.materialcalendarview.listeners.OnCalendarPageChangeListener;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.applandeo.materialcalendarview.utils.CalendarProperties;
import com.applandeo.materialcalendarview.utils.DateUtils;
import com.applandeo.materialcalendarview.utils.SelectedDay;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.applandeo.materialcalendarview.adapters.CalendarPageAdapter.CALENDAR_SIZE;

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
 * - Set calendar type: type="classic or one_day_picker or many_days_picker or range_picker"
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

    public static final int CLASSIC = 0;
    public static final int ONE_DAY_PICKER = 1;
    public static final int MANY_DAYS_PICKER = 2;
    public static final int RANGE_PICKER = 3;

    private static final int FIRST_VISIBLE_PAGE = CALENDAR_SIZE / 2;

    private Context mContext;
    private CalendarPageAdapter mCalendarPageAdapter;

    private ImageButton mPreviousButton, mForwardButton;
    private TextView mCurrentMonthLabel;
    private int mCurrentPage;
    private CalendarViewPager mViewPager;
    private LinearLayout mAbbreviationsBar;

    private CalendarProperties mCalendarProperties;

    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initControl(context, attrs);
        initCalendar();
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initControl(context, attrs);
        initCalendar();
    }

    //protected constructor to create CalendarView for the dialog date picker
    protected CalendarView(Context context, CalendarProperties calendarProperties) {
        super(context);
        mContext = context;
        mCalendarProperties = calendarProperties;

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.calendar_view, this);

        initUiElements();
        initAttributes();
        initCalendar();
    }

    private void initControl(Context context, AttributeSet attrs) {
        mContext = context;
        mCalendarProperties = new CalendarProperties(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.calendar_view, this);

        initUiElements();
        setAttributes(attrs);
        initCalendar();
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

            // Abbreviations bar color
            int abbreviationsBarColor = typedArray.getColor(R.styleable.CalendarView_abbreviationsBarColor, 0);

            if (abbreviationsBarColor != 0) {
                mAbbreviationsBar.setBackgroundColor(abbreviationsBarColor);
            }

            // Abbreviations labels colors
            int abbreviationsLabelsColor = typedArray.getColor(R.styleable.CalendarView_abbreviationsLabelsColor, 0);

            if (abbreviationsLabelsColor != 0) {
                ((TextView)findViewById(R.id.mondayLabel)).setTextColor(abbreviationsLabelsColor);
                ((TextView)findViewById(R.id.tuesdayLabel)).setTextColor(abbreviationsLabelsColor);
                ((TextView)findViewById(R.id.wednesdayLabel)).setTextColor(abbreviationsLabelsColor);
                ((TextView)findViewById(R.id.thursdayLabel)).setTextColor(abbreviationsLabelsColor);
                ((TextView)findViewById(R.id.fridayLabel)).setTextColor(abbreviationsLabelsColor);
                ((TextView)findViewById(R.id.saturdayLabel)).setTextColor(abbreviationsLabelsColor);
                ((TextView)findViewById(R.id.sundayLabel)).setTextColor(abbreviationsLabelsColor);
            }

            // Calendar pages color
            int pagesColor = typedArray.getColor(R.styleable.CalendarView_pagesColor, 0);

            if (pagesColor != 0) {
                mViewPager.setBackgroundColor(pagesColor);
            }

            // Days numbers color
            mCalendarProperties.setDaysLabelsColor(typedArray.getColor(R.styleable.CalendarView_daysLabelsColor,
                    0));

            // Today number color
            mCalendarProperties.setTodayLabelColor(typedArray.getColor(R.styleable.CalendarView_todayLabelColor,
                    0));

            // Selection circle color
            mCalendarProperties.setSelectionColor(typedArray.getColor(R.styleable.CalendarView_selectionColor,
                    0));

            // Selection label color
            mCalendarProperties.setSelectionLabelColor(typedArray.getColor(R.styleable.CalendarView_selectionLabelColor,
                    0));

            // Disabled days label color
            mCalendarProperties.setDisabledDaysColor(typedArray.getColor(R.styleable.CalendarView_disabledDaysColor,
                    0));

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

            // Set calendar type
            mCalendarProperties.setCalendarType(typedArray.getInt(R.styleable.CalendarView_type, CLASSIC));

            // Set picker mode !DEPRECATED!
            if (typedArray.getBoolean(R.styleable.CalendarView_datePicker, false)) {
                mCalendarProperties.setCalendarType(ONE_DAY_PICKER);
            }

            // Sets layout for date picker or normal calendar
            setCalendarRowLayout();
        } finally {
            typedArray.recycle();
        }
    }

    private void setCalendarRowLayout() {
        if (mCalendarProperties.getCalendarType() == CLASSIC) {
            mCalendarProperties.setItemLayoutResource(R.layout.calendar_view_day);
        } else {
            mCalendarProperties.setItemLayoutResource(R.layout.calendar_view_picker_day);
        }
    }

    //This method set CalendarView attributes when the view is creating in the date picker
    private void initAttributes() {
        setCalendarRowLayout();

        if (mCalendarProperties.getHeaderColor() != 0) {
            ConstraintLayout mCalendarHeader = (ConstraintLayout) findViewById(R.id.calendarHeader);
            mCalendarHeader.setBackgroundColor(ContextCompat.getColor(mContext, mCalendarProperties.getHeaderColor()));
        }

        if (mCalendarProperties.getHeaderLabelColor() != 0) {
            mCurrentMonthLabel.setTextColor(ContextCompat.getColor(mContext, mCalendarProperties.getHeaderLabelColor()));
        }

        if (mCalendarProperties.getPreviousButtonSrc() != 0) {
            mPreviousButton.setImageResource(mCalendarProperties.getPreviousButtonSrc());
        }

        if (mCalendarProperties.getForwardButtonSrc() != 0) {
            mForwardButton.setImageResource(mCalendarProperties.getForwardButtonSrc());
        }

        if (mCalendarProperties.getAbbreviationsBarColor() != 0) {
            mAbbreviationsBar.setBackgroundColor(mCalendarProperties.getAbbreviationsBarColor());
        }

        if (mCalendarProperties.getAbbreviationsLabelsColor() != 0) {
            ((TextView)findViewById(R.id.mondayLabel)).setTextColor(mCalendarProperties.getAbbreviationsLabelsColor());
            ((TextView)findViewById(R.id.tuesdayLabel)).setTextColor(mCalendarProperties.getAbbreviationsLabelsColor());
            ((TextView)findViewById(R.id.wednesdayLabel)).setTextColor(mCalendarProperties.getAbbreviationsLabelsColor());
            ((TextView)findViewById(R.id.thursdayLabel)).setTextColor(mCalendarProperties.getAbbreviationsLabelsColor());
            ((TextView)findViewById(R.id.fridayLabel)).setTextColor(mCalendarProperties.getAbbreviationsLabelsColor());
            ((TextView)findViewById(R.id.saturdayLabel)).setTextColor(mCalendarProperties.getAbbreviationsLabelsColor());
            ((TextView)findViewById(R.id.sundayLabel)).setTextColor(mCalendarProperties.getAbbreviationsLabelsColor());
        }

        if (mCalendarProperties.getPagesColor() != 0) {
            mViewPager.setBackgroundColor(mCalendarProperties.getPagesColor());
        }
    }

    private void initUiElements() {
        // This line subtracts a half of all calendar months to set calendar
        // in the correct position (in the middle)
        mCalendarProperties.getCurrentDate().set(Calendar.MONTH, -FIRST_VISIBLE_PAGE);

        mForwardButton = (ImageButton) findViewById(R.id.forwardButton);
        mForwardButton.setOnClickListener(onNextClickListener);

        mPreviousButton = (ImageButton) findViewById(R.id.previousButton);
        mPreviousButton.setOnClickListener(onPreviousClickListener);

        mCurrentMonthLabel = (TextView) findViewById(R.id.currentDateLabel);

        mAbbreviationsBar = (LinearLayout) findViewById(R.id.abbreviationsBar);
        mViewPager = (CalendarViewPager) findViewById(R.id.calendarViewPager);
    }

    private void initCalendar() {
        mCalendarPageAdapter = new CalendarPageAdapter(mContext, mCalendarProperties);

        mViewPager.setAdapter(mCalendarPageAdapter);
        mViewPager.addOnPageChangeListener(onPageChangeListener);

        // This line move calendar to the middle page
        mViewPager.setCurrentItem(FIRST_VISIBLE_PAGE);
    }

    public void setOnPreviousPageChangeListener(OnCalendarPageChangeListener listener) {
        mCalendarProperties.setOnPreviousPageChangeListener(listener);
    }

    public void setOnForwardPageChangeListener(OnCalendarPageChangeListener listener) {
        mCalendarProperties.setOnForwardPageChangeListener(listener);
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
            Calendar calendar = (Calendar) mCalendarProperties.getCurrentDate().clone();
            calendar.add(Calendar.MONTH, position);

            if (!isScrollingLimited(calendar, position)) {
                setHeaderName(calendar, position);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

    private boolean isScrollingLimited(Calendar calendar, int position) {
        if (DateUtils.isMonthBefore(mCalendarProperties.getMinimumDate(), calendar)) {
            mViewPager.setCurrentItem(position + 1);
            return true;
        }

        if (DateUtils.isMonthAfter(mCalendarProperties.getMaximumDate(), calendar)) {
            mViewPager.setCurrentItem(position - 1);
            return true;
        }

        return false;
    }

    private void setHeaderName(Calendar calendar, int position) {
        mCurrentMonthLabel.setText(DateUtils.getMonthAndYearDate(mContext, calendar));
        callOnPageChangeListeners(position);
    }

    // This method calls page change listeners after swipe calendar or click arrow buttons
    private void callOnPageChangeListeners(int position) {
        if (position > mCurrentPage && mCalendarProperties.getOnForwardPageChangeListener() != null) {
            mCalendarProperties.getOnForwardPageChangeListener().onChange();
        }

        if (position < mCurrentPage && mCalendarProperties.getOnPreviousPageChangeListener() != null) {
            mCalendarProperties.getOnPreviousPageChangeListener().onChange();
        }

        mCurrentPage = position;
    }

    /**
     * @param onDayClickListener OnDayClickListener interface responsible for handle clicks on calendar cells
     * @see OnDayClickListener
     */
    public void setOnDayClickListener(OnDayClickListener onDayClickListener) {
        mCalendarProperties.setOnDayClickListener(onDayClickListener);
    }

    /**
     * This method set a current and selected date of the calendar using Calendar object.
     *
     * @param date A Calendar object representing a date to which the calendar will be set
     */
    public void setDate(Calendar date) throws OutOfDateRangeException {
        if (mCalendarProperties.getMinimumDate() != null && date.before(mCalendarProperties.getMinimumDate())) {
            throw new OutOfDateRangeException("SET DATE EXCEEDS THE MINIMUM DATE");
        }

        if (mCalendarProperties.getMaximumDate() != null && date.after(mCalendarProperties.getMaximumDate())) {
            throw new OutOfDateRangeException("SET DATE EXCEEDS THE MAXIMUM DATE");
        }

        DateUtils.setMidnight(date);

        mCalendarProperties.getSelectedDate().setTime(date.getTime());

        mCalendarProperties.getCurrentDate().setTime(date.getTime());
        mCalendarProperties.getCurrentDate().add(Calendar.MONTH, -FIRST_VISIBLE_PAGE);
        mCurrentMonthLabel.setText(DateUtils.getMonthAndYearDate(mContext, date));

        mViewPager.setCurrentItem(FIRST_VISIBLE_PAGE);
        mCalendarPageAdapter.notifyDataSetChanged();
    }

    /**
     * This method set a current and selected date of the calendar using Date object.
     *
     * @param currentDate A date to which the calendar will be set
     */
    public void setDate(Date currentDate) throws OutOfDateRangeException {
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
        if (mCalendarProperties.getCalendarType() == CLASSIC) {
            mCalendarProperties.setEventDays(eventDays);
            mCalendarPageAdapter.notifyDataSetChanged();
        }
    }

    /**
     * @return List of Calendar object representing a selected dates
     */
    public List<Calendar> getSelectedDates() {
        return Stream.of(mCalendarPageAdapter.getSelectedDays())
                .map(SelectedDay::getCalendar)
                .sortBy(calendar -> calendar).toList();
    }

    /**
     * @return Calendar object representing a selected date
     */
    @Deprecated
    public Calendar getSelectedDate() {
        return getFirstSelectedDate();
    }

    /**
     * @return Calendar object representing a selected date
     */
    public Calendar getFirstSelectedDate() {
        return Stream.of(mCalendarPageAdapter.getSelectedDays())
                .map(SelectedDay::getCalendar).findFirst().get();
    }

    /**
     * @return Calendar object representing a date of current calendar page
     */
    public Calendar getCurrentPageDate() {
        Calendar calendar = (Calendar) mCalendarProperties.getCurrentDate().clone();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, mViewPager.getCurrentItem());
        return calendar;
    }

    /**
     * This method set a minimum available date in calendar
     *
     * @param calendar Calendar object representing a minimum date
     */
    public void setMinimumDate(Calendar calendar) {
        mCalendarProperties.setMinimumDate(calendar);
    }

    /**
     * This method set a maximum available date in calendar
     *
     * @param calendar Calendar object representing a maximum date
     */
    public void setMaximumDate(Calendar calendar) {
        mCalendarProperties.setMaximumDate(calendar);
    }

    /**
     * This method is used to return to current month page
     */
    public void showCurrentMonthPage() {
        mViewPager.setCurrentItem(mViewPager.getCurrentItem()
                - DateUtils.getMonthsBetweenDates(DateUtils.getCalendar(), getCurrentPageDate()), true);
    }

    public void setDisabledDays(List<Calendar> disabledDays) {
        mCalendarProperties.setDisabledDays(disabledDays);
    }
}
