package com.solvd.gadgetrepair.status;

import com.solvd.gadgetrepair.human.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Email extends Notification implements Notifiable {
    private static final Logger LOGGER=LogManager.getLogger(Email.class);
    public Email(Notifiable notifiable) {
        super(notifiable);
    }

    @Override
    public void sendNotification(Customer recipient, String subject, String message) {
        // Pretend an email is actually being sent
        LOGGER.info("Sending an email notification to: " + recipient.getEmail());
        LOGGER.info("Subject: " + subject);
        LOGGER.info("Message: " + message);
    }
}
