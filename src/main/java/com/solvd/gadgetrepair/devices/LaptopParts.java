package com.solvd.gadgetrepair.devices;

@SuppressWarnings("unused")
public enum LaptopParts implements ICost {
    SCREEN(300.00),
    HARD_DRIVE(50.00),
    BATTERY(150.00),
    KEYBOARD(125.00),
    FAN(50.00),
    LABOR_ONLY(0.00);

    private final double cost;

    LaptopParts(double cost) {
        this.cost = cost;
    }

    @Override
    public double getCost() {
        return cost;
    }
}