package com.solvd.gadgetrepair;

import com.solvd.gadgetrepair.cost.Payment;
import com.solvd.gadgetrepair.cost.RepairService;
import com.solvd.gadgetrepair.devices.DeviceRepairInfo;
import com.solvd.gadgetrepair.devices.Gadget;
import com.solvd.gadgetrepair.devices.ServiceRecord;
import com.solvd.gadgetrepair.human.Customer;
import com.solvd.gadgetrepair.status.Notification;

public class Main {
    public static void main (String[] args) {
        // Create and initialize objects, can customize
        Customer customer = new Customer("Max Stirner", "mstirner@example.com", "215-555-9640", "email");
        Gadget gadget = new Gadget("Phone", "SN123456", "Camera lens cracked");
        DeviceRepairInfo repairInfo = new DeviceRepairInfo("Phone", 150.00, 2);
        ServiceRecord[] repairRecords = {
                new ServiceRecord("15/2/22", 100.0, "Screen replacement"),
                new ServiceRecord("14/1/23", 50.0, "Battery replacement"),
                new ServiceRecord("22/10/23", 75.00, "Software update")
        };
        Notification notification = new Notification(customer, "Repair complete", "Your device is ready for pickup");
        Payment payment = new Payment();


        // Add a new service record to the customer
        ServiceRecord newRecord = new ServiceRecord("30/10/23", 60.0, "Camera repair");
        customer.addRepairRecord(newRecord);

        // Calculate total repair cost
        payment.calculateCost(customer, customer.getRepairHistory().toArray(new ServiceRecord[0]));

        // Generate and send invoice
        payment.generateInvoice(customer);
        notification.sendNotification();

        // Process payment
        String paymentMethod = "Credit card";
        payment.setPaymentMethod(paymentMethod);
        payment.processPayment(customer);
    }
}