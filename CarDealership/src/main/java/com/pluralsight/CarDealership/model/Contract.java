package com.pluralsight.CarDealership.model;

import java.time.LocalDate;

public abstract class Contract {
    private String customerName;
    private String customerEmail;
    private LocalDate saleDate;
    private Vehicle vehicle;

    // Constructor
    public Contract(String date, String customerName, String customerEmail, Vehicle vehicle) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.saleDate = LocalDate.parse(date); // Converts date string to LocalDate
        this.vehicle = vehicle;
    }

    // Getters
    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    // Abstract methods for subclasses to implement
    public abstract double getTotalPrice();
    public abstract double getMonthlyPayment();
}
