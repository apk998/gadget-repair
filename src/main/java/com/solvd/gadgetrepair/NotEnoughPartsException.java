package com.solvd.gadgetrepair.exceptions;

// When the inventory does not have enough requested parts in stock
public class NotEnoughPartsException extends Exception {
    public NotEnoughPartsException (String message) {
        super(message);
    }
}