package com.solvd.gadgetrepair.status;

import com.solvd.gadgetrepair.devices.Gadget;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class RepairStatus {
    private final Map<Gadget, String> status;
    private final Queue<Gadget> inQueue;
    private final Queue<Gadget> underRepair;
    private final Queue<Gadget> ready;

    public RepairStatus() {
        status = new HashMap<>();
        inQueue = new LinkedList<>();
        underRepair = new LinkedList<>();
        ready = new LinkedList<>();
    }

    public void addToQueue(Gadget gadget) {
        status.put(gadget, "In Queue");
        inQueue.add(gadget);
    }

    public void markUnderRepair(Gadget gadget) {
        status.put(gadget, "Under Repair");
        underRepair.add(gadget);
    }

    public void markReady(Gadget gadget) {
        status.put(gadget, "Ready for Pickup");
        ready.add(gadget);
    }

    public String getStatus(Gadget gadget) {
        return status.get(gadget);
    }

    public Queue<Gadget> getInQueue() {
        return inQueue;
    }

    public Queue<Gadget> getUnderRepair() {
        return underRepair;
    }

    public Queue<Gadget> getReady() {
        return ready;
    }
}