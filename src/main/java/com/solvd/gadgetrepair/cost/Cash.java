package com.solvd.gadgetrepair.cost;

import com.solvd.gadgetrepair.devices.RepairCosts;
import com.solvd.gadgetrepair.devices.ServiceRecord;
import com.solvd.gadgetrepair.human.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("unused")
public class Cash extends Billing implements Payable {
    private static final Logger LOGGER=LogManager.getLogger(Cash.class);
    private double amountPaid;

    public Cash() {
        super(AcceptedPayments.CASH);
    }

    public double getAmountPaid() {
        return amountPaid;
    }
    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    @Override
    public void calculateCost(Customer customer, ServiceRecord[] repairRecords) {
    }

    @Override
    public void processPayment(Customer customer, RepairCosts repairCosts) {
        double totalCost = repairCosts.calculateRepairCost();
        double change = amountPaid - totalCost;
        LOGGER.info("Amount paid in cash: $" + amountPaid);

        if (change > 0) {
            LOGGER.info("Change: $" + change);
        } else {
            LOGGER.info("No change due.");
        }
    }
}