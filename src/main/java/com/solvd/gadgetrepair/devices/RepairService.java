package com.solvd.gadgetrepair.devices;

import com.solvd.gadgetrepair.exceptions.GadgetException;

// Stores information about repair costs for a particular gadget
public abstract class RepairService {
    private final Gadget gadget;
    public static final double LABOR_COST_PER_HOUR = 25.00;
    public static final double ADDITIONAL_FEES = 20.00;
    public static final double TAX_RATE = 1.08;

    public RepairService(Gadget gadget) {
        this.gadget = gadget;
    }

    public Gadget getGadget() {
        return gadget;
    }

    public static Repairable getRepairInfo(Gadget gadget) {
        AcceptedGadgets gadgetType = gadget.getGadgetType();
        switch (gadgetType) {
            case PHONE:
                return new PhoneRepair(gadget);
            case LAPTOP:
                return new LaptopRepair(gadget, new RepairCosts());
            case TV:
                return new TVRepair(gadget);
            default:
                throw new GadgetException("Unknown device");
        }
    }

    public abstract int estimateRepairTime(Gadget gadget);
    public abstract double calculatePartsCost(String partNeeded);
    public abstract String getPartNeeded();

    public double calculateRepairCost() {
        double laborCost = estimateRepairTime(gadget) * LABOR_COST_PER_HOUR;
        double partsCost = calculatePartsCost(getPartNeeded());
        double subtotal = laborCost + partsCost + ADDITIONAL_FEES;
        return subtotal * TAX_RATE;
    }
}