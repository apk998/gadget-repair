package com.solvd.gadgetrepair.exceptions;

// When an unsupported part is requested
public class UnsupportedPartException extends Exception {
    public UnsupportedPartException (String message) {
        super(message);
    }
}
