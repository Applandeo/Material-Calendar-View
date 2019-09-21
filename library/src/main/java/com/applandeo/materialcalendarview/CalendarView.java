package com.applandeo.materialcalendarview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import androidx.annotation.ColorRes;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.annimon.stream.Stream;
import com.applandeo.materialcalendarview.adapters.CalendarPageAdapter;
import com.applandeo.materialcalendarview.exceptions.ErrorsMessages;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.applandeo.materialcalendarview.extensions.CalendarViewPager;
import com.applandeo.materialcalendarview.listeners.OnCalendarPageChangeListener;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.applandeo.materialcalendarview.utils.AppearanceUtils;
import com.applandeo.materialcalendarview.utils.CalendarProperties;
import com.applandeo.materialcalendarview.utils.DateUtils;
import com.applandeo.materialcalendarview.utils.SelectedDay;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.applandeo.materialcalendarview.utils.CalendarProperties.FIRST_VISIBLE_PAGE;

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

    private Context mContext;
    private CalendarPageAdapter mCalendarPageAdapter;

    private ImageButton mForwardButton;
    private ImageButton mPreviousButton;
    private TextView mCurrentMonthLabel;
    private int mCurrentPage;
    private CalendarViewPager mViewPager;

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
    }

    /**
     * This method set xml values for calendar elements
     *
     * @param attrs A set of xml attributes
     */
    private void setAttributes(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CalendarView);

        try {
            initCalendarProperties(typedArray);
            initAttributes();
        } finally {
            typedArray.recycle();
        }
    }

    private void initCalendarProperties(TypedArray typedArray) {
        int headerColor = typedArray.getColor(R.styleable.CalendarView_headerColor, 0);
        mCalendarProperties.setHeaderColor(headerColor);

        int headerLabelColor = typedArray.getColor(R.styleable.CalendarView_headerLabelColor, 0);
        mCalendarProperties.setHeaderLabelColor(headerLabelColor);

        int abbreviationsBarColor = typedArray.getColor(R.styleable.CalendarView_abbreviationsBarColor, 0);
        mCalendarProperties.setAbbreviationsBarColor(abbreviationsBarColor);

        int abbreviationsLabelsColor = typedArray.getColor(R.styleable.CalendarView_abbreviationsLabelsColor, 0);
        mCalendarProperties.setAbbreviationsLabelsColor(abbreviationsLabelsColor);

        int pagesColor = typedArray.getColor(R.styleable.CalendarView_pagesColor, 0);
        mCalendarProperties.setPagesColor(pagesColor);

        int daysLabelsColor = typedArray.getColor(R.styleable.CalendarView_daysLabelsColor, 0);
        mCalendarProperties.setDaysLabelsColor(daysLabelsColor);

        int anotherMonthsDaysLabelsColor = typedArray.getColor(R.styleable.CalendarView_anotherMonthsDaysLabelsColor, 0);
        mCalendarProperties.setAnotherMonthsDaysLabelsColor(anotherMonthsDaysLabelsColor);

        int todayLabelColor = typedArray.getColor(R.styleable.CalendarView_todayLabelColor, 0);
        mCalendarProperties.setTodayLabelColor(todayLabelColor);

        int selectionColor = typedArray.getColor(R.styleable.CalendarView_selectionColor, 0);
        mCalendarProperties.setSelectionColor(selectionColor);

        int selectionLabelColor = typedArray.getColor(R.styleable.CalendarView_selectionLabelColor, 0);
        mCalendarProperties.setSelectionLabelColor(selectionLabelColor);

        int disabledDaysLabelsColor = typedArray.getColor(R.styleable.CalendarView_disabledDaysLabelsColor, 0);
        mCalendarProperties.setDisabledDaysLabelsColor(disabledDaysLabelsColor);

        int highlightedDaysLabelsColor = typedArray.getColor(R.styleable.CalendarView_highlightedDaysLabelsColor, 0);
        mCalendarProperties.setHighlightedDaysLabelsColor(highlightedDaysLabelsColor);

        int calendarType = typedArray.getInt(R.styleable.CalendarView_type, CLASSIC);
        mCalendarProperties.setCalendarType(calendarType);

        int maximumDaysRange = typedArray.getInt(R.styleable.CalendarView_maximumDaysRange, 0);
        mCalendarProperties.setMaximumDaysRange(maximumDaysRange);

        // Set picker mode !DEPRECATED!
        if (typedArray.getBoolean(R.styleable.CalendarView_datePicker, false)) {
            mCalendarProperties.setCalendarType(ONE_DAY_PICKER);
        }

        boolean eventsEnabled = typedArray.getBoolean(R.styleable.CalendarView_eventsEnabled,
                mCalendarProperties.getCalendarType() == CLASSIC);
        mCalendarProperties.setEventsEnabled(eventsEnabled);

        boolean swipeEnabled = typedArray.getBoolean(R.styleable.CalendarView_swipeEnabled, true);
        mCalendarProperties.setSwipeEnabled(swipeEnabled);

        Drawable previousButtonSrc = typedArray.getDrawable(R.styleable.CalendarView_previousButtonSrc);
        mCalendarProperties.setPreviousButtonSrc(previousButtonSrc);

        Drawable forwardButtonSrc = typedArray.getDrawable(R.styleable.CalendarView_forwardButtonSrc);
        mCalendarProperties.setForwardButtonSrc(forwardButtonSrc);
    }

    private void initAttributes() {
        AppearanceUtils.setHeaderColor(getRootView(), mCalendarProperties.getHeaderColor());

        AppearanceUtils.setHeaderVisibility(getRootView(), mCalendarProperties.getHeaderVisibility());

        AppearanceUtils.setAbbreviationsBarVisibility(getRootView(), mCalendarProperties.getAbbreviationsBarVisibility());

        AppearanceUtils.setNavigationVisibility(getRootView(), mCalendarProperties.getNavigationVisibility());

        AppearanceUtils.setHeaderLabelColor(getRootView(), mCalendarProperties.getHeaderLabelColor());

        AppearanceUtils.setAbbreviationsBarColor(getRootView(), mCalendarProperties.getAbbreviationsBarColor());

        AppearanceUtils.setAbbreviationsLabels(getRootView(), mCalendarProperties.getAbbreviationsLabelsColor(),
                mCalendarProperties.getFirstPageCalendarDate().getFirstDayOfWeek());

        AppearanceUtils.setPagesColor(getRootView(), mCalendarProperties.getPagesColor());

        AppearanceUtils.setPreviousButtonImage(getRootView(), mCalendarProperties.getPreviousButtonSrc());

        AppearanceUtils.setForwardButtonImage(getRootView(), mCalendarProperties.getForwardButtonSrc());

        mViewPager.setSwipeEnabled(mCalendarProperties.getSwipeEnabled());

        // Sets layout for date picker or normal calendar
        setCalendarRowLayout();
    }

    public void setHeaderColor(@ColorRes int color) {
        mCalendarProperties.setHeaderColor(color);
        AppearanceUtils.setHeaderColor(getRootView(), mCalendarProperties.getHeaderColor());
    }

    public void setHeaderVisibility(int visibility) {
        mCalendarProperties.setHeaderVisibility(visibility);
        AppearanceUtils.setHeaderVisibility(getRootView(), mCalendarProperties.getHeaderVisibility());
    }

    public void setAbbreviationsBarVisibility(int visibility) {
        mCalendarProperties.setAbbreviationsBarVisibility(visibility);
        AppearanceUtils.setAbbreviationsBarVisibility(getRootView(), mCalendarProperties.getAbbreviationsBarVisibility());
    }

    public void setHeaderLabelColor(@ColorRes int color) {
        mCalendarProperties.setHeaderLabelColor(color);
        AppearanceUtils.setHeaderLabelColor(getRootView(), mCalendarProperties.getHeaderLabelColor());
    }

    public void setPreviousButtonImage(Drawable drawable) {
        mCalendarProperties.setPreviousButtonSrc(drawable);
        AppearanceUtils.setPreviousButtonImage(getRootView(), mCalendarProperties.getPreviousButtonSrc());
    }

    public void setForwardButtonImage(Drawable drawable) {
        mCalendarProperties.setForwardButtonSrc(drawable);
        AppearanceUtils.setForwardButtonImage(getRootView(), mCalendarProperties.getForwardButtonSrc());
    }

    private void setCalendarRowLayout() {
        if (mCalendarProperties.getEventsEnabled()) {
            mCalendarProperties.setItemLayoutResource(R.layout.calendar_view_day);
        } else {
            mCalendarProperties.setItemLayoutResource(R.layout.calendar_view_picker_day);
        }
    }

    private void initUiElements() {
        mForwardButton = (ImageButton) findViewById(R.id.forwardButton);
        mForwardButton.setOnClickListener(onNextClickListener);

        mPreviousButton = (ImageButton) findViewById(R.id.previousButton);
        mPreviousButton.setOnClickListener(onPreviousClickListener);

        mCurrentMonthLabel = (TextView) findViewById(R.id.currentDateLabel);

        mViewPager = (CalendarViewPager) findViewById(R.id.calendarViewPager);
    }

    private void initCalendar() {
        mCalendarPageAdapter = new CalendarPageAdapter(mContext, mCalendarProperties);

        mViewPager.setAdapter(mCalendarPageAdapter);
        mViewPager.addOnPageChangeListener(onPageChangeListener);

        setUpCalendarPosition(Calendar.getInstance());
    }

    private void setUpCalendarPosition(Calendar calendar) {
        DateUtils.setMidnight(calendar);

        if (mCalendarProperties.getCalendarType() == CalendarView.ONE_DAY_PICKER) {
            mCalendarProperties.setSelectedDay(calendar);
        }

        mCalendarProperties.getFirstPageCalendarDate().setTime(calendar.getTime());
        mCalendarProperties.getFirstPageCalendarDate().add(Calendar.MONTH, -FIRST_VISIBLE_PAGE);

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
            Calendar calendar = (Calendar) mCalendarProperties.getFirstPageCalendarDate().clone();
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
            throw new OutOfDateRangeException(ErrorsMessages.OUT_OF_RANGE_MIN);
        }

        if (mCalendarProperties.getMaximumDate() != null && date.after(mCalendarProperties.getMaximumDate())) {
            throw new OutOfDateRangeException(ErrorsMessages.OUT_OF_RANGE_MAX);
        }

        setUpCalendarPosition(date);

        mCurrentMonthLabel.setText(DateUtils.getMonthAndYearDate(mContext, date));
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
        if (mCalendarProperties.getEventsEnabled()) {
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

    public void setSelectedDates(List<Calendar> selectedDates) {
        mCalendarProperties.setSelectedDays(selectedDates);
        mCalendarPageAdapter.notifyDataSetChanged();
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
        Calendar calendar = (Calendar) mCalendarProperties.getFirstPageCalendarDate().clone();
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

    public void setHighlightedDays(List<Calendar> highlightedDays) {
        mCalendarProperties.setHighlightedDays(highlightedDays);
    }

    public void setSwipeEnabled(boolean swipeEnabled) {
        mCalendarProperties.setSwipeEnabled(swipeEnabled);
        mViewPager.setSwipeEnabled(mCalendarProperties.getSwipeEnabled());
    }
}
