package com.solvd.gadgetrepair.devices;

import java.util.ArrayList;
import java.util.List;

public class RepairCosts {
    public static final double LABOR_COST_PER_HOUR = 25.00;
    public static final double ADDITIONAL_FEES = 20.00;
    public static final double TAX_RATE = 1.08;

    private final List<ICost> costs = new ArrayList<>();
    private final Gadget gadget;
    private int timeEstimate;   // in hours

    public RepairCosts(Gadget gadget) {
        this.gadget = gadget;
    }

    public int getTimeEstimate() {
        return timeEstimate;
    }

    public void setTimeEstimate(int timeEstimate) {
        this.timeEstimate = timeEstimate;
    }

    public double getAllPartsCost() {
        return costs.stream().mapToDouble(ICost::getCost).sum();
    }

    public void addCost(ICost cost) {
        costs.add(cost);
    }

    public double calculateRepairCost() {
        double laborCost = getTimeEstimate() * LABOR_COST_PER_HOUR;
        double partsCost = getAllPartsCost();
        double subtotal = laborCost + partsCost;
        double totalCost = subtotal + ADDITIONAL_FEES;
        return totalCost * TAX_RATE;
    }
}