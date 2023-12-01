package com.solvd.gadgetrepair.devices;

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
}