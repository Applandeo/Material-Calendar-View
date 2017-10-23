package com.applandeo.materialcalendarview.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.applandeo.materialcalendarview.R;

import java.util.Calendar;

/**
 * This class is used to set a style of calendar cells.
 * <p>
 * Created by Mateusz Kornakiewicz on 24.05.2017.
 */

public class DayColorsUtils {

    /**
     * This is general method which sets a color of the text, font type and a background of a TextView object.
     * It is used to set day cell (numbers) style.
     *
     * @param textView   TextView containing a day number
     * @param textColor  A resource of a color of the day number
     * @param typeface   A type of text style, can be set as NORMAL or BOLD
     * @param background A resource of a background drawable
     */
    public static void setDayColors(TextView textView, int textColor, int typeface, int background) {
        if(textView == null){
            return;
        }

        textView.setTypeface(null, typeface);
        textView.setTextColor(textColor);
        textView.setBackgroundResource(background);
    }

    /**
     * This method sets a color of the text, font type and a background of a TextView object.
     * It is used to set day cell (numbers) style in the case of selected day (when calendar is in
     * the picker mode). It also colors a background of the selection.
     *
     * @param context        An application context necessary to get color from resources
     * @param dayLabel       TextView containing a day number
     * @param selectionColor A resource of a selection background color
     */
    public static void setSelectedDayColors(Context context, TextView dayLabel, int selectionColor) {
        setDayColors(dayLabel, ContextCompat.getColor(context, android.R.color.white), Typeface.NORMAL,
                R.drawable.background_color_circle_selector);

        dayLabel.getBackground().setColorFilter(selectionColor, android.graphics.PorterDuff.Mode.MULTIPLY);
    }

    /**
     * This method is used to set a color of texts, font types and backgrounds of TextView objects
     * in a current visible month. Visible day labels from previous and forward months are set using
     * setDayColors() method. It also checks if a day number is a day number of today and set it
     * a different color and bold face type.
     *
     * @param context         An application context necessary to get color from resources
     * @param day             A calendar instance representing day date
     * @param today           A calendar instance representing today date
     * @param dayLabel        TextView containing a day number
     * @param todayLabelColor A resource of a color used to mark today day
     */
    public static void setCurrentMonthDayColors(Context context, Calendar day, Calendar today,
                                                TextView dayLabel, int todayLabelColor) {
        if (today.equals(day)) {
            setDayColors(dayLabel, todayLabelColor, Typeface.BOLD, R.drawable.background_transparent);
        } else {
            setDayColors(dayLabel, ContextCompat.getColor(context, R.color.currentMonthDayColor), Typeface.NORMAL,
                    R.drawable.background_transparent);
        }
    }
}
