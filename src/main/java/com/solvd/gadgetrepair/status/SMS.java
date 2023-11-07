package com.solvd.gadgetrepair.status;

import com.solvd.gadgetrepair.human.Customer;

public class SMS extends Notification implements Notifiable {

    public SMS(Notifiable notifiable) {
        super(notifiable);
    }

    @Override
    public void sendNotification(Customer recipient, String subject, String message) {
        // Pretend an SMS is actually being sent
        System.out.println("Sending an email notification to: " + recipient.getPhoneNumber());
        System.out.println("Message: " + message);
    }
}
