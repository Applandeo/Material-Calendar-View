package com.applandeo.materialcalendarview.utils;

import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.applandeo.materialcalendarview.R;

/**
 * Created by Mateusz Kornakiewicz on 04.01.2018.
 */

public final class AppearanceUtils {

    public static void setAbbreviationsLabelsColor(View view, int color) {
        if (color == 0) {
            return;
        }

        ((TextView) view.findViewById(R.id.mondayLabel)).setTextColor(color);
        ((TextView) view.findViewById(R.id.tuesdayLabel)).setTextColor(color);
        ((TextView) view.findViewById(R.id.wednesdayLabel)).setTextColor(color);
        ((TextView) view.findViewById(R.id.thursdayLabel)).setTextColor(color);
        ((TextView) view.findViewById(R.id.fridayLabel)).setTextColor(color);
        ((TextView) view.findViewById(R.id.saturdayLabel)).setTextColor(color);
        ((TextView) view.findViewById(R.id.sundayLabel)).setTextColor(color);
    }

    public static void setHeaderColor(View view, int color) {
        if (color == 0) {
            return;
        }

        ConstraintLayout mCalendarHeader = (ConstraintLayout) view.findViewById(R.id.calendarHeader);
        mCalendarHeader.setBackgroundColor(color);
    }

    public static void setHeaderLabelColor(View view, int color) {
        if (color == 0) {
            return;
        }

        ((TextView) view.findViewById(R.id.currentDateLabel)).setTextColor(color);
    }

    public static void setAbbreviationsBarColor(View view, int color) {
        if (color == 0) {
            return;
        }

        view.findViewById(R.id.abbreviationsBar).setBackgroundColor(color);
    }

    public static void setPagesColor(View view, int color) {
        if (color == 0) {
            return;
        }

        view.findViewById(R.id.calendarViewPager).setBackgroundColor(color);
    }

    private AppearanceUtils() {
    }

    public static void setPreviousButtonImage(View view, Drawable drawable) {
        if (drawable == null) {
            return;
        }

        ((ImageButton) view.findViewById(R.id.previousButton)).setImageDrawable(drawable);
    }

    public static void setForwardButtonImage(View view, Drawable drawable) {
        if (drawable == null) {
            return;
        }

        ((ImageButton) view.findViewById(R.id.forwardButton)).setImageDrawable(drawable);
    }
}
