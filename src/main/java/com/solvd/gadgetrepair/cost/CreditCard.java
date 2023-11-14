package com.solvd.gadgetrepair.cost;

import com.solvd.gadgetrepair.human.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreditCard extends Billing implements Payable {
    private static final Logger LOGGER=LogManager.getLogger(CreditCard.class);
    public CreditCard() {
        setPaymentMethod("Credit card");
    }

    @Override
    public void processPayment (Customer customer) {
        // Pretend customer has paid with credit card
        LOGGER.info("Processing credit card payment for: " + customer.getFullName());
        LOGGER.info("Total amount: $" + getTotalCost());
        LOGGER.info("Payment method: " + getPaymentMethod());
    }
}
