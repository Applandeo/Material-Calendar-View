package com.applandeo.materialcalendarsampleapp;

import com.applandeo.materialcalendarview.EventDay;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by shuwnyuan on 04/04/2018.
 */

public class NoteEventDay extends EventDay {
    private final List<Integer> noteList = new ArrayList<>();

    public NoteEventDay(Calendar day, List<Integer> noteList) {
        super(day);
        this.noteList.addAll(noteList);
    }

    public List<Integer> getNoteList() {
        return this.noteList;
    }
}
