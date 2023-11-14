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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    static {
        System.setProperty("log4j.configurationfile", "log4j2.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        // Customers enter the store
        Customer customer1 = new Customer("Max Stirner", "mstirner@example.com", "215-555-9640", "SMS");
        Customer customer2 = new Customer("Emma Goldman", "egoldman@example.com", "215-555-2321", "email");

        // Customers present gadgets for repair
        Gadget gadget1;
        try {
            gadget1 = new Gadget("Pager", "SN789", "won't turn on");
            LOGGER.info(customer1.getFullName() + " brings in a " + gadget1.getGadgetType() + " that " + gadget1.getProblemDescription() + " for repair.");
        } catch (GadgetException e) {
            LOGGER.info("Unaccepted gadget: " + e.getMessage());
        }
        Gadget gadget2 = null;
        try {
            gadget2 = new Gadget("Phone", "SN123456", "cracked screen");
            LOGGER.info(customer2.getFullName() + " brings in a " + gadget2.getGadgetType() + " with a " + gadget2.getProblemDescription() + " for repair.");
        } catch (GadgetException e) {
            LOGGER.info("Unaccepted gadget: " + e.getMessage());
        }

        // Pull up customer record, enter preferred notification method
        PhoneRepair repairInfo = new PhoneRepair("screen", 150.00);
        ServiceRecord[] repairRecords = {
                new ServiceRecord("15/2/22", 250.00, "Screen replacement"),
                new ServiceRecord("14/1/23", 100.00, "Battery replacement"),
                new ServiceRecord("22/10/23", 75.00, "Software update")
        };
        Notifiable emailMethod = new Email(null);

        // Update gadget repair status
        RepairStatus repairStatus = new RepairStatus();
        repairStatus.addToQueue(gadget2);
        LOGGER.info("Gadget status is " + repairStatus.getStatus(gadget2));

        // Find an employee
        Employee employee = new Employee("John Zerzan", "Phone technician", "Available");
        LOGGER.info("Employee " + employee.getFullName() + " is " + employee.getAvailability());
        employee.setAvailability("Busy");
        LOGGER.info(employee.getFullName() + " is now " + employee.getAvailability());
        repairStatus.markUnderRepair(gadget2);
        LOGGER.info("Gadget status is " + repairStatus.getStatus(gadget2));

        // Get time and cost estimates
        int phoneRepairTime = repairInfo.estimateRepairTime();
        double phoneRepairCost = repairInfo.calculateRepairCost();
        LOGGER.info("Phone Repair - Time Estimate: " + phoneRepairTime + " hours, Cost: $" + phoneRepairCost);

        // Check inventory for necessary part
        Inventory inventory = new Inventory(Inventory.MAX_CAPACITY);
        // Employee mistakenly grabs wrong part
        try {
            int screensNeeded = 1;
            try {
                inventory.removePart("keyboard", screensNeeded);
                LOGGER.info("Used " + screensNeeded + " screen(s) from inventory for repair.");
            } catch (UnsupportedPartException e) {
                LOGGER.info("Unsupported part: " + e.getMessage());
                // Offer the employee another chance to grab the correct part
                inventory.addPart("screen", 1);
            } catch (NotEnoughPartsException e) {
                LOGGER.info("Not enough parts in stock: " + e.getMessage());
                // Offer the employee another chance to grab the correct quantity
                inventory.addPart("screen", Inventory.DEFAULT_PART_QUANTITY);
            }
        } catch (InventoryFullException e) {
            LOGGER.info("Inventory is full: " + e.getMessage());
            // Reset inventory to a lower capacity
            inventory = new Inventory(Inventory.MAX_CAPACITY - 10);
        } finally {
            int screenStock = inventory.getQuantity("screen");
            if (screenStock > 0) {
                LOGGER.info("Available screens in inventory: " + screenStock);
            } else {
                LOGGER.info("No screens in inventory, repair cannot proceed.");
            }
            LOGGER.info(screenStock + " screen(s) left in inventory.");
        }

        // Gadget repair is now finished
        repairStatus.markReady(gadget2);
        LOGGER.info("Gadget status is " + repairStatus.getStatus(gadget2));

        // Add a new service record to the customer
        ServiceRecord newRecord = new ServiceRecord("13/11/23", 237.60, "Screen repair");
        customer2.addRepairRecord(newRecord);

        // Update customer's repair history
        customer2.getRepairHistory().toArray(new ServiceRecord[0]);

        // Generate and send invoice
        Billing payment = new CreditCard();
        payment.calculateCost(customer2, new ServiceRecord[]{newRecord});
        emailMethod.sendNotification(customer2, "Repair complete", "Your device is ready for pickup");

        // Process payment
        try {
            payment.processPayment(customer2);
        } catch (PaymentException e) {
            LOGGER.info("Failed to process payment: " + e.getMessage());
            // Customer offers alternative payment method
            Billing alternativePayment = new CreditCard();
            try {
                alternativePayment.processPayment(customer2);
            } catch (PaymentException ex) {
                LOGGER.info("Failed to process alternative payment: " + e.getMessage());
            }
        }
    }
}
