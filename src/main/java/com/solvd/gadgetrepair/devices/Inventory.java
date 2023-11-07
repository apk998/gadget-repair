package com.solvd.gadgetrepair.devices;

// Stores the stock of spare parts and tools for repair
public class Inventory {
    private String[] parts;
    private int[] quantities;
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

    public void addPart(String part, int quantity) {
        if (isInventoryFull()) {
            System.out.println("Inventory is full. Cannot add part(s).");
            return;
        }

        for (int i=0; i < parts.length; i++) {
            if (parts[i] == null) {
                parts[i] = part;
                quantities[i] = quantity;
                break;
            }
        }
    }

    public void removePart(String part, int quantity) {
        for (int i=0; i < parts.length; i++) {
            if (parts[i] != null && parts[i].equals(part)) {
                if (quantities[i] > quantity) {
                    quantities[i] -= quantity;
                } else {
                    parts[i] = null;
                    quantities[i] = 0;
                }
                break;
            }
        }
    }

    public int getQuantity(String part) {
        for (int i = 0; i < parts.length; i++) {
            if (part.equals(parts[i])) {
                return quantities[i];
            }
        }
        return 0;   // if the part is not found
    }
}
