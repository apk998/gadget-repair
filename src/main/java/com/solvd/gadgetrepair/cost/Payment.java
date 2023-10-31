package com.solvd.gadgetrepair.cost;

import com.solvd.gadgetrepair.devices.ServiceRecord;
import com.solvd.gadgetrepair.human.Customer;

// Calculates total cost, generates invoices, and processes payments
public class Payment {
    private double totalCost;
    private String paymentMethod;

    public Payment() {
        totalCost = 0.0;
        paymentMethod = "Credit Card";   // default payment method
    }

    public void calculateCost(Customer customer, ServiceRecord[] repairRecords) {
        totalCost = 0.0;
        for (ServiceRecord record : repairRecords) {
            totalCost += record.getRepairCost();
        }
    }

    public void generateInvoice(Customer customer) {
        // Pretend an invoice is actually generated
        System.out.println("Invoice for: " + customer.getFullName());
        System.out.println("Total Cost: $" + totalCost);
        System.out.println("Payment Method: " + paymentMethod);
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void processPayment(Customer customer) {
        // Pretend the payment is actually processed
        System.out.println("Payment processed for: " + customer.getFullName());
        System.out.println("Total Amount: $" + totalCost);
        System.out.println("Payment Method: " + paymentMethod);
    }
}
