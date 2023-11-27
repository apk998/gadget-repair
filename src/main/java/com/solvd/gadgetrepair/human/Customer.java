package com.solvd.gadgetrepair.human;

import com.solvd.gadgetrepair.devices.ServiceRecord;

import java.util.ArrayList;
import java.util.List;

// Represents information about the customer
public class Customer extends Person {
    private String email;
    private String phoneNumber;
    private String preferredContact;
    private final List<ServiceRecord<String>> repairHistory;

    public Customer() {
        super("");
        this.email = "";
        this.phoneNumber = "";
        this.preferredContact = "";
        this.repairHistory = new ArrayList<>();
    }

    public String getFullName() {
        return super.getFullName();
    }
    public void setFullName(String fullName) {
        super.setFullName(fullName);
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getPreferredContact() {
        return preferredContact;
    }
    public void setPreferredContact(String preferredContact) {
        this.preferredContact = preferredContact;
    }
    public List<ServiceRecord<String>> getRepairHistory() {
        return repairHistory;
    }
    public void addRepairRecord(ServiceRecord<String> serviceRecord) {
        repairHistory.add(serviceRecord);
    }

    @Override
    public String getInfo() {
        return "Customer name: " + getFullName() + "\nEmail: " + email +
                "\nPhone Number: " + phoneNumber + "\nPreferred Contact Method: " + preferredContact;
    }
}