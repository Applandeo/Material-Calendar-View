package com.applandeo.materialcalendarview

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.viewpager.widget.ViewPager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView

import com.applandeo.materialcalendarview.adapters.CalendarPageAdapter
import com.applandeo.materialcalendarview.exceptions.ErrorsMessages
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException
import com.applandeo.materialcalendarview.extensions.CalendarViewPager
import com.applandeo.materialcalendarview.listeners.OnCalendarPageChangeListener
import com.applandeo.materialcalendarview.listeners.OnDayClickListener
import com.applandeo.materialcalendarview.utils.AppearanceUtils
import com.applandeo.materialcalendarview.utils.CalendarProperties
import com.applandeo.materialcalendarview.utils.CalendarProperties.Companion.FIRST_VISIBLE_PAGE
import com.applandeo.materialcalendarview.utils.DateUtils

import java.util.Calendar
import java.util.Date

/**
 * This class represents a view, displays to user as calendar. It allows to work in date picker
 * mode or like a normal calendar. In a normal calendar mode it can displays an image under the day
 * number. In both modes it marks today day. It also provides click on day events using
 * OnDayClickListener which returns an EventDay object.
 *
 * @see EventDay
 *
 * @see OnDayClickListener
 *
 *
 *
 *
 * XML attributes:
 * - Set calendar type: type="classic or one_day_picker or many_days_picker or range_picker"
 * - Set calendar header color: headerColor="@color/[color]"
 * - Set calendar header label color: headerLabelColor="@color/[color]"
 * - Set previous button resource: previousButtonSrc="@drawable/[drawable]"
 * - Ser forward button resource: forwardButtonSrc="@drawable/[drawable]"
 * - Set today label color: todayLabelColor="@color/[color]"
 * - Set selection color: selectionColor="@color/[color]"
 *
 *
 * Created by Mateusz Kornakiewicz on 23.05.2017.
 */

class CalendarView : LinearLayout {

    private var currentMonthLabel: TextView? = null
    private var currentPage: Int = 0
    private var viewPager: CalendarViewPager? = null

    private var calendarProperties: CalendarProperties = CalendarProperties(context)
    private var calendarPageAdapter: CalendarPageAdapter = CalendarPageAdapter(context, calendarProperties)

    private val onPageChangeListener = object : ViewPager.OnPageChangeListener {
        /**
         * This method set calendar header label
         *
         * @param position Current ViewPager position
         * @see ViewPager.OnPageChangeListener
         */
        override fun onPageSelected(position: Int) {
            val calendar = calendarProperties.firstPageCalendarDate?.clone() as Calendar
            calendar.add(Calendar.MONTH, position)

            if (!isScrollingLimited(calendar, position)) {
                setHeaderName(calendar, position)
            }
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
        override fun onPageScrollStateChanged(state: Int) {}
    }

    /**
     * @return List of Calendar object representing a selected dates
     */
    var selectedDates: List<Calendar>
        get() = calendarPageAdapter.selectedDays
                .mapNotNull { it.calendar }
        set(selectedDates) {
            calendarProperties.selectDays(selectedDates)
            calendarPageAdapter.notifyDataSetChanged()
        }

    /**
     * @return Calendar object representing a selected date
     */
    val selectedDate: Calendar?
        get() = calendarPageAdapter.selectedDays.map { it.calendar }.first()

    /**
     * @return Calendar object representing a date of current calendar page
     */
    private val currentPageDate: Calendar
        get() {
            val calendar = calendarProperties.firstPageCalendarDate?.clone() as Calendar
            return calendar.apply {
                set(Calendar.DAY_OF_MONTH, 1)
                viewPager?.currentItem?.let { add(Calendar.MONTH, it) }
            }
        }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initControl(context, attrs)
        initCalendar()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initControl(context, attrs)
        initCalendar()
    }

    constructor(context: Context, properties: CalendarProperties) : super(context) {
        this.calendarProperties = properties

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.calendar_view, this)

        initUiElements()
        initAttributes()
        initCalendar()
    }

    private fun initControl(context: Context, attrs: AttributeSet) {
        calendarProperties = CalendarProperties(context)

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.calendar_view, this)

        initUiElements()
        setAttributes(attrs)
    }

    /**
     * This method set xml values for calendar elements
     *
     * @param attrs A set of xml attributes
     */
    private fun setAttributes(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CalendarView)

        try {
            initCalendarProperties(typedArray)
            initAttributes()
        } finally {
            typedArray.recycle()
        }
    }

    private fun initCalendarProperties(typedArray: TypedArray) {
        with(calendarProperties) {
            headerColor = typedArray.getColor(R.styleable.CalendarView_headerColor, 0)
            headerLabelColor = typedArray.getColor(R.styleable.CalendarView_headerLabelColor, 0)
            abbreviationsBarColor = typedArray.getColor(R.styleable.CalendarView_abbreviationsBarColor, 0)
            abbreviationsLabelsColor = typedArray.getColor(R.styleable.CalendarView_abbreviationsLabelsColor, 0)
            pagesColor = typedArray.getColor(R.styleable.CalendarView_pagesColor, 0)
            daysLabelsColor = typedArray.getColor(R.styleable.CalendarView_daysLabelsColor, 0)
            anotherMonthsDaysLabelsColor = typedArray.getColor(R.styleable.CalendarView_anotherMonthsDaysLabelsColor, 0)
            todayLabelColor = typedArray.getColor(R.styleable.CalendarView_todayLabelColor, 0)
            selectionColor = typedArray.getColor(R.styleable.CalendarView_selectionColor, 0)
            selectionLabelColor = typedArray.getColor(R.styleable.CalendarView_selectionLabelColor, 0)
            disabledDaysLabelsColor = typedArray.getColor(R.styleable.CalendarView_disabledDaysLabelsColor, 0)
            highlightedDaysLabelsColor = typedArray.getColor(R.styleable.CalendarView_highlightedDaysLabelsColor, 0)
            calendarType = typedArray.getInt(R.styleable.CalendarView_type, CLASSIC)
            // Set picker mode !DEPRECATED!
            if (typedArray.getBoolean(R.styleable.CalendarView_datePicker, false)) {
                calendarType = ONE_DAY_PICKER
            }

            eventsEnabled = typedArray.getBoolean(R.styleable.CalendarView_eventsEnabled,
               calendarProperties.calendarType == CLASSIC)
            swipeEnabled = typedArray.getBoolean(R.styleable.CalendarView_swipeEnabled, true)
            previousButtonSrc = typedArray.getDrawable(R.styleable.CalendarView_previousButtonSrc)
            forwardButtonSrc = typedArray.getDrawable(R.styleable.CalendarView_forwardButtonSrc)
        }

    }

    private fun initAttributes() {
        AppearanceUtils.setHeaderColor(rootView, calendarProperties.headerColor)
        AppearanceUtils.setHeaderVisibility(rootView, calendarProperties.headerVisibility)
        AppearanceUtils.setAbbreviationsBarVisibility(rootView, calendarProperties.abbreviationsBarVisibility)
        AppearanceUtils.setHeaderLabelColor(rootView, calendarProperties.headerLabelColor)
        AppearanceUtils.setAbbreviationsBarColor(rootView, calendarProperties.abbreviationsBarColor)
        AppearanceUtils.setPagesColor(rootView, calendarProperties.pagesColor)
        AppearanceUtils.setPreviousButtonImage(rootView, calendarProperties.previousButtonSrc)
        AppearanceUtils.setForwardButtonImage(rootView, calendarProperties.forwardButtonSrc)
        viewPager?.setSwipeEnabled(calendarProperties.swipeEnabled)

        calendarProperties.firstPageCalendarDate?.firstDayOfWeek?.let {
            AppearanceUtils.setAbbreviationsLabels(rootView, calendarProperties.abbreviationsLabelsColor, it)
        }

        // Sets layout for date picker or normal calendar
        setCalendarRowLayout()
    }

    fun setHeaderColor(@ColorRes color: Int) {
        calendarProperties.headerColor = color
        AppearanceUtils.setHeaderColor(rootView, calendarProperties.headerColor)
    }

    fun setHeaderVisibility(visibility: Int) {
        calendarProperties.headerVisibility = visibility
        AppearanceUtils.setHeaderVisibility(rootView, calendarProperties.headerVisibility)
    }

    fun setAbbreviationsBarVisibility(visibility: Int) {
        calendarProperties.abbreviationsBarVisibility = visibility
        AppearanceUtils.setAbbreviationsBarVisibility(rootView, calendarProperties.abbreviationsBarVisibility)
    }

    fun setHeaderLabelColor(@ColorRes color: Int) {
        calendarProperties.headerLabelColor = color
        AppearanceUtils.setHeaderLabelColor(rootView, calendarProperties.headerLabelColor)
    }

    fun setPreviousButtonImage(drawable: Drawable) {
        calendarProperties.previousButtonSrc = drawable
        AppearanceUtils.setPreviousButtonImage(rootView, calendarProperties.previousButtonSrc)
    }

    fun setForwardButtonImage(drawable: Drawable) {
        calendarProperties.forwardButtonSrc = drawable
        AppearanceUtils.setForwardButtonImage(rootView, calendarProperties.forwardButtonSrc)
    }

    private fun setCalendarRowLayout() {
        if (calendarProperties.eventsEnabled) {
            calendarProperties.itemLayoutResource = R.layout.calendar_view_day
        } else {
            calendarProperties.itemLayoutResource = R.layout.calendar_view_picker_day
        }
    }

    private fun initUiElements() {
        val forwardButton = findViewById<ImageButton>(R.id.forwardButton)
        forwardButton.setOnClickListener {
            viewPager?.currentItem = viewPager?.currentItem?.plus(1) ?: return@setOnClickListener
        }

        val previousButton = findViewById<ImageButton>(R.id.previousButton)
        previousButton.setOnClickListener {
            viewPager?.currentItem = viewPager?.currentItem?.minus(1) ?: return@setOnClickListener
        }

        currentMonthLabel = findViewById(R.id.currentDateLabel)
        viewPager = findViewById(R.id.calendarViewPager)
    }

    private fun initCalendar() {
        calendarPageAdapter = CalendarPageAdapter(context, calendarProperties)

        viewPager?.adapter = calendarPageAdapter
        viewPager?.addOnPageChangeListener(onPageChangeListener)

        setUpCalendarPosition(Calendar.getInstance())
    }

    private fun setUpCalendarPosition(calendar: Calendar) {
        DateUtils.setMidnight(calendar)

        if (calendarProperties.calendarType == ONE_DAY_PICKER) {
            calendarProperties.setSelectedDay(calendar)
        }

        calendarProperties.firstPageCalendarDate?.time = calendar.time
        calendarProperties.firstPageCalendarDate?.add(Calendar.MONTH, -FIRST_VISIBLE_PAGE)

        viewPager?.currentItem = FIRST_VISIBLE_PAGE
    }

    fun setOnPreviousPageChangeListener(listener: OnCalendarPageChangeListener) {
        calendarProperties.onPreviousPageChangeListener = listener
    }

    fun setOnForwardPageChangeListener(listener: OnCalendarPageChangeListener) {
        calendarProperties.onForwardPageChangeListener = listener
    }

    private fun isScrollingLimited(calendar: Calendar, position: Int): Boolean {
        if (DateUtils.isMonthBefore(calendarProperties.minimumDate, calendar)) {
            viewPager?.currentItem = position + 1
            return true
        }

        if (DateUtils.isMonthAfter(calendarProperties.maximumDate, calendar)) {
            viewPager?.currentItem = position - 1
            return true
        }

        return false
    }

    private fun setHeaderName(calendar: Calendar, position: Int) {
        currentMonthLabel?.text = DateUtils.getMonthAndYearDate(context, calendar)
        callOnPageChangeListeners(position)
    }

    // This method calls page change listeners after swipe calendar or click arrow buttons
    private fun callOnPageChangeListeners(position: Int) {
        if (position > currentPage && calendarProperties.onForwardPageChangeListener != null) {
            calendarProperties.onForwardPageChangeListener?.onChange()
        }

        if (position < currentPage && calendarProperties.onPreviousPageChangeListener != null) {
            calendarProperties.onPreviousPageChangeListener?.onChange()
        }

        currentPage = position
    }

    /**
     * @param onDayClickListener OnDayClickListener interface responsible for handle clicks on calendar cells
     * @see OnDayClickListener
     */
    fun setOnDayClickListener(onDayClickListener: OnDayClickListener) {
        calendarProperties.onDayClickListener = onDayClickListener
    }

    /**
     * This method set a current and selected date of the calendar using Calendar object.
     *
     * @param date A Calendar object representing a date to which the calendar will be set
     */
    @Throws(OutOfDateRangeException::class)
    fun setDate(date: Calendar) {
        if (calendarProperties.minimumDate != null && date.before(calendarProperties.minimumDate)) {
            throw OutOfDateRangeException(ErrorsMessages.OUT_OF_RANGE_MIN)
        }

        if (calendarProperties.maximumDate != null && date.after(calendarProperties.maximumDate)) {
            throw OutOfDateRangeException(ErrorsMessages.OUT_OF_RANGE_MAX)
        }

        setUpCalendarPosition(date)

        currentMonthLabel?.text = DateUtils.getMonthAndYearDate(context, date)
        calendarPageAdapter.notifyDataSetChanged()
    }

    /**
     * This method set a current and selected date of the calendar using Date object.
     *
     * @param currentDate A date to which the calendar will be set
     */
    @Throws(OutOfDateRangeException::class)
    fun setDate(currentDate: Date) {
        val calendar = Calendar.getInstance()
        calendar.time = currentDate

        setDate(calendar)
    }

    /**
     * This method is used to set a list of events displayed in calendar cells,
     * visible as images under the day number.
     *
     * @param eventDays List of EventDay objects
     * @see EventDay
     */
    fun setEvents(eventDays: List<EventDay>) {
        if (calendarProperties.eventsEnabled) {
            calendarProperties.eventDays = eventDays
            calendarPageAdapter.notifyDataSetChanged()
        }
    }

    /**
     * This method set a minimum available date in calendar
     *
     * @param calendar Calendar object representing a minimum date
     */
    fun setMinimumDate(calendar: Calendar) {
        calendarProperties.minimumDate = calendar
    }

    /**
     * This method set a maximum available date in calendar
     *
     * @param calendar Calendar object representing a maximum date
     */
    fun setMaximumDate(calendar: Calendar) {
        calendarProperties.maximumDate = calendar
    }

    /**
     * This method is used to return to current month page
     */
    fun showCurrentMonthPage() {
        viewPager?.let {
            it.setCurrentItem(it.currentItem - DateUtils.getMonthsBetweenDates(DateUtils.calendar, currentPageDate), true)
        }
    }

    fun setDisabledDays(disabledDays: List<Calendar>) {
        calendarProperties.disabledDays = disabledDays
    }

    fun setHighlightedDays(highlightedDays: List<Calendar>) {
        calendarProperties.highlightedDays = highlightedDays
    }

    fun setSwipeEnabled(swipeEnabled: Boolean) {
        calendarProperties.swipeEnabled = swipeEnabled
        viewPager?.setSwipeEnabled(calendarProperties.swipeEnabled)
    }

    companion object {
        const val CLASSIC = 0
        const val ONE_DAY_PICKER = 1
        const val MANY_DAYS_PICKER = 2
        const val RANGE_PICKER = 3
    }
}
