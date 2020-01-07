package com.applandeo.materialcalendarview.extensions

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.core.view.children
import androidx.viewpager.widget.ViewPager

/**
 * Created by Applandeo Team.
 */

typealias OnCalendarPageChangedListener = (Int) -> Unit

class CalendarViewPager @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null
) : ViewPager(context, attrs) {

    var swipeEnabled = true
    private var onCalendarPageChangedListener: OnCalendarPageChangedListener? = null

    init {
        addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(position: Int, offset: Float, offsetPixels: Int) = Unit
            override fun onPageScrollStateChanged(state: Int) = Unit

            override fun onPageSelected(position: Int) {
                onCalendarPageChangedListener?.invoke(position)
            }
        })
    }

    //This method is needed to get wrap_content height for ViewPager
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var heightMeasure = heightMeasureSpec
        var height = 0

        children.forEach { child ->
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED))
            val measuredHeight = child.measuredHeight
            if (measuredHeight > height) {
                height = measuredHeight
            }
        }

        if (height != 0) {
            heightMeasure = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)
        }

        super.onMeasure(widthMeasureSpec, heightMeasure)
    }

    override fun onTouchEvent(event: MotionEvent) = swipeEnabled && super.onTouchEvent(event)

    override fun onInterceptTouchEvent(event: MotionEvent) = swipeEnabled && super.onInterceptTouchEvent(event)

    fun onCalendarPageChangedListener(listener: OnCalendarPageChangedListener) {
        onCalendarPageChangedListener = listener
    }
}
