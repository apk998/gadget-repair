package com.solvd.gadgetrepair.cost;

import com.solvd.gadgetrepair.human.Customer;

public class PayPal extends Billing implements Payable {
    public PayPal() {
        paymentMethod = "PayPal";
    }

    @Override
    public void processPayment(Customer customer) {
        // Pretend customer has paid via PayPal
        System.out.println("Processing credit card payment for: " + customer.getFullName());
        System.out.println("Total amount: $" + totalCost);
        System.out.println("Payment method: " + paymentMethod);
    }
}
