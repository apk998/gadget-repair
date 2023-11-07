package com.solvd.gadgetrepair;

import com.solvd.gadgetrepair.cost.Billing;
import com.solvd.gadgetrepair.cost.CreditCard;
import com.solvd.gadgetrepair.devices.*;
import com.solvd.gadgetrepair.human.Customer;
import com.solvd.gadgetrepair.human.Employee;
import com.solvd.gadgetrepair.status.Email;
import com.solvd.gadgetrepair.status.Notifiable;
import com.solvd.gadgetrepair.status.RepairStatus;

public class Main {
    public static void main(String[] args) {
        // Create and initialize objects, can customize
        Customer customer = new Customer("Max Stirner", "mstirner@example.com", "215-555-9640", "email");
        Gadget gadget = new Gadget("Phone", "SN123456", "Cracked screen");
        PhoneRepair repairInfo = new PhoneRepair("screen", 150.00);
        ServiceRecord[] repairRecords = {
                new ServiceRecord("15/2/22", 250.00, "Screen replacement"),
                new ServiceRecord("14/1/23", 100.00, "Battery replacement"),
                new ServiceRecord("22/10/23", 75.00, "Software update")
        };
        Notifiable emailMethod = new Email(null);
        Billing payment = new CreditCard();


        // Customer brings in a new device for repair
        System.out.println(customer.getFullName() + " brings in a " + gadget.getGadgetType() + " with a " + gadget.getProblemDescription() + " for repair.");

        // Update gadget repair status
        RepairStatus repairStatus = new RepairStatus();
        repairStatus.addToQueue(gadget);
        System.out.println("Gadget status is " + repairStatus.getStatus(gadget));

        // Find an employee
        Employee employee = new Employee("John Zerzan", "Phone technician", "Available");
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
        Inventory inventory = new Inventory(Inventory.MAX_CAPACITY);
        inventory.addPart("screen", Inventory.DEFAULT_PART_QUANTITY);
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
        ServiceRecord newRecord = new ServiceRecord("30/10/23", 237.60, "Screen repair");
        customer.addRepairRecord(newRecord);

        // Update customer's repair history
        customer.getRepairHistory().toArray(new ServiceRecord[0]);

        // Generate and send invoice
        payment.calculateCost(customer, new ServiceRecord[]{newRecord});
        emailMethod.sendNotification(customer, "Repair complete", "Your device is ready for pickup");

        // Process payment
        payment.processPayment(customer);
    }
}
