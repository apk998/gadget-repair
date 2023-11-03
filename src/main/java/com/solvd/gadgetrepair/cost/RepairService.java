package com.solvd.gadgetrepair.cost;

import com.solvd.gadgetrepair.devices.DeviceRepairInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

// Handles repair time and cost estimation based on device and problem
public class RepairService {
    private List<DeviceRepairInfo> supportedDevices;
    private Queue<DeviceRepairInfo> repairQueue;

    public RepairService() {
        supportedDevices = new ArrayList<>();
        supportedDevices.add(new DeviceRepairInfo.PhoneRepair("screen", 150.00));
        supportedDevices.add(new DeviceRepairInfo.LaptopRepair("screen", 300.00));
        supportedDevices.add(new DeviceRepairInfo.TVRepair("screen", 250.00));

        repairQueue = new LinkedList<>();
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
            summary.append(gadget.getClass().getSimpleName()).append(" - Cost: $").append(gadget.calculateRepairCost()).append("\n");
        }
        summary.append("Devices in Repair Queue: ").append(repairQueue.size()).append("\n");
        return summary.toString();
    }
}
