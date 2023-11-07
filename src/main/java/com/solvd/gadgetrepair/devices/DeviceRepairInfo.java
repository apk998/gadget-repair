package com.solvd.gadgetrepair.devices;

// Stores information about repair costs and time estimates for a particular gadget
public abstract class DeviceRepairInfo {
    protected String deviceType;
    protected String partsNeeded;
    protected double repairCost;   // cost of repair in dollars
    protected int repairTime;      // estimate of repair time in hours
    protected double sparePartsCost;
    public static final double LABOR_COST_PER_HOUR = 25.00;
    public static final double ADDITIONAL_FEES = 20.00;
    public static final double TAX_RATE = 1.08;

    public DeviceRepairInfo(String deviceType, String partsNeeded, double repairCost, int repairTime,
                            double sparePartsCost) {
        this.deviceType = deviceType;
        this.partsNeeded = partsNeeded;
        this.repairCost = repairCost;
        this.repairTime = repairTime;
        this.sparePartsCost = sparePartsCost;
    }

    public abstract int estimateRepairTime();
    public double calculatePartsCost(String partsNeeded) {
        return 0.0;
    }
    public abstract double calculateRepairCost();

    public String getDeviceType () {
        return deviceType;
    }
    public void setDeviceType (String deviceType) {
        this.deviceType = deviceType;
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
    public double getSparePartsCost () {
        return sparePartsCost;
    }
    public void setSparePartsCost (double sparePartsCost) {
        this.sparePartsCost = sparePartsCost;
    }
}
