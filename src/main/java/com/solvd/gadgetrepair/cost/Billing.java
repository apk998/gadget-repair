package com.solvd.gadgetrepair.cost;

import com.solvd.gadgetrepair.devices.ServiceRecord;
import com.solvd.gadgetrepair.exceptions.PaymentException;
import com.solvd.gadgetrepair.human.Customer;

import java.util.Arrays;

// Calculates total cost, generates invoices, and processes payments
public abstract class Billing {
    private double totalCost;
    private String paymentMethod;

    public double getTotalCost() {
        return totalCost;
    }
    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
    public String getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void calculateCost(Customer customer, ServiceRecord[] repairRecords) {
        totalCost = 0.0;
        if (repairRecords.length > 0) {
            totalCost = repairRecords[repairRecords.length - 1].getRepairCost();
        }
    }

    public void processPayment (Customer customer) throws PaymentException {
        String paymentMethod = getPaymentMethod();
        if (!isPaymentAccepted(paymentMethod)) {
            throw new PaymentException("Unaccepted payment method: " + paymentMethod);
        }
    }

    private boolean isPaymentAccepted(String paymentMethod) {
        String[] acceptedMethods = {"Cash", "Credit card", "PayPal"};
        return Arrays.asList(acceptedMethods).contains(paymentMethod);
    }
}
