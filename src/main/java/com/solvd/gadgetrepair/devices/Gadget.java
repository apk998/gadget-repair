package com.solvd.gadgetrepair.devices;

// Represents the gadgets brought in to be repaired
public class Gadget {
    private String gadgetType;
    private String serialNumber;
    private String problemDescription;

    public Gadget(String gadgetType, String serialNumber, String problemDescription) {
        this.gadgetType = gadgetType;
        this.serialNumber = serialNumber;
        this.problemDescription = problemDescription;
    }

    public String getGadgetType() {
        return gadgetType;
    }
    public void setGadgetType(String gadgetType) {
        this.gadgetType = gadgetType;
    }
    public String getSerialNumber() {
        return serialNumber;
    }
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    public String getProblemDescription() {
        return problemDescription;
    }
    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    @Override
    public String toString() {
        return "Gadget Type: " + gadgetType + "\nSerial Number: " + serialNumber + "\nProblem Description: " + problemDescription;
    }
}
