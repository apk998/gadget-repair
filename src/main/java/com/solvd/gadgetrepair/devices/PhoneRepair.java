package com.solvd.gadgetrepair.devices;

import com.solvd.gadgetrepair.exceptions.ProblemException;

import java.util.HashMap;
import java.util.Map;

public class PhoneRepair extends RepairService implements Repairable {
    // Map to store part costs
    private static final Map<String, Double> PHONE_PART_COSTS = initializePartCosts();
    private String partNeeded;

    public PhoneRepair(Gadget gadget) {
        super(gadget);
        phoneParts(gadget);
    }

    private static Map<String, Double> initializePartCosts() {
        Map<String, Double> partCosts = new HashMap<>();
        partCosts.put("screen", 150.0);
        partCosts.put("camera lens", 100.0);
        partCosts.put("battery", 50.0);
        partCosts.put("charging port", 50.0);
        partCosts.put("labor only", 0.0);
        return partCosts;
    }

    public void phoneParts(Gadget gadget) throws ProblemException {
        String problem = gadget.getProblemDescription();
        String partNeeded;
        switch (problem) {
            case "cracked screen":
                partNeeded = "screen";
                break;
            case "broken camera":
                partNeeded = "camera lens";
                break;
            case "battery malfunction":
                partNeeded = "battery";
                break;
            case "charging problems":
                partNeeded = "charging port";
                break;
            case "connectivity problems":
            case "virus or malware":
            case "nonworking buttons":
                partNeeded = "labor only";
                break;
            default:
                throw new ProblemException("We cannot fix the gadget");
        }
        this.partNeeded = partNeeded;
    }

    @Override
    public int estimateRepairTime(Gadget gadget) throws ProblemException {
        String problem = gadget.getProblemDescription();
        int repairTime;   // in hours
        switch (problem) {
            case "cracked screen":
            case "nonworking buttons":
                repairTime = 1;
                break;
            case "charging problems":
            case "connectivity problems":
                repairTime = 2;
                break;
            case "broken camera":
            case "battery malfunction":
            case "virus or malware":
                repairTime = 3;
                break;
            default:
                throw new ProblemException("We cannot fix the gadget");
        }
        return repairTime;
    }

    @Override
    public double calculatePartsCost(String partNeeded) throws ProblemException {
        double partsCost;
        if (PHONE_PART_COSTS.containsKey(partNeeded)) {
            partsCost = PHONE_PART_COSTS.get(partNeeded);
        } else {
            throw new ProblemException("Unsupported part: " + partNeeded);
        }
        return partsCost;
    }

    @Override
    public String getPartNeeded() {
        return partNeeded;
    }
}