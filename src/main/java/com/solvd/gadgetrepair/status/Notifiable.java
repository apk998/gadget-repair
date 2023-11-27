package com.solvd.gadgetrepair.status;

import com.solvd.gadgetrepair.human.Customer;

public interface Notifiable {
    void sendNotification(Customer recipient, String subject, String message);
}