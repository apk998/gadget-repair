package com.solvd.gadgetrepair.exceptions;

// When a customer brings in an unaccepted gadget
public class GadgetException extends RuntimeException {
    public GadgetException (String message) {
        super(message);
    }
}
