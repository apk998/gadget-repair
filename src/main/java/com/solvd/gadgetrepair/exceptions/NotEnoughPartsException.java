package com.solvd.gadgetrepair.exceptions;

// When the inventory does not have enough requested parts in stock
public class NotEnoughPartsException extends RuntimeException {
    public NotEnoughPartsException (String message) {
        super(message);
    }
}