package com.solvd.gadgetrepair.devices;

public enum RepairParts implements ICost, IName {
    PHONE_SCREEN(150.00),
    PHONE_CAMERA_LENS(100.00),
    PHONE_BATTERY(50.00),
    PHONE_CHARGING_PORT(50.00),

    LAPTOP_SCREEN(300.00),
    LAPTOP_HARD_DRIVE(50.00),
    LAPTOP_BATTERY(150.00),
    LAPTOP_KEYBOARD(125.00),
    LAPTOP_FAN(50.00),

    TV_SCREEN(250.00),
    TV_LED_BACKLIGHTS(150.00),
    TV_CAPACITORS(50.00),
    TV_INTERNAL_SPEAKERS(50.00),

    LABOR_ONLY(0.00);

    private final double cost;
    RepairParts(double cost) {
        this.cost = cost;
    }


    @Override
    public double getCost() {
        return cost;
    }

    @Override
    public String getDisplayName() {
        return name().toLowerCase().replace('_', ' ');
    }
}
