package com.solvd.gadgetrepair.human;

import com.solvd.gadgetrepair.devices.ServiceRecord;

import java.util.ArrayList;
import java.util.List;

// Represents information about the customer
public class Customer {
    private String fullName;
    private String email;
    private String phoneNumber;
    private String preferredContact;
    private List<ServiceRecord> repairHistory;

    public Customer(String fullName, String email, String phoneNumber, String preferredContact) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.preferredContact = preferredContact;
        this.repairHistory = new ArrayList<>();
    } // include no argument constructor?

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
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
    public List<ServiceRecord> getRepairHistory() {
        return repairHistory;
    }
    public void addRepairRecord(ServiceRecord serviceRecord) {
        repairHistory.add(serviceRecord);
    }

    @Override
    public String toString() {
        return "Customer name: " + fullName + "\nEmail: " + email +
                "\nPhone Number: " + phoneNumber + "\nPreferred Contact Method: " + preferredContact;
    }
}
