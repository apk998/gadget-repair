package com.solvd.gadgetrepair.devices;

// Represents the gadgets brought in to be repaired
public class Gadget {
    private AcceptedGadgets gadgetType;
    private String problemDescription;

    public Gadget() {
    }

    public AcceptedGadgets getGadgetType() {
        return gadgetType;
    }
    public void setGadgetType(AcceptedGadgets gadgetType) {
        this.gadgetType = gadgetType;
    }
    public String getProblemDescription() {
        return problemDescription;
    }
    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    @Override
    public String toString() {
        return "Gadget Type: " + gadgetType + "\nProblem Description: " + problemDescription;
    }
}