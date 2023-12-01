package com.solvd.gadgetrepair.devices;

public enum AcceptedGadgets {
    PHONE ("phone"),
    LAPTOP ("laptop"),
    TV ("TV");

    private final String displayName;

    AcceptedGadgets (String displayName) {
        this.displayName = displayName;
    }
}