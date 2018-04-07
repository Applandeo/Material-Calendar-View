package com.applandeo.materialcalendarsampleapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.adapters.CalendarDayImage;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.applandeo.materialcalendarview.utils.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * Created by Mateusz Kornakiewicz on 26.05.2017.
 */

public class NoteCalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_calendar_activity);

        List<EventDay> eventList = new ArrayList<>();

        // today
        Calendar day1 = Calendar.getInstance();
        DateUtils.setMidnight(day1);

        List<Note> e1 = new ArrayList<>();
        e1.add(new Note("Note 1", Color.RED));

        NoteEventDay ed1 = new NoteEventDay(day1, e1);
        eventList.add(ed1);

        // 2 days from today
        Calendar day2 = Calendar.getInstance();
        day2.add(Calendar.DAY_OF_MONTH, 2);
        DateUtils.setMidnight(day2);

        List<Note> e2 = new ArrayList<>();
        e2.add(new Note("Note 1", Color.RED));
        e2.add(new Note("Note 2", Color.BLUE));

        NoteEventDay ed2 = new NoteEventDay(day2, e2);
        eventList.add(ed2);

        // 5 days from today
        Calendar day3 = Calendar.getInstance();
        DateUtils.setMidnight(day3);
        day3.add(Calendar.DAY_OF_MONTH, 5);

        List<Note> e3 = new ArrayList<>();
        e3.add(new Note("Note 1", Color.RED));
        e3.add(new Note("Note 2", Color.YELLOW));
        e3.add(new Note("Note 3", Color.BLUE));

        NoteEventDay ed3 = new NoteEventDay(day3, e3);
        eventList.add(ed3);

        // 10 days from today
        Calendar day4 = Calendar.getInstance();
        DateUtils.setMidnight(day4);
        day4.add(Calendar.DAY_OF_MONTH, 10);

        List<Note> e4 = new ArrayList<>();
        e4.add(new Note("Note 1", Color.RED));
        e4.add(new Note("Note 2", Color.YELLOW));
        e4.add(new Note("Note 3", Color.BLUE));
        e4.add(new Note("Note 4", Color.GREEN));
        e4.add(new Note("Note 5", Color.GRAY));
        e4.add(new Note("Note 6", Color.BLACK));

        NoteEventDay ed4 = new NoteEventDay(day4, e4);
        eventList.add(ed4);

        CalendarView calendarView = findViewById(R.id.calendarView);

        Calendar min = Calendar.getInstance();
        min.add(Calendar.MONTH, -2);

        Calendar max = Calendar.getInstance();
        max.add(Calendar.MONTH, 2);

        calendarView.setMinimumDate(min);
        calendarView.setMaximumDate(max);

        calendarView.setEvents(eventList);

        calendarView.setDisabledDays(getDisabledDays());

        calendarView.setOnDayClickListener(eventDay ->
                Toast.makeText(getApplicationContext(),
                        eventDay.getCalendar().getTime().toString() + " "
                                + eventDay.isEnabled(),
                        Toast.LENGTH_SHORT).show());

        calendarView.setCalendarDayImage((layout, day, setTransparent) -> {
            if (day instanceof NoteEventDay) {
                NoteEventDay multipleEventDay = (NoteEventDay) day;
                List<Note> noteList = multipleEventDay.getNoteList();

                final Context context = layout.getContext();
                View view = LayoutInflater.from(context).inflate(R.layout.note_images, null, false);

                final List<ImageView> imageViews = new ArrayList<>();
                imageViews.add(view.findViewById(R.id.image_view_1));
                imageViews.add(view.findViewById(R.id.image_view_2));
                imageViews.add(view.findViewById(R.id.image_view_3));
                imageViews.add(view.findViewById(R.id.image_view_4));
                imageViews.add(view.findViewById(R.id.image_view_5));
                imageViews.add(view.findViewById(R.id.image_view_6));

                for (int i = 0; i < noteList.size(); i++) {
                    if (i >= 6) {
                        break;
                    }

                    @ColorInt int color = noteList.get(i).getColor();
                    ImageView imageView = imageViews.get(i);

                    Drawable d = context.getResources().getDrawable(R.drawable.rectangle);
                    ((GradientDrawable) d.mutate()).setColor(color);

                    imageView.setImageDrawable(d);

                    if (setTransparent) {
                        imageView.setAlpha(0.12f);
                    }
                }

                layout.addView(view);
            }
        });

        Button setDateButton = (Button) findViewById(R.id.setDateButton);
        setDateButton.setOnClickListener(v -> {
            try {
                calendarView.setDate(getRandomCalendar());
            } catch (OutOfDateRangeException exception) {
                exception.printStackTrace();

                Toast.makeText(getApplicationContext(),
                        "Date is out of range",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private List<Calendar> getDisabledDays() {
        Calendar firstDisabled = DateUtils.getCalendar();
        firstDisabled.add(Calendar.DAY_OF_MONTH, 2);

        Calendar secondDisabled = DateUtils.getCalendar();
        secondDisabled.add(Calendar.DAY_OF_MONTH, 1);

        Calendar thirdDisabled = DateUtils.getCalendar();
        thirdDisabled.add(Calendar.DAY_OF_MONTH, 18);

        List<Calendar> calendars = new ArrayList<>();
        calendars.add(firstDisabled);
        calendars.add(secondDisabled);
        calendars.add(thirdDisabled);
        return calendars;
    }

    private Calendar getRandomCalendar() {
        Random random = new Random();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, random.nextInt(99));

        return calendar;
    }
}
