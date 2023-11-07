package com.solvd.gadgetrepair.devices;

public class LaptopRepair extends DeviceRepairInfo implements Repairable {
    public LaptopRepair(String partsNeeded, double sparePartsCost) {
        super("Laptop", partsNeeded, 0.0, 0, sparePartsCost);
    }

    @Override
    public int estimateRepairTime() {
        // Assume a fixed time estimate of 4 hours for laptop repair for all causes
        return 4;
    }

    @Override
    public double calculatePartsCost(String partsNeeded) {
        if ("screen".equalsIgnoreCase(partsNeeded)) {
            return 300.00;
        } else if ("battery".equalsIgnoreCase(partsNeeded)) {
            return 150.00;
        } else if ("camera lens".equalsIgnoreCase(partsNeeded)) {
            return 50.00;
        } else if ("keyboard".equalsIgnoreCase(partsNeeded)) {
            return 125.00;
        } else {
            return 0.00; // part is not recognized or supported
        }
    }

    @Override
    public double calculateRepairCost() {
        int repairTime = estimateRepairTime();
        double partsCost = calculatePartsCost(partsNeeded);
        double laborCost = LABOR_COST_PER_HOUR * repairTime;
        double totalCost = (laborCost + partsCost + ADDITIONAL_FEES) * TAX_RATE;
        return totalCost;
    }
}
