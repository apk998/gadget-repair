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
    private static final Logger LOGGER=LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        // Customers enter the store
        Customer customer1 = new Customer();
        customer1.setFullName("Max Stirner");
        customer1.setEmail("mstirner@example.com");
        customer1.setPhoneNumber("215-555-9640");
        customer1.setPreferredContact("SMS");

        Customer customer2 = new Customer();
        customer2.setFullName("Emma Goldman");
        customer2.setEmail("egoldman@example.com");
        customer2.setPhoneNumber("215-555-2321");
        customer2.setPreferredContact("email");

        // Customers present gadgets for repair
        Gadget gadget1;
        try {
            gadget1 = new Gadget();
            gadget1.setGadgetType("Pager");
            gadget1.setProblemDescription("won't turn on");
            LOGGER.info(customer1.getFullName() + " brings in a " + gadget1.getGadgetType() + " that " + gadget1.getProblemDescription() + " for repair.");
        } catch (GadgetException e) {
            LOGGER.info(e.getMessage());
        }
        Gadget gadget2 = null;
        try {
            gadget2 = new Gadget();
            gadget2.setGadgetType("Phone");
            gadget2.setProblemDescription("cracked screen");
            LOGGER.info(customer2.getFullName() + " brings in a " + gadget2.getGadgetType() + " with a " + gadget2.getProblemDescription() + " for repair.");
        } catch (GadgetException e) {
            LOGGER.info("Unaccepted gadget: " + e.getMessage());
        }

        // Pull up customer record, enter preferred notification method
        ServiceRecord<String>[] repairRecords = new ServiceRecord[]{
                new ServiceRecord<>("15/2/22", 250.00, "Screen replacement"),
                new ServiceRecord<>("14/1/23", 100.00, "Battery replacement"),
                new ServiceRecord<>("22/10/23", 75.00, "Software update")
        };
        Notifiable emailMethod = new Email(null);

        // Update gadget repair status
        RepairStatus repairStatus = new RepairStatus();
        repairStatus.addToQueue(gadget2);
        LOGGER.info("Gadget status is " + repairStatus.getStatus(gadget2));

        // Find an employee
        Employee employee = new Employee();
        employee.setFullName("John Zerzan");
        employee.setSpecialty("Phone technician");
        employee.setAvailability("Available");
        LOGGER.info("Employee " + employee.getFullName() + " is " + employee.getAvailability());
        employee.setAvailability("Busy");
        LOGGER.info(employee.getFullName() + " is now " + employee.getAvailability());
        repairStatus.markUnderRepair(gadget2);
        LOGGER.info("Gadget status is " + repairStatus.getStatus(gadget2));

        try {
            RepairService repairService = (RepairService) RepairService.getRepairInfo(gadget2);
            int phoneRepairTime = repairService.estimateRepairTime(gadget2);
            double phoneRepairCost = repairService.calculateRepairCost();
            LOGGER.info("Phone Repair - Time Estimate: " + phoneRepairTime + " hours, Cost: $" + phoneRepairCost);
        } catch (GadgetException e) {
            LOGGER.info(e.getMessage());
        }


        // Check inventory for necessary part
        Inventory inventory = new Inventory(Inventory.MAX_CAPACITY);
        // Employee mistakenly grabs wrong part
        try {
            int screensNeeded = 1;
            try {
                inventory.removePart("keyboard", screensNeeded);
                LOGGER.info("Used " + screensNeeded + " screen(s) from inventory for repair.");
            } catch (UnsupportedPartException e) {
                LOGGER.info(e.getMessage());
                inventory.addPart("screen", 1);
            }
            // Offer the employee another chance to grab the correct part
            try {
                inventory.removePart("screen", 2);
            } catch (NotEnoughPartsException e) {
                LOGGER.info(e.getMessage());
                // Offer the employee another chance to grab the correct quantity
                inventory.addPart("screen", Inventory.DEFAULT_PART_QUANTITY);
                inventory.removePart("screen", 1);
            }
        } catch (InventoryFullException e) {
            LOGGER.info("Inventory is full: " + e.getMessage());
            // Reset inventory to a lower capacity
            inventory = new Inventory(Inventory.MAX_CAPACITY - 10);
        } finally {
            int screenStock = inventory.getQuantity("screen");
            if (screenStock > 0) {
                LOGGER.info(screenStock + " screen(s) left in inventory.");
            } else {
                LOGGER.info("No screens in inventory, repair cannot proceed.");
            }
        }

        // Gadget repair is now finished
        repairStatus.markReady(gadget2);
        LOGGER.info("Gadget status is " + repairStatus.getStatus(gadget2));

        // Add a new service record to the customer
        ServiceRecord<String> newRecord = new ServiceRecord<>("13/11/23", 237.60, "Screen repair");
        customer2.addRepairRecord(newRecord);

        // Update customer's repair history
        customer2.getRepairHistory().toArray(new ServiceRecord[0]);

        // Generate and send invoice
        Billing payment = new CreditCard();
        try {
            payment.setPaymentMethod("Check");
            payment.calculateCost(customer2, new ServiceRecord[]{newRecord});
            emailMethod.sendNotification(customer2, "Repair complete", "Your device is ready for pickup");

            // Process payment
            payment.processPayment(customer2);
        } catch (PaymentException e) {
            LOGGER.info("Failed to process payment: " + e.getMessage());

            // Customer offers alternative payment method
            Billing alternativePayment = new CreditCard();
            try {
                alternativePayment.setPaymentMethod("Credit Card");
                alternativePayment.calculateCost(customer2, new ServiceRecord[]{newRecord});
                emailMethod.sendNotification(customer2, "Repair complete", "Your device is ready for pickup");
                alternativePayment.processPayment(customer2);
            } catch (PaymentException ex) {
                LOGGER.info("Failed to process alternative payment: " + ex.getMessage());
            }
        }
    }
}