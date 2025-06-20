package com.pluralsight.CarDealership.model;


public class LeaseContract extends Contract{
    private double price;
    private double expectedEndingValue;
    private double leaseFee;

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);

        this.price = vehicleSold.getPrice();
        this.expectedEndingValue = price * 0.5;
        this.leaseFee = price * 0.07;
    }

    public double getPrice() {
        return price;
    }

    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }




    @Override
    public double getTotalPrice() {
        return 0;
    }

    @Override
    public double getMonthlyPayment() {
        double interestRate = 0.04;
        return (getTotalPrice() * (1 + interestRate)) / 36 ;
    }
}
