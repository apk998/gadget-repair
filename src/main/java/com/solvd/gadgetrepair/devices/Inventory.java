package com.solvd.gadgetrepair.devices;

import com.solvd.gadgetrepair.exceptions.InventoryFullException;
import com.solvd.gadgetrepair.exceptions.NotEnoughPartsException;
import com.solvd.gadgetrepair.exceptions.UnsupportedPartException;

// Stores the stock of spare parts and tools for repair
public class Inventory {
    private final String[] parts;
    private final int[] quantities;
    public static final int MAX_CAPACITY = 5000;
    public static final int DEFAULT_PART_QUANTITY = 10;

    public Inventory(int capacity) {
        parts = new String[capacity];
        quantities = new int[capacity];
    }

    public boolean isInventoryFull() {
        int totalParts = 0;
        for (String part : parts) {
            if (part != null) {
                totalParts++;
            }
        }
        return totalParts >= MAX_CAPACITY;
    }

    public void addPart(String part, int quantity) throws InventoryFullException {
        if (isInventoryFull()) {
            throw new InventoryFullException("Inventory is full. Cannot add part(s).");
        }

        for (int i=0; i < parts.length; i++) {
            if (parts[i] == null) {
                parts[i] = part;
                quantities[i] = quantity;
                break;
            }
        }
    }

    public void removePart(String part, int quantity) throws NotEnoughPartsException, UnsupportedPartException {
        int partIndex = -1;
        for (int i=0; i < parts.length; i++) {
            if (parts[i] != null && parts[i].equals(part)) {
                partIndex = i;
                break;
            }
        }
        if (partIndex == -1) {
            throw new UnsupportedPartException("Part not recognized or supported.");
        }
        if (quantities[partIndex] < quantity) {
            throw new NotEnoughPartsException("Not enough parts in stock.");
        }
        quantities[partIndex] -= quantity;
        if (quantities[partIndex] == 0) {
            parts[partIndex] = null;
        }
    }

    public int getQuantity(String part) {
        for (int i = 0; i < parts.length; i++) {
            if (part.equals(parts[i])) {
                return quantities[i];
            }
        }
        return -1;   // if the part is not found
    }
}
