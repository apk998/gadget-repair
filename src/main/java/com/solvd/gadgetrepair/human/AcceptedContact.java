package com.solvd.gadgetrepair.human;

public enum AcceptedContact {
    EMAIL ("email"),
    SMS ("SMS");

    private final String displayName;

    AcceptedContact (String displayName) {
        this.displayName = displayName;
    }
}