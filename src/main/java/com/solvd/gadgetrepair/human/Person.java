package com.solvd.gadgetrepair.human;

public abstract class Person {
    private String fullName;

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
}