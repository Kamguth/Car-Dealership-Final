package com.pluralsight.CarDealership.model;


public class Vehicle {
    private String vin;
    private int year;
    private String make;
    private String model;
    private String type;
    private String color;
    private int odometer;
    private double price;

    // Full constructor with all fields
    public Vehicle(String vin, int year, String make, String model, String type, String color, int odometer, double price) {
        this.vin = vin;
        this.year = year;
        this.make = String.valueOf(make);
        this.model = model;
        this.type = String.valueOf(type);
        this.color = color;
        this.odometer = odometer;
        this.price = price;
    }

    public Vehicle(String vin, int year, String make, String model, String type, String color, int mileage, double price, boolean sold) {
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return vin + " | " + year + " | " + make + " | " + model + " | " + type + " | " + color + " | " + odometer + " | $" + price;
    }
}
