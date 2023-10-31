package com.solvd.gadgetrepair.devices;

// Stores information about repair costs and time estimates for a particular gadget
public class DeviceRepairInfo {
    private String gadgetType;
    private double repairCost;   // cost of repair in dollars
    private int repairTime;      // estimate of repair time in hours

    public DeviceRepairInfo(String gadgetType, double repairCost, int repairTime) {
        this.gadgetType = gadgetType;
        this.repairCost = repairCost;
        this.repairTime = repairTime;
    }

    public String getGadgetType () {
        return gadgetType;
    }
    public void setGadget (String gadgetType) {
        this.gadgetType = gadgetType;
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
        return "Device: " + gadgetType + "\nRepair Cost: $" + repairCost +
                "\nRepair Time Estimate: " + repairTime + "hours";
    }
}
