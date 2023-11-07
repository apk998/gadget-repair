package com.solvd.gadgetrepair.cost;

import com.solvd.gadgetrepair.human.Customer;

public class Cash extends Billing implements Payable {
    public Cash() {
        paymentMethod = "Cash";
    }

    @Override
    public void processPayment(Customer customer) {
        // Pretend customer has paid with cash
        System.out.println("Processing credit card payment for: " + customer.getFullName());
        System.out.println("Total amount: $" + totalCost);
        System.out.println("Payment method: " + paymentMethod);
    }
}
