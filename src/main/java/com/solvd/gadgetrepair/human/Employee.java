package com.solvd.gadgetrepair.human;

// Represents the employee or technician working for the repair service
public class Employee {
    private String name;
    private String specialty;
    private String availability;

    public Employee(String name, String specialty, String availability) {
        this.name = name;
        this.specialty = specialty;
        this.availability = availability;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
    public String toString() {
        return "Employee name: " + name + "\nSpecialty: " + specialty + "\nAvailability: " + availability;
    }
}
