package com.applandeo.materialcalendarview.extensions;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by Mateusz Kornakiewicz on 15.11.2017.
 */

public class CalendarGridView extends GridView {

    public CalendarGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CalendarGridView(Context context) {
        super(context);
    }

    public CalendarGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    //This method is needed to get wrap_content height for GridView
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
