package com.solvd.gadgetrepair.human;

import com.solvd.gadgetrepair.devices.ServiceRecord;

import java.util.ArrayList;
import java.util.List;

public abstract class Person {
    protected String fullName;

    public Person(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public abstract String getInfo();

    @Override
    public String toString() {
        return getInfo();
    }

    // Represents information about the customer
    public static class Customer extends Person {
        private String email;
        private String phoneNumber;
        private String preferredContact;
        private List<ServiceRecord> repairHistory;

        public Customer(String fullName, String email, String phoneNumber, String preferredContact) {
            super(fullName);
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.preferredContact = preferredContact;
            this.repairHistory = new ArrayList<>();
        }

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
        public String getInfo() {
            return "Customer name: " + fullName + "\nEmail: " + email +
                    "\nPhone Number: " + phoneNumber + "\nPreferred Contact Method: " + preferredContact;
        }
    }

    // Represents the employee or technician working for the repair service
    public static class Employee extends Person {
        private String specialty;
        private String availability;

        public Employee(String fullName, String specialty, String availability) {
            super(fullName);
            this.specialty = specialty;
            this.availability = availability;
        }

        public String getSpecialty() {
            return specialty;
        }
        public void setSpecialty(String specialty) {
            this.specialty = specialty;
        }
        public String getAvailability() {
            return availability;
        }
        public void setAvailability(String availability) {
            this.availability = availability;
        }

        @Override
        public String getInfo() {
            return "Employee name: " + fullName + "\nSpecialty: " + specialty + "\nAvailability: " + availability;
        }
    }
}
