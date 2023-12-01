package com.solvd.gadgetrepair.devices;

import com.solvd.gadgetrepair.exceptions.ProblemException;

public class LaptopRepair extends RepairService implements Repairable {
    private final RepairCosts repairCosts;
    private String partNeeded;

    public LaptopRepair(Gadget gadget, RepairCosts repairCosts) {
        super(gadget);
        this.repairCosts = repairCosts;
    }

    @Override
    public int estimateRepairTime(Gadget gadget) {
        String problem = gadget.getProblemDescription();
        return repairCosts.getTimeEstimate(problem);
    }

    @Override
    public double calculatePartsCost(String partNeeded) {
        return repairCosts.getPartCost(partNeeded);
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

    private String determinePartNeeded(String problem) throws ProblemException {
        switch (problem) {
            case "cracked screen":
                return "screen";
            case "hard drive failure":
                return "hard drive";
            case "battery malfunction":
            case "charging problems":
                return "battery";
            case "keyboard damage":
                return "keyboard";
            case "overheating":
                return "fan";
            case "connectivity problems":
            case "virus or malware":
                return "labor only";
            default:
                throw new ProblemException("Unsupported problem: " + problem);
        }
    }
}