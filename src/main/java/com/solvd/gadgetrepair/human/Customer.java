package com.solvd.gadgetrepair.human;

import com.solvd.gadgetrepair.devices.ServiceRecord;

import java.util.ArrayList;
import java.util.List;

// Represents information about the customer
public class Customer extends Person {
    private String email;
    private String phoneNumber;
    private AcceptedContact preferredContact;
    private final List<ServiceRecord<String>> repairHistory = new ArrayList<>();

    public Customer(String fullName) {
        super(fullName);
    }

    public String getFullName() {
        return super.getFullName();
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
    public AcceptedContact getPreferredContact() {
        return preferredContact;
    }
    public void setPreferredContact(AcceptedContact preferredContact) {
        this.preferredContact = preferredContact;
    }
    public List<ServiceRecord<String>> getRepairHistory() {
        return repairHistory;
    }
    public void addRepairRecord(ServiceRecord<String> serviceRecord) {
        if (serviceRecord != null) {
            repairHistory.add(serviceRecord);
        }
    }

    @Override
    public String getInfo() {
        return "Customer name: " + getFullName() + "\nEmail: " + email +
                "\nPhone Number: " + phoneNumber + "\nPreferred Contact Method: " + preferredContact;
    }
}