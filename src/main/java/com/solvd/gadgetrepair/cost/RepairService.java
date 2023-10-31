package com.solvd.gadgetrepair.cost;

import com.solvd.gadgetrepair.devices.DeviceRepairInfo;
import com.solvd.gadgetrepair.devices.Gadget;

// Handles repair time and cost estimation based on device and problem
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Queue;
    import java.util.LinkedList;

public class RepairService {
    private List<DeviceRepairInfo> supportedDevices;
    private double laborCostPerHour;
    private double sparePartsCost;
    private double additionalFees;
    private Queue<DeviceRepairInfo> repairQueue;

    public RepairService() {
        supportedDevices = new ArrayList<>();
        supportedDevices.add(new DeviceRepairInfo("phone", 50.00, 2)); // just examples
        supportedDevices.add(new DeviceRepairInfo("laptop", 100.00, 4));
        supportedDevices.add(new DeviceRepairInfo("TV", 75.00, 3));
        laborCostPerHour = 25.00; // example wage
        sparePartsCost = 0.00;
        additionalFees = 10.00; // could include overhead
        repairQueue = new LinkedList<DeviceRepairInfo>();
    }

    // To calculate repair time for a particular gadget
    public int estimateRepairTime(DeviceRepairInfo gadgetType) {
        for (DeviceRepairInfo gadget : supportedDevices) {
            if (gadget.getGadgetType().equalsIgnoreCase(gadgetType.getGadgetType())) {
                return gadget.getRepairTime();
            }
        }
        return -1; // if the gadget type is not supported
    }

    // To calculate repair cost for a particular gadget
    public double calculateRepairCost(DeviceRepairInfo gadget) {
        String gadgetType = gadget.getGadgetType();
        for (DeviceRepairInfo supportedDevice : supportedDevices) {
            if (supportedDevice.getGadgetType().equalsIgnoreCase(gadgetType)) {
                double laborCost = laborCostPerHour * supportedDevice.getRepairTime();
                double totalCost = laborCost + sparePartsCost + additionalFees;
                return totalCost;
            }
        }
        return -1.0; // if the device type is not supported
    }

    // Add a gadget to the queue
    public void addToQueue(DeviceRepairInfo gadget) {
        repairQueue.add(gadget);
    }
    // Mark a gadget as repaired
    public void markRepaired(DeviceRepairInfo gadget) {
        repairQueue.remove(gadget);
    }

    @Override
    public String toString() {
        StringBuilder summary = new StringBuilder("Repair Service Information:\n");
        summary.append("Supported Devices:\n");
        for (DeviceRepairInfo gadget : supportedDevices) {
            summary.append(gadget.getGadgetType()).append(" - Cost: $").append(gadget.getRepairCost())
                    .append(", Time Estimate: ").append(gadget.getRepairTime()).append("hours\n");
        }
        summary.append("Labor Cost per Hour: $").append(laborCostPerHour).append("\n");
        summary.append("Spare Parts Cost: $").append(sparePartsCost).append("\n");
        summary.append("Additional Fees: $").append(additionalFees).append("\n");
        summary.append("Devices in Repair Queue: ").append(repairQueue.size()).append("\n");
        return summary.toString();
    }
}