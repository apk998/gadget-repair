package com.solvd.gadgetrepair.human;

@SuppressWarnings("unused")
public enum AcceptedContact {
    EMAIL ("email"),
    SMS ("SMS");

    private final String displayName;

    AcceptedContact (String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}