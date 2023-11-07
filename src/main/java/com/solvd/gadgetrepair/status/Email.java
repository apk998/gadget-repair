package com.solvd.gadgetrepair.status;

import com.solvd.gadgetrepair.human.Customer;

public class Email extends Notification implements Notifiable {
    public Email(Notifiable notifiable) {
        super(notifiable);
    }

    @Override
    public void sendNotification(Customer recipient, String subject, String message) {
        // Pretend an email is actually being sent
        System.out.println("Sending an email notification to: " + recipient.getEmail());
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + message);
    }
}
