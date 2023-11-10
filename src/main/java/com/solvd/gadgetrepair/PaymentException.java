package com.solvd.gadgetrepair.exceptions;

// When the customer pays using an unaccepted method
public class PaymentException extends Exception {
    public PaymentException (String message) {
        super(message);
    }
}