package com.solvd.gadgetrepair.devices;

import java.util.HashMap;
import java.util.Map;

public class RepairCosts {
    private final Map<String, Double> partCost;
    private final Map<String, Integer> timeEstimate;

    public RepairCosts () {
        this.partCost = new HashMap<>();
        this.timeEstimate = new HashMap<>();
    }

    public void setPartCost(String partName, double cost) {
        partCost.put(partName, cost);
    }

    public void setTimeEstimate(String problemDescription, int hours) {
        timeEstimate.put(problemDescription, hours);
    }

    public double getPartCost(String partName) {
        return partCost.getOrDefault(partName, 0.0);
    }

    public int getTimeEstimate(String problemDescription) {
        return timeEstimate.getOrDefault(problemDescription, 0);
    }
}
