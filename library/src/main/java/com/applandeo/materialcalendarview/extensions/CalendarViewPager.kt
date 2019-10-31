package com.applandeo.materialcalendarview.extensions

import android.content.Context
import androidx.viewpager.widget.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

/**
 * Created by Mateusz Kornakiewicz on 21.11.2017.
 */

class CalendarViewPager : ViewPager {

    private var swipeEnabled = true

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    //This method is needed to get wrap_content height for ViewPager
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var heightMeasureSpec = heightMeasureSpec
        var height = 0

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED))

            val measuredHeight = child.measuredHeight

            if (measuredHeight > height) {
                height = measuredHeight
            }
        }

        if (height != 0) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    fun setSwipeEnabled(swipeEnabled: Boolean) {
        this.swipeEnabled = swipeEnabled
    }

    override fun onTouchEvent(event: MotionEvent)
            = swipeEnabled && super.onTouchEvent(event)

    override fun onInterceptTouchEvent(event: MotionEvent)
            = swipeEnabled && super.onInterceptTouchEvent(event)

}
