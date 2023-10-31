package com.solvd.gadgetrepair.status;

import com.solvd.gadgetrepair.human.Customer;

// Manages notifications and communication within gadget repair service
public class Notification {
    private Customer recipient;
    private String subject;
    private String message;

    public Notification(Customer recipient, String subject, String message) {
        this.recipient = recipient;
        this.subject = subject;
        this.message = message;
    }

    public void sendNotification() {
        String preferredContact = recipient.getPreferredContact();

        if ("email".equalsIgnoreCase(preferredContact)) {
            sendEmailNotification();
        } else if ("sms".equalsIgnoreCase(preferredContact)) {
            sendSMSNotification();
        } else {
            System.out.println("Unsupported contact method, no notification sent");
        }
    }

    private void sendEmailNotification() {
        String email = recipient.getEmail();
        // Pretend an email is actually being sent
        System.out.println("Sending an email notification to: " + email);
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + message);
    }

    private void sendSMSNotification() {
        String phoneNumber = recipient.getPhoneNumber();
        // Pretend an SMS is actually being sent
        System.out.println("Sending an SMS notification to: " + phoneNumber);
        System.out.println("Message: " + message);
    }

    public Customer getRecipient() {
        return recipient;
    }
    public String getSubject() {
        return subject;
    }
    public String getMessage() {
        return message;
    }
}