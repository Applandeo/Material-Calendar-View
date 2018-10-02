package com.applandeo.materialcalendarview.exceptions;

/**
 * Created by Mateusz Kornakiewicz on 09.08.2018.
 */

public final class ErrorsMessages {

    public final static String ONE_DAY_PICKER_MULTIPLE_SELECTION = "ONE_DAY_PICKER DOES NOT SUPPORT MULTIPLE DAYS, USE setDate() METHOD INSTEAD";
    public final static String OUT_OF_RANGE_MIN = "SET DATE EXCEEDS THE MINIMUM DATE";
    public final static String OUT_OF_RANGE_MAX = "SET DATE EXCEEDS THE MAXIMUM DATE";
    public final static String RANGE_PICKER_NOT_RANGE = "RANGE_PICKER ACCEPTS ONLY CONTINUOUS LIST OF CALENDARS";

    private ErrorsMessages() {
    }
}
