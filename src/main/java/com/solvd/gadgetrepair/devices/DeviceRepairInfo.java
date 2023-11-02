package com.solvd.gadgetrepair.devices;

// Stores information about repair costs and time estimates for a particular gadget
public class DeviceRepairInfo {
    private String gadgetType;
    private String partsNeeded;  // to calculate cost of spare parts
    private double repairCost;   // cost of repair in dollars
    private int repairTime;      // estimate of repair time in hours

    public DeviceRepairInfo(String gadgetType, String partsNeeded, double repairCost, int repairTime) {
        this.gadgetType = gadgetType;
        this.partsNeeded = partsNeeded;
        this.repairCost = repairCost;
        this.repairTime = repairTime;
    }

    public String getGadgetType () {
        return gadgetType;
    }
    public void setGadget (String gadgetType) {
        this.gadgetType = gadgetType;
    }
    public String getPartsNeeded () {
        return partsNeeded;
    }
    public void setPartsNeeded (String partsNeeded) {
        this.partsNeeded = partsNeeded;
    }
    public double getRepairCost () {
        return repairCost;
    }
    public void setRepairCost (double repairCost) {
        this.repairCost = repairCost;
    }
    public int getRepairTime () {
        return repairTime;
    }
    public void setRepairTime (int repairTime) {
        this.repairTime = repairTime;
    }

    @Override
    public String toString() {
        return "Device: " + gadgetType + "\nPart(s) Needed: " + partsNeeded + 
               "\nRepair Cost: $" + repairCost + "\nRepair Time Estimate: " + repairTime + "hours";
    }
}
