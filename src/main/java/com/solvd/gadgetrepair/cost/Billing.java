package com.solvd.gadgetrepair.cost;

import com.solvd.gadgetrepair.devices.ServiceRecord;
import com.solvd.gadgetrepair.human.Customer;

// Calculates total cost, generates invoices, and processes payments
public abstract class Billing {
    protected double totalCost;
    protected String paymentMethod;

    public Billing() {
        totalCost = 0.00;
        paymentMethod = "Credit card";   // default payment method
    }

    public void calculateCost(Customer customer, ServiceRecord[] repairRecords) {
        totalCost = 0.0;
        if (repairRecords.length > 0) {
            totalCost = repairRecords[repairRecords.length - 1].getRepairCost();
        }
    }

    public abstract void processPayment (Customer customer);
}
