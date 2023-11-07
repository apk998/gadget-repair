package com.solvd.gadgetrepair.cost;

import com.solvd.gadgetrepair.human.Customer;

public class CreditCard extends Billing implements Payable {
    public CreditCard() {
        paymentMethod = "Credit card";
    }

    @Override
    public void processPayment (Customer customer) {
        // Pretend customer has paid with credit card
        System.out.println("Processing credit card payment for: " + customer.getFullName());
        System.out.println("Total amount: $" + totalCost);
        System.out.println("Payment method: " + paymentMethod);
    }
}
