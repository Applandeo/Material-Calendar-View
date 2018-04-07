package com.applandeo.materialcalendarsampleapp;

import com.applandeo.materialcalendarview.EventDay;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by shuwnyuan on 04/04/2018.
 */

public class NoteEventDay extends EventDay {
    private final List<Note> noteList = new ArrayList<>();

    public NoteEventDay(Calendar day, List<Note> noteList) {
        super(day);
        this.noteList.addAll(noteList);
    }

    public List<Note> getNoteList() {
        return this.noteList;
    }
}
