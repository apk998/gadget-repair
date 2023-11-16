package com.solvd.gadgetrepair.devices;

// Records repair history of each customer
public class ServiceRecord<T> {
    private T repairDate;   // can be datetime, String, or other object
    private double repairCost;
    private T repairDetails;

    public ServiceRecord(T repairDate, double repairCost, T repairDetails) {
        this.repairDate = repairDate;
        this.repairCost = repairCost;
        this.repairDetails = repairDetails;
    }

    public T getRepairDate() {
        return repairDate;
    }
    public void setRepairDate(T repairDate) {
        this.repairDate = repairDate;
    }
    public double getRepairCost() {
        return repairCost;
    }
    public void setRepairCost(double repairCost) {
        this.repairCost = repairCost;
    }
    public T getRepairDetails() {
        return repairDetails;
    }
    public void setRepairDetails(T repairDetails) {
        this.repairDetails = repairDetails;
    }

    @Override
    public String toString() {
        return "Repair Date: " + repairDate + "\nRepair Cost: $" + repairCost + "\nRepair Details: " + repairDetails;
    }
}
