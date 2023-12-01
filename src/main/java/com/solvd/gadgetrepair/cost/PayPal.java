package com.solvd.gadgetrepair.cost;

import com.solvd.gadgetrepair.devices.RepairService;
import com.solvd.gadgetrepair.devices.ServiceRecord;
import com.solvd.gadgetrepair.human.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PayPal extends Billing implements Payable {
    private static final Logger LOGGER= LogManager.getLogger(PayPal.class);
    private double accountBalance;

    public PayPal() {
        super(AcceptedPayments.PAYPAL);
    }

    public double getAccountBalance() {
        return accountBalance;
    }
    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public void calculateCost(Customer customer, ServiceRecord[] repairRecords) {
    }

    @Override
    public void processPayment(Customer customer, RepairService repairService) {
        double totalCost = repairService.calculateRepairCost();
        double difference = accountBalance - totalCost;
        String account = customer.getEmail();
        LOGGER.info("Amount charged through PayPal account " + account + ": $" + totalCost);

        if (difference < 0) {
            LOGGER.info("Bank declined electronic funds transfer.");
        } else {
            LOGGER.info("Payment successful.");
        }
    }
}
