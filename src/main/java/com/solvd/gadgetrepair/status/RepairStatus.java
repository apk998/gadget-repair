package com.solvd.gadgetrepair.status;

// Tracks the status of each gadget in the repair service
import com.solvd.gadgetrepair.devices.Gadget;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class RepairStatus {
    private Map<Gadget, String> status;   // Map to associate gadgets with their status
    private List<Gadget> inQueue;         // List of gadgets in the repair queue
    private List<Gadget> underRepair;     // List of gadgets currently under repair
    private List<Gadget> ready;           // List of gadgets ready for pickup

    public RepairStatus() {
        status = new HashMap<>();
        inQueue = new ArrayList<>();
        underRepair = new ArrayList<>();
        ready = new ArrayList<>();
    }

    // Add a gadget to the repair queue
    public void addToQueue(Gadget gadget) {
        status.put(gadget, "In Queue");
        inQueue.add(gadget);
    }

    // Mark a gadget as under repair
    public void markUnderRepair(Gadget gadget) {
        status.put(gadget, "Under Repair");
        underRepair.add(gadget);
    }

    // Mark a gadget as ready for pickup
    public void markReady(Gadget gadget) {
        status.put(gadget, "Ready for Pickup");
        ready.add(gadget);
    }

    // Get the status of a specific gadget
    public String getStatus(Gadget gadget) {
        return status.get(gadget);
    }

    // Get the list of gadgets in the repair queue
    public List<Gadget> getInQueue() {
        return inQueue;
    }

    // Get the list of gadgets under repair
    public List<Gadget> getUnderRepair() {
        return underRepair;
    }

    // Get the list of gadgets ready for pickup
    public List<Gadget> getReady() {
        return ready;
    }
}
