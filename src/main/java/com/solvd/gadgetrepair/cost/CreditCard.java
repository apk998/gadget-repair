package com.solvd.gadgetrepair.cost;

import com.solvd.gadgetrepair.devices.RepairCosts;
import com.solvd.gadgetrepair.devices.ServiceRecord;
import com.solvd.gadgetrepair.human.Customer;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreditCard extends Billing implements Payable {
    private static final Logger LOGGER=LogManager.getLogger(CreditCard.class);
    private String creditCardNumber;
    private double creditLimit;

    public CreditCard() {
        super(AcceptedPayments.CREDIT_CARD);
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }
    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }
    public double getCreditLimit() {
        return creditLimit;
    }
    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    @Override
    public void calculateCost(Customer customer, ServiceRecord[] repairRecords) {
    }

    @Override
    public void processPayment(Customer customer, RepairCosts repairCosts) {
        double totalCost = repairCosts.calculateRepairCost();
        double difference = creditLimit - totalCost;
        String lastDigits = StringUtils.substring(creditCardNumber, creditCardNumber.length() - 4);
        LOGGER.info("Amount charged to credit card ending in " + lastDigits + ": $" + totalCost);

        if (difference < 0) {
            LOGGER.info("Card declined. Insufficient credit limit.");
        } else {
            LOGGER.info("Payment successful.");
        }
    }
}