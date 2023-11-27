package com.solvd.gadgetrepair.status;

import com.solvd.gadgetrepair.human.Customer;

public abstract class Notification {
    private final Notifiable notifiable;

    public Notification(Notifiable notifiable) {
        this.notifiable = notifiable;
    }

    public void sendNotification(Customer recipient, String subject, String message) {
        notifiable.sendNotification(recipient, subject, message);
    }
}