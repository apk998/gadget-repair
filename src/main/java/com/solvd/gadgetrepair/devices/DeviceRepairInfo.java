package com.solvd.gadgetrepair.devices;

// Stores information about repair costs and time estimates for a particular gadget
public abstract class DeviceRepairInfo {
    protected String deviceType;
    protected String partsNeeded;
    protected double repairCost;   // cost of repair in dollars
    protected int repairTime;      // estimate of repair time in hours
    protected double laborCostPerHour;
    protected double sparePartsCost;
    protected double additionalFees;

    public DeviceRepairInfo(String deviceType, String partsNeeded, double repairCost, int repairTime,
                            double sparePartsCost, double laborCostPerHour, double additionalFees) {
        this.deviceType = deviceType;
        this.partsNeeded = partsNeeded;
        this.repairCost = repairCost;
        this.repairTime = repairTime;
        this.sparePartsCost = sparePartsCost;
        laborCostPerHour = 25.00;                 // example wage
        additionalFees = 20.00;                   // could include overhead
    }

    public abstract int estimateRepairTime();
    public double calculatePartsCost(String partsNeeded) {
        return 0.0;
    }
    public abstract double calculateRepairCost();

    public String getDeviceType () {
        return deviceType;
    }
    public void setDeviceType (String deviceType) {
        this.deviceType = deviceType;
    }
    public String getPartsNeeded () {
        return partsNeeded;
    }
    public void setPartsNeeded (String partsNeeded) {
        this.partsNeeded = partsNeeded;
    }
    public double getRepairCost () {
        return repairCost;
    }
    public void setRepairCost (double repairCost) {
        this.repairCost = repairCost;
    }
    public int getRepairTime () {
        return repairTime;
    }
    public void setRepairTime (int repairTime) {
        this.repairTime = repairTime;
    }
    public double getSparePartsCost () {
        return sparePartsCost;
    }
    public void setSparePartsCost (double sparePartsCost) {
        this.sparePartsCost = sparePartsCost;
    }

    public static class PhoneRepair extends DeviceRepairInfo {
        public PhoneRepair(String partsNeeded, double sparePartsCost) {
            super("Phone", partsNeeded, 0.0, 0, sparePartsCost, 25.00, 20.00);
        }

        @Override
        public int estimateRepairTime() {
            // Assume a fixed time estimate of 2 hours for phone repair for all causes
            return 2;
        }

        @Override
        public double calculatePartsCost(String partsNeeded) {
            if ("screen".equalsIgnoreCase(partsNeeded)) {
                return 150.00;
            } else if ("battery".equalsIgnoreCase(partsNeeded)) {
                return 50.00;
            } else if ("camera lens".equalsIgnoreCase(partsNeeded)) {
                return 100.00;
            } else {
                return 0.00; // part is not recognized or supported
            }
        }

        @Override
        public double calculateRepairCost() {
            int repairTime = estimateRepairTime();
            double partsCost = calculatePartsCost(partsNeeded);
            double laborCost = 25.00 * repairTime;
            double totalCost = laborCost + partsCost + 20.00;
            return totalCost;
        }
    }

    public static class LaptopRepair extends DeviceRepairInfo {
        public LaptopRepair(String partsNeeded, double sparePartsCost) {
            super("Laptop", partsNeeded, 0.0, 0, sparePartsCost, 25.00, 20.00);
        }

        @Override
        public int estimateRepairTime() {
            // Assume a fixed time estimate of 4 hours for laptop repair for all causes
            return 4;
        }

        @Override
        public double calculatePartsCost(String partsNeeded) {
            if ("screen".equalsIgnoreCase(partsNeeded)) {
                return 300.00;
            } else if ("battery".equalsIgnoreCase(partsNeeded)) {
                return 150.00;
            } else if ("camera lens".equalsIgnoreCase(partsNeeded)) {
                return 50.00;
            } else if ("keyboard".equalsIgnoreCase(partsNeeded)) {
                return 125.00;
            } else {
                return 0.00; // part is not recognized or supported
            }
        }

        @Override
        public double calculateRepairCost() {
            int repairTime = estimateRepairTime();
            double partsCost = calculatePartsCost(partsNeeded);
            double laborCost = 25.00 * repairTime;
            double totalCost = laborCost + partsCost + 20.00;
            return totalCost;
        }
    }

    public static class TVRepair extends DeviceRepairInfo {
        public TVRepair(String partsNeeded, double sparePartsCost) {
            super("TV", partsNeeded, 0.0, 0, sparePartsCost, 25.00, 20.00);
        }

        @Override
        public int estimateRepairTime() {
            // Assume a fixed time estimate of 3 hours for TV repair for all causes
            return 3;
        }

        @Override
        public double calculatePartsCost(String partsNeeded) {
            if ("screen".equalsIgnoreCase(partsNeeded)) {
                return 250.00;
            } else if ("LED backlights".equalsIgnoreCase(partsNeeded)) {
                return 150.00;
            } else if ("capacitors".equalsIgnoreCase(partsNeeded)) {
                return 50.00;
            } else {
                return 0.00; // part is not recognized or supported
            }
        }

        @Override
        public double calculateRepairCost() {
            int repairTime = estimateRepairTime();
            double partsCost = calculatePartsCost(partsNeeded);
            double laborCost = 25.00 * repairTime;
            double totalCost = laborCost + partsCost + 20.00;
            return totalCost;
        }
    }
}
