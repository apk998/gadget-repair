package com.solvd.gadgetrepair.cost;

public enum AcceptedPayments {
    CASH("cash"),
    CREDIT_CARD("credit card"),
    PAYPAL("PayPal");

    private final String displayName;
    AcceptedPayments(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}