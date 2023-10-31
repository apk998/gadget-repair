package com.solvd.gadgetrepair.devices;

// Records repair history of each gadget
public class ServiceRecord {
    private String repairDate;   // can make datetime object later
    private double repairCost;
    private String repairDetails;

    public ServiceRecord(String repairDate, double repairCost, String repairDetails) {
        this.repairDate = repairDate;
        this.repairCost = repairCost;
        this.repairDetails = repairDetails;
    }

    public String getRepairDate() {
        return repairDate;
    }
    public void setRepairDate(String repairDate) {
        this.repairDate = repairDate;
    }
    public double getRepairCost() {
        return repairCost;
    }
    public void setRepairCost(double repairCost) {
        this.repairCost = repairCost;
    }
    public String getRepairDetails() {
        return repairDetails;
    }
    public void setRepairDetails() {
        this.repairDetails = repairDetails;
    }

    @Override
    public String toString() {
        return "Repair Date: " + repairDate + "\nRepair Cost: $" + repairCost + "\nRepair Details: " + repairDetails;
    }
}
