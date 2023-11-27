package com.solvd.gadgetrepair.cost;

import com.solvd.gadgetrepair.human.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PayPal extends Billing implements Payable {
    private static final Logger LOGGER= LogManager.getLogger(PayPal.class);

    @Override
    public void processPayment(Customer customer) {
        // Pretend customer has paid via PayPal
        LOGGER.info("Processing credit card payment for: " + customer.getFullName());
        LOGGER.info("Total amount: $" + getTotalCost());
        LOGGER.info("Payment method: " + getPaymentMethod());
    }
}
