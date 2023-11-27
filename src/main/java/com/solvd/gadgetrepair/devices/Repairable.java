package com.solvd.gadgetrepair.devices;

public interface Repairable {
    int estimateRepairTime(Gadget gadget);
    double calculatePartsCost(String partNeeded);
    String getPartNeeded();
}