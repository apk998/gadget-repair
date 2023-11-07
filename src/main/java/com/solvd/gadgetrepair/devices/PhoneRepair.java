package com.solvd.gadgetrepair.devices;

public class PhoneRepair extends DeviceRepairInfo implements Repairable {
    public PhoneRepair(String partsNeeded, double sparePartsCost) {
        super("Phone", partsNeeded, 0.0, 0, sparePartsCost);
    }

    @Override
    public int estimateRepairTime() {
        // Assume a fixed time estimate of 2 hours for phone repair for all causes
        return 2;
    }

    @Override
    public double calculatePartsCost(String partsNeeded) {
        if ("screen".equalsIgnoreCase(partsNeeded)) {
            return 150.00;
        } else if ("battery".equalsIgnoreCase(partsNeeded)) {
            return 50.00;
        } else if ("camera lens".equalsIgnoreCase(partsNeeded)) {
            return 100.00;
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
