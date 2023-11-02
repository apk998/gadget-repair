package com.solvd.gadgetrepair;

import com.solvd.gadgetrepair.cost.Payment;
import com.solvd.gadgetrepair.cost.RepairService;
import com.solvd.gadgetrepair.devices.DeviceRepairInfo;
import com.solvd.gadgetrepair.devices.Gadget;
import com.solvd.gadgetrepair.devices.Inventory;
import com.solvd.gadgetrepair.devices.ServiceRecord;
import com.solvd.gadgetrepair.human.Customer;
import com.solvd.gadgetrepair.human.Employee;
import com.solvd.gadgetrepair.status.Notification;
import com.solvd.gadgetrepair.status.RepairStatus;

public class Main {
    public static void main (String[] args) {
        // Create and initialize objects, can customize
        Customer customer = new Customer("Max Stirner", "mstirner@example.com", "215-555-9640", "email");
        Gadget gadget = new Gadget("Phone", "SN123456", "Camera lens cracked");
        DeviceRepairInfo repairInfo = new DeviceRepairInfo("Phone", "screen", 100.00, 2);
        ServiceRecord[] repairRecords = {
                new ServiceRecord("15/2/22", 250.0, "Screen replacement"),
                new ServiceRecord("14/1/23", 100.0, "Battery replacement"),
                new ServiceRecord("22/10/23", 75.00, "Software update")
        };
        Notification notification = new Notification(customer, "Repair complete", "Your device is ready for pickup");
        Payment payment = new Payment();


        // Customer brings in a new device for repair
        System.out.println(customer.getFullName() + " brings in a " + gadget.getGadgetType() + " with a " + gadget.getProblemDescription() + " for repair.");
        RepairService repairService = new RepairService();

        // Update gadget repair status
        RepairStatus repairStatus = new RepairStatus();
        repairStatus.addToQueue(gadget);
        System.out.println("Gadget status is " + repairStatus.getStatus(gadget));

        // Find an employee
        Employee employee = new Employee("John Zerzan", "Phone technician", "Available");
        System.out.println("Employee " + employee.getName() + " is " + employee.getAvailability());
        employee.setAvailability("Busy");
        System.out.println(employee.getName() + " is now " + employee.getAvailability());
        repairStatus.markUnderRepair(gadget);
        System.out.println("Gadget status is " + repairStatus.getStatus(gadget));

        // Estimate time and cost of repair
        int estimatedTime = repairService.estimateRepairTime(repairInfo);
        if (estimatedTime != -1) {
            System.out.println("Estimated Repair Time for " + repairInfo.getGadgetType() + ": " + estimatedTime + " hours");
        } else {
            System.out.println("Device type not supported for repair.");
        }

        double cost = repairService.calculateRepairCost(repairInfo);
        if (cost != -1.0) {
            System.out.println("Calculated Repair Cost for " + repairInfo.getGadgetType() + ": $" + cost);
        } else {
            System.out.println("Device type not supported for repair.");
        }

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
        ServiceRecord newRecord = new ServiceRecord("30/10/23", 160.0, "Screen repair");
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
