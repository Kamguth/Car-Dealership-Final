package com.pluralsight.CarDealership.model;

public class SalesContract extends Contract{
    private boolean finance;
    private double salesTax;
    private double recordingFee;
    private double processingFee;
    private double price;

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);
        this.finance = finance;
        this.price = vehicleSold.getPrice();
        this.salesTax = price * 0.05;
        this.recordingFee = 100;
        this.processingFee =(price < 10000) ? 295 : 495; //Ternary operator
    }

    public boolean isFinance() {
        return finance;
    }
    public void setFinance(boolean finance) {
        this.finance = finance;
    }

    public double getSalesTax() {
        return salesTax;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public double getTotalPrice() {
        return price + salesTax + recordingFee + processingFee;
    }

    @Override
    public double getMonthlyPayment() {
        if (!finance) return 0;
        double totalPrice = getTotalPrice();
        int months;
        double rate;
        if (price >= 10000) {
            months = 48;
            rate = 0.0425;
        }
        else {
            months = 24;
            rate = 0.0525;
        }
        return  totalPrice * (1 + rate) / months;
    }
}
