package com.solvd.gadgetrepair.cost;

import com.solvd.gadgetrepair.devices.ServiceRecord;
import com.solvd.gadgetrepair.human.Person;

// Calculates total cost, generates invoices, and processes payments
public abstract class Billing {
    protected double totalCost;
    protected String paymentMethod;

    public Billing() {
        totalCost = 0.00;
        paymentMethod = "Credit card";   // default payment method
    }

    public void calculateCost(Person.Customer customer, ServiceRecord[] repairRecords) {
        totalCost = 0.0;
        if (repairRecords.length > 0) {
            totalCost = repairRecords[repairRecords.length - 1].getRepairCost();
        }
    }

    public abstract void processPayment (Person.Customer customer);

    public static class CreditCard extends Billing {
        public CreditCard() {
            paymentMethod = "Credit card";
        }

        @Override
        public void processPayment (Person.Customer customer) {
            // Pretend customer has paid with credit card
            System.out.println("Processing credit card payment for: " + customer.getFullName());
            System.out.println("Total amount: $" + totalCost);
            System.out.println("Payment method: " + paymentMethod);
        }
    }

    public static class PayPal extends Billing {
        public PayPal() {
            paymentMethod = "PayPal";
        }

        @Override
        public void processPayment(Person.Customer customer) {
            // Pretend customer has paid via PayPal
            System.out.println("Processing credit card payment for: " + customer.getFullName());
            System.out.println("Total amount: $" + totalCost);
            System.out.println("Payment method: " + paymentMethod);
        }
    }

    public static class Cash extends Billing {
        public Cash() {
            paymentMethod = "Cash";
        }

        @Override
        public void processPayment(Person.Customer customer) {
            // Pretend customer has paid with cash
            System.out.println("Processing credit card payment for: " + customer.getFullName());
            System.out.println("Total amount: $" + totalCost);
            System.out.println("Payment method: " + paymentMethod);
        }
    }
}

