package com.solvd.gadgetrepair.exceptions;

// When someone attempts to add a part to a full inventory
public class InventoryFullException extends RuntimeException {
    public InventoryFullException (String message) {
        super(message);
    }
}
