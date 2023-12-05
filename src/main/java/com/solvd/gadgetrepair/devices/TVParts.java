package com.solvd.gadgetrepair.devices;

@SuppressWarnings("unused")
public enum TVParts implements ICost {
    SCREEN(250.00),
    LED_BACKLIGHTS(150.00),
    CAPACITORS(50.00),
    INTERNAL_SPEAKERS(50.00),
    LABOR_ONLY(0.00);

    private final double cost;

    TVParts(double cost) {
        this.cost = cost;
    }

    @Override
    public double getCost() {
        return cost;
    }
}