package com.solvd.gadgetrepair;

import com.solvd.gadgetrepair.cost.Billing;
import com.solvd.gadgetrepair.cost.RepairService;
import com.solvd.gadgetrepair.devices.DeviceRepairInfo;
import com.solvd.gadgetrepair.devices.Gadget;
import com.solvd.gadgetrepair.devices.Inventory;
import com.solvd.gadgetrepair.devices.ServiceRecord;
import com.solvd.gadgetrepair.human.Person;
import com.solvd.gadgetrepair.status.Notification;
import com.solvd.gadgetrepair.status.RepairStatus;

public class Main {
    public static void main(String[] args) {
        // Create and initialize objects, can customize
        Person.Customer customer = new Person.Customer("Max Stirner", "mstirner@example.com", "215-555-9640", "email");
        Gadget gadget = new Gadget("Phone", "SN123456", "Cracked screen");
        DeviceRepairInfo.PhoneRepair repairInfo = new DeviceRepairInfo.PhoneRepair("screen", 150.00);
        ServiceRecord[] repairRecords = {
                new ServiceRecord("15/2/22", 250.00, "Screen replacement"),
                new ServiceRecord("14/1/23", 100.00, "Battery replacement"),
                new ServiceRecord("22/10/23", 75.00, "Software update")
        };
        Notification notification = new Notification.Email(customer, "Repair complete", "Your device is ready for pickup");
        Billing payment = new Billing.CreditCard();


        // Customer brings in a new device for repair
        System.out.println(customer.getFullName() + " brings in a " + gadget.getGadgetType() + " with a " + gadget.getProblemDescription() + " for repair.");
        RepairService repairService = new RepairService();

        // Update gadget repair status
        RepairStatus repairStatus = new RepairStatus();
        repairStatus.addToQueue(gadget);
        System.out.println("Gadget status is " + repairStatus.getStatus(gadget));

        // Find an employee
        Person.Employee employee = new Person.Employee("John Zerzan", "Phone technician", "Available");
        System.out.println("Employee " + employee.getFullName() + " is " + employee.getAvailability());
        employee.setAvailability("Busy");
        System.out.println(employee.getFullName() + " is now " + employee.getAvailability());
        repairStatus.markUnderRepair(gadget);
        System.out.println("Gadget status is " + repairStatus.getStatus(gadget));

        // Get time and cost estimates
        int phoneRepairTime = repairInfo.estimateRepairTime();
        double phoneRepairCost = repairInfo.calculateRepairCost();
        System.out.println("Phone Repair - Time Estimate: " + phoneRepairTime + " hours, Cost: $" + phoneRepairCost);

        // Check inventory for necessary part
        Inventory inventory = new Inventory(250);
        inventory.addPart("screen", 10);
        int screenStock = inventory.getQuantity("screen");
        if (screenStock > 0) {
            System.out.println("Available screens in inventory: " + screenStock);

            // Get the part
            int screensNeeded = 1;
            if (screenStock >= screensNeeded) {
                inventory.removePart("screen", screensNeeded);
                System.out.println("Used " + screensNeeded + " screen(s) from inventory for repair.");
            } else {
                System.out.println("Not enough screens for repair.");
            }
        } else {
            System.out.println("No screens in inventory, repair cannot proceed.");
        }
        System.out.println(inventory.getQuantity("screen") + " screen(s) left in inventory.");

        // Gadget repair is now finished
        repairStatus.markReady(gadget);
        System.out.println("Gadget status is " + repairStatus.getStatus(gadget));

        // Add a new service record to the customer
        ServiceRecord newRecord = new ServiceRecord("30/10/23", 220.00, "Screen repair");
        customer.addRepairRecord(newRecord);

        // Update customer's repair history
        customer.getRepairHistory().toArray(new ServiceRecord[0]);

        // Generate and send invoice
        payment.calculateCost(customer, new ServiceRecord[]{newRecord});
        notification.sendNotification();

        // Process payment
        payment.processPayment(customer);
    }
}
