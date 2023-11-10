package com.solvd.gadgetrepair;

import com.solvd.gadgetrepair.cost.Billing;
import com.solvd.gadgetrepair.cost.CreditCard;
import com.solvd.gadgetrepair.devices.*;
import com.solvd.gadgetrepair.exceptions.*;
import com.solvd.gadgetrepair.human.Customer;
import com.solvd.gadgetrepair.human.Employee;
import com.solvd.gadgetrepair.status.Email;
import com.solvd.gadgetrepair.status.Notifiable;
import com.solvd.gadgetrepair.status.RepairStatus;

public class Main {
    public static void main(String[] args) {
        // Create and initialize objects, can customize
        Customer customer1 = new Customer("Max Stirner", "mstirner@example.com", "215-555-9640", "email");
        Customer customer2 = new Customer("Emma Goldman", "egoldman@example.com", "215-555-2321", "email");
        Customer customer3 = new Customer("Kahlil Gibran", "kgibran@example.com", "215-555-1854", "SMS");
        Gadget gadget1;
        Gadget gadget2;
        try {
            gadget1 = new Gadget("Pager", "SN789", "won't turn on");
            gadget2 = new Gadget("Phone", "SN123456", "cracked screen");
        } catch (GadgetException e) {
            throw new RuntimeException(e);
        }
        PhoneRepair repairInfo = new PhoneRepair("screen", 150.00);
        ServiceRecord[] repairRecords = {
                new ServiceRecord("15/2/22", 250.00, "Screen replacement"),
                new ServiceRecord("14/1/23", 100.00, "Battery replacement"),
                new ServiceRecord("22/10/23", 75.00, "Software update")
        };
        Notifiable emailMethod = new Email(null);
        Billing payment = new CreditCard();


        // Customer brings in a new device for repair
        System.out.println(customer1.getFullName() + " brings in a " + gadget1.getGadgetType() + " that " + gadget1.getProblemDescription() + " for repair.");
        System.out.println(customer2.getFullName() + " brings in a " + gadget2.getGadgetType() + " with a " + gadget2.getProblemDescription() + " for repair.");

        // Update gadget repair status
        RepairStatus repairStatus = new RepairStatus();
        repairStatus.addToQueue(gadget2);
        System.out.println("Gadget status is " + repairStatus.getStatus(gadget2));

        // Find an employee
        Employee employee = new Employee("John Zerzan", "Phone technician", "Available");
        System.out.println("Employee " + employee.getFullName() + " is " + employee.getAvailability());
        employee.setAvailability("Busy");
        System.out.println(employee.getFullName() + " is now " + employee.getAvailability());
        repairStatus.markUnderRepair(gadget2);
        System.out.println("Gadget status is " + repairStatus.getStatus(gadget2));

        // Get time and cost estimates
        int phoneRepairTime = repairInfo.estimateRepairTime();
        double phoneRepairCost = repairInfo.calculateRepairCost();
        System.out.println("Phone Repair - Time Estimate: " + phoneRepairTime + " hours, Cost: $" + phoneRepairCost);

        // Check inventory for necessary part
        Inventory inventory = new Inventory(Inventory.MAX_CAPACITY);
        try {
            inventory.addPart("screen", Inventory.DEFAULT_PART_QUANTITY);
        } catch (InventoryFullException e) {
            throw new RuntimeException(e);
        }
        int screenStock = inventory.getQuantity("keyboard");
        if (screenStock > 0) {
            System.out.println("Available screens in inventory: " + screenStock);

            // Get the part
            int screensNeeded = 1;
            if (screenStock >= screensNeeded) {
                try {
                    inventory.removePart("keyboard", screensNeeded);
                } catch (NotEnoughPartsException | UnsupportedPartException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Used " + screensNeeded + " screen(s) from inventory for repair.");
            } else {
                System.out.println("Not enough screens for repair.");
            }
        } else {
            System.out.println("No screens in inventory, repair cannot proceed.");
        }
        System.out.println(inventory.getQuantity("screen") + " screen(s) left in inventory.");

        // Gadget repair is now finished
        repairStatus.markReady(gadget2);
        System.out.println("Gadget status is " + repairStatus.getStatus(gadget2));

        // Add a new service record to the customer
        ServiceRecord newRecord = new ServiceRecord("30/10/23", 237.60, "Screen repair");
        customer2.addRepairRecord(newRecord);

        // Update customer's repair history
        customer2.getRepairHistory().toArray(new ServiceRecord[0]);

        // Generate and send invoice
        payment.calculateCost(customer2, new ServiceRecord[]{newRecord});
        emailMethod.sendNotification(customer2, "Repair complete", "Your device is ready for pickup");

        // Process payment
        try {
            payment.processPayment(customer2);
        } catch (PaymentException e) {
            throw new RuntimeException(e);
        }
    }
}
