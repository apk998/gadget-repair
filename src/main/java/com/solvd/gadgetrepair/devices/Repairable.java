package com.solvd.gadgetrepair.devices;

public interface Repairable {
    int estimateRepairTime();
    double calculatePartsCost(String partsNeeded);
    double calculateRepairCost();
}
