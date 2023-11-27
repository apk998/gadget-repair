package com.solvd.gadgetrepair.human;

// Represents the employee or technician working for the repair service
public class Employee extends Person {
    private String specialty;
    private String availability;

    public Employee() {
        super("");
        this.specialty = "";
        this.availability = "";
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
        return "Employee name: " + getFullName() + "\nSpecialty: " + specialty + "\nAvailability: " + availability;
    }
}