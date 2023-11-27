package com.solvd.gadgetrepair.devices;

import com.solvd.gadgetrepair.exceptions.ProblemException;

import java.util.HashMap;
import java.util.Map;

public class LaptopRepair extends RepairService implements Repairable {
    // Map to store part costs
    private static final Map<String, Double> LAPTOP_PART_COSTS = initializePartCosts();
    private String partNeeded;

    public LaptopRepair(Gadget gadget) {
        super(gadget);
        laptopParts(gadget);
    }

    private static Map<String, Double> initializePartCosts() {
        Map<String, Double> partCosts = new HashMap<>();
        partCosts.put("screen", 300.0);
        partCosts.put("hard drive", 50.0);
        partCosts.put("battery", 150.0);
        partCosts.put("keyboard", 125.0);
        partCosts.put("fan", 50.0);
        partCosts.put("labor only", 0.0);
        return partCosts;
    }

    public void laptopParts(Gadget gadget) throws ProblemException {
        String problem = gadget.getProblemDescription();
        String partNeeded;
        switch (problem) {
            case "cracked screen":
                partNeeded = "screen";
                break;
            case "hard drive failure":
                partNeeded = "hard drive";
                break;
            case "battery malfunction":
            case "charging problems":
                partNeeded = "battery";
                break;
            case "keyboard damage":
                partNeeded = "keyboard";
                break;
            case "overheating":
                partNeeded = "fan";
                break;
            case "connectivity problems":
            case "virus or malware":
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
            case "battery malfunction":
            case "charging problems":
                repairTime = 1;
                break;
            case "overheating":
            case "connectivity problems":
                repairTime = 2;
                break;
            case "keyboard damage":
            case "hard drive problems":
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
        if (LAPTOP_PART_COSTS.containsKey(partNeeded)) {
            partsCost = LAPTOP_PART_COSTS.get(partNeeded);
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