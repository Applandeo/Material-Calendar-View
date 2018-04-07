package com.applandeo.materialcalendarsampleapp;

import android.support.annotation.ColorInt;

/**
 * Created by shuwnyuan on 07/04/2018.
 */

public class Note {
    private String note;
    private @ColorInt int color;

    public Note (String note, @ColorInt int color) {
        this.note = note;
        this.color = color;
    }

    public String getNote() {
        return this.note;
    }

    public int getColor() {
        return this.color;
    }
}
