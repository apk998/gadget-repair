package com.solvd.gadgetrepair.status;

import com.solvd.gadgetrepair.human.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("unused")
public class SMS extends Notification implements Notifiable {
    private static final Logger LOGGER=LogManager.getLogger(SMS.class);
    public SMS(Notifiable notifiable) {
        super(notifiable);
    }

    @Override
    public void sendNotification(Customer recipient, String subject, String message) {
        // Pretend an SMS is actually being sent
        LOGGER.info("Sending an email notification to: " + recipient.getPhoneNumber());
        LOGGER.info("Message: " + message);
    }
}
