package com.solvd.gadgetrepair;

import com.solvd.gadgetrepair.cost.AcceptedPayments;
import com.solvd.gadgetrepair.cost.Billing;
import com.solvd.gadgetrepair.cost.CreditCard;
import com.solvd.gadgetrepair.devices.*;
import com.solvd.gadgetrepair.exceptions.*;
import com.solvd.gadgetrepair.human.AcceptedContact;
import com.solvd.gadgetrepair.human.Customer;
import com.solvd.gadgetrepair.human.Employee;
import com.solvd.gadgetrepair.status.Email;
import com.solvd.gadgetrepair.status.Notifiable;
import com.solvd.gadgetrepair.status.RepairStatus;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Main {
    private static final Logger LOGGER=LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        // Customers enter the store
        Customer customer = new Customer();
        customer.setFullName("Max Stirner");
        customer.setEmail("mstirner@example.com");
        customer.setPhoneNumber("215-555-9640");
        customer.setPreferredContact(AcceptedContact.EMAIL);

        // Customer presents gadget for repair
        IMenu chooseGadgetType = () -> {
            System.out.println("Choose a gadget type:");
            AcceptedGadgets[] gadgetTypes = AcceptedGadgets.values();
            for (int i = 0; i < gadgetTypes.length; i++) {
                System.out.println((i + 1) + ". " + gadgetTypes[i].getDisplayName());
            }

            int userChoice = getUserChoice(gadgetTypes.length);

            Gadget gadget = new Gadget();
            gadget.setGadgetType(gadgetTypes[userChoice - 1]);

            // Display the chosen gadget type
            System.out.println("Chosen Gadget Type: " + gadget.getGadgetType().getDisplayName());
        };
        chooseGadgetType.execute();

        Gadget gadget = new Gadget();
        gadget.setGadgetType(AcceptedGadgets.PHONE);
        gadget.setProblemDescription("cracked screen");
        LOGGER.info(customer.getFullName() + " brings in a " + gadget.getGadgetType().getDisplayName() + " with a " + gadget.getProblemDescription() + " for repair.");

        // Pull up customer record, enter preferred notification method
        ServiceRecord<String>[] repairRecords = new ServiceRecord[]{
                new ServiceRecord<>("15/2/22", 250.00, "Screen replacement"),
                new ServiceRecord<>("14/1/23", 100.00, "Battery replacement"),
                new ServiceRecord<>("22/10/23", 75.00, "Software update")
        };
        Notifiable emailMethod = new Email(null);

        // Update gadget repair status
        RepairStatus repairStatus = new RepairStatus();
        repairStatus.addToQueue(gadget);
        LOGGER.info("Gadget status is " + repairStatus.getStatus(gadget));

        // Find an employee
        Employee employee = new Employee();
        employee.setFullName("John Zerzan");
        employee.setSpecialty("Phone technician");
        employee.setAvailability("Available");
        LOGGER.info("Employee " + employee.getFullName() + " is " + employee.getAvailability());
        employee.setAvailability("Busy");
        LOGGER.info(employee.getFullName() + " is now " + employee.getAvailability());
        repairStatus.markUnderRepair(gadget);
        LOGGER.info("Gadget status is " + repairStatus.getStatus(gadget));

        RepairService repairService = (RepairService) RepairService.getRepairInfo(gadget);
        try {
            int phoneRepairTime = repairService.estimateRepairTime(gadget);
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
        repairStatus.markReady(gadget);
        LOGGER.info("Gadget status is " + repairStatus.getStatus(gadget));

        // Add a new service record to the customer
        ServiceRecord<String> newRecord = new ServiceRecord<>("13/11/23", 237.60, "Screen repair");
        customer.addRepairRecord(newRecord);

        // Update customer's repair history
        customer.getRepairHistory().toArray(new ServiceRecord[0]);

        // Generate and send invoice
        try {
            Billing billing = new CreditCard();
            billing.setPaymentMethod(AcceptedPayments.CREDIT_CARD);

            CreditCard creditCard = new CreditCard();
            creditCard.setCreditCardNumber("1234-5678-9101-1121");
            creditCard.setCreditLimit(1000.0);
            creditCard.processPayment(customer, repairService);
        } catch (PaymentException e) {
            LOGGER.info(e.getMessage() + "Try another payment method.");
        }
    }

    private static void displayMenu(String menuTitle, IMenu[] menuOptions) {
        System.out.println(menuTitle + ":");
        for (int i = 0; i < menuOptions.length; i++) {
            System.out.println((i + 1) + ". " + menuOptions[i].getClass().getSimpleName());
        }
    }

    private static int getUserChoice(int maxOption) {
        Scanner scanner = new Scanner(System.in);
        int userChoice = 0;

        do {
            System.out.print("Enter your choice (1-" + maxOption + "): ");
            try {
                userChoice = Integer.parseInt(scanner.nextLine());

                if (userChoice < 1 || userChoice > maxOption) {
                    System.out.println("Invalid choice. Please enter a number between 1 and " + maxOption + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        } while (userChoice < 1 || userChoice > maxOption);

        return userChoice;
    }
}