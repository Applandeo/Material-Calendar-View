package com.applandeo.materialcalendarview.exceptions;

/**
 * Created by Mateusz Kornakiewicz on 27.10.2017.
 */

public class OutOfDateRangeException extends RuntimeException {
    public OutOfDateRangeException(String message) {
        super(message);
    }
}
