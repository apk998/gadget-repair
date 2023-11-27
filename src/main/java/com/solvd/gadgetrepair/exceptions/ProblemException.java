package com.solvd.gadgetrepair.exceptions;

// When the service cannot fix the device
public class ProblemException extends RuntimeException {
    public ProblemException (String message) {
        super(message);
    }
}