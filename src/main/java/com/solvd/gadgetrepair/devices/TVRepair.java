package com.solvd.gadgetrepair.devices;

import com.solvd.gadgetrepair.exceptions.ProblemException;

import java.util.HashMap;
import java.util.Map;

public class TVRepair extends RepairService implements Repairable {
    // Map to store part costs
    private static final Map<String, Double> TV_PART_COSTS = initializePartCosts();
    private String partNeeded;

    public TVRepair(Gadget gadget) {
        super(gadget);
        tvParts(gadget);
    }

    private static Map<String, Double> initializePartCosts() {
        Map<String, Double> partCosts = new HashMap<>();
        partCosts.put("screen", 250.0);
        partCosts.put("LED backlights", 150.0);
        partCosts.put("capacitors", 50.0);
        partCosts.put("internal speakers", 50.0);
        partCosts.put("labor only", 0.0);
        return partCosts;
    }

    public void tvParts(Gadget gadget) throws ProblemException {
        String problem = gadget.getProblemDescription();
        String partNeeded;
        switch (problem) {
            case "cracked screen":
                partNeeded = "screen";
                break;
            case "damaged speakers":
            case "echo":
                partNeeded = "internal speakers";
                break;
            case "poor picture quality":
                partNeeded = "LED backlights";
                break;
            case "no picture":
                partNeeded = "capacitors";
                break;
            case "color calibration":
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
            case "color calibration":
            case "nonworking buttons":
                repairTime = 1;
                break;
            case "poor picture quality":
            case "cracked screen":
                repairTime = 2;
                break;
            case "no picture":
            case "echo":
            case "damaged speakers":
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
        if (TV_PART_COSTS.containsKey(partNeeded)) {
            partsCost = TV_PART_COSTS.get(partNeeded);
        } else {
            throw new ProblemException("Unsupported part: " + partNeeded);
        }
        return partsCost;
    }

    @Override
    public double calculateRepairCost() {
        double laborCost = estimateRepairTime(getGadget()) * LABOR_COST_PER_HOUR;
        double subtotal = laborCost + calculatePartsCost(partNeeded) + ADDITIONAL_FEES;
        return subtotal * TAX_RATE;
    }

    @Override
    public String getPartNeeded() {
        return partNeeded;
    }
}