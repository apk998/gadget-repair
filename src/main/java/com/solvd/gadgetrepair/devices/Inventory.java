package com.solvd.gadgetrepair.devices;

import com.solvd.gadgetrepair.exceptions.InventoryFullException;
import com.solvd.gadgetrepair.exceptions.NotEnoughPartsException;
import com.solvd.gadgetrepair.exceptions.UnsupportedPartException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Stores the stock of spare parts and tools for repair
public class Inventory {
    private final Set<String> parts;   // rewrite to account for enums?
    private final Map<String, Integer> quantities;
    public static final int MAX_CAPACITY = 5000;
    public static final int DEFAULT_PART_QUANTITY = 10;

    public Inventory(int capacity) {
        parts = new HashSet<>();
        quantities = new HashMap<>();
    }

    public boolean isInventoryFull() {
        return parts.size() >= MAX_CAPACITY;
    }

    public void addPart(String part, int quantity) throws InventoryFullException {
        if (isInventoryFull()) {
            throw new InventoryFullException("Inventory is full. Cannot add part(s).");
        }

        parts.add(part);
        quantities.put(part, quantity);
    }

    public void removePart(String part, int quantity) throws NotEnoughPartsException, UnsupportedPartException {
        if (parts.contains(part)) {
            int currentQuantity = quantities.get(part);

            if (currentQuantity < quantity) {
                throw new NotEnoughPartsException("Not enough parts in stock.");
            }

            currentQuantity -= quantity;
            quantities.put(part, currentQuantity);

            if (currentQuantity == 0) {
                parts.remove(part);
            }
        } else {
            throw new UnsupportedPartException("Part not recognized or supported.");
        }
    }

    public int getQuantity(String part) {
        return quantities.getOrDefault(part, -1);   // -1 if the part is not found
    }

    public Set<String> getUniqueParts() {
        return parts;
    }
}
