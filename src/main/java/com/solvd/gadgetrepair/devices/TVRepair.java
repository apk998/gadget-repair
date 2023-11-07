package com.solvd.gadgetrepair.devices;

public class TVRepair extends DeviceRepairInfo implements Repairable {
    public TVRepair(String partsNeeded, double sparePartsCost) {
        super("TV", partsNeeded, 0.0, 0, sparePartsCost);
    }

    @Override
    public int estimateRepairTime() {
        // Assume a fixed time estimate of 3 hours for TV repair for all causes
        return 3;
    }

    @Override
    public double calculatePartsCost(String partsNeeded) {
        if ("screen".equalsIgnoreCase(partsNeeded)) {
            return 250.00;
        } else if ("LED backlights".equalsIgnoreCase(partsNeeded)) {
            return 150.00;
        } else if ("capacitors".equalsIgnoreCase(partsNeeded)) {
            return 50.00;
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
