package com.solvd.gadgetrepair.cost;

import com.solvd.gadgetrepair.devices.RepairCosts;
import com.solvd.gadgetrepair.devices.ServiceRecord;
import com.solvd.gadgetrepair.human.Customer;

public interface Payable {
    void calculateCost(Customer customer, ServiceRecord[] repairRecords);
    void processPayment(Customer customer, RepairCosts repairCosts);
}