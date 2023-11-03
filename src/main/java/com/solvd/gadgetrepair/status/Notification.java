package com.solvd.gadgetrepair.status;

import com.solvd.gadgetrepair.human.Person;

// Manages notifications and communication within gadget repair service
public abstract class Notification {
    protected Person.Customer recipient;
    protected String subject;
    protected String message;

    public Notification(Person.Customer recipient, String subject, String message) {
        this.recipient = recipient;
        this.subject = subject;
        this.message = message;
    }

    public abstract void sendNotification();

    public Person.Customer getRecipient() {
        return recipient;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public static class Email extends Notification {
        public Email(Person.Customer recipient, String subject, String message) {
            super(recipient, subject, message);
        }

        @Override
        public void sendNotification() {
            String email = recipient.getEmail();
            // Pretend an email is actually being sent
            System.out.println("Sending an email notification to: " + email);
            System.out.println("Subject: " + subject);
            System.out.println("Message: " + message);
        }
    }

    public static class SMS extends Notification {
        public SMS(Person.Customer recipient, String subject, String message) {
            super(recipient, subject, message);
        }

        @Override
        public void sendNotification() {
            String phoneNumber = recipient.getPhoneNumber();
            // Pretend an SMS is actually being sent
            System.out.println("Sending an SMS notification to: " + phoneNumber);
            System.out.println("Message: " + message);
        }
    }
}
