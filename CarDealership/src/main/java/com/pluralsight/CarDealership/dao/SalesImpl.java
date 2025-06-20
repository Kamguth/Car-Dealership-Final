package com.pluralsight.CarDealership.dao;

import com.pluralsight.CarDealership.model.SalesContract;
import com.pluralsight.CarDealership.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class SalesImpl implements SalesDao {

    private final DataSource dataSource;

    @Autowired
    public SalesImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(SalesContract contract) {
        String sql = """
            INSERT INTO SalesContracts (
                vin, sale_date, customer_name, customer_email,
                year, make, model, type, color, mileage,
                price, sales_tax, fees, total_price, is_financed, monthly_payment
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)""";

        Vehicle vehicle = contract.getVehicle();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vehicle.getVin());
            stmt.setDate(2, Date.valueOf(contract.getSaleDate()));
            stmt.setString(3, contract.getCustomerName());
            stmt.setString(4, contract.getCustomerEmail());
            stmt.setInt(5, vehicle.getYear());
            stmt.setString(6, vehicle.getMake());
            stmt.setString(7, vehicle.getModel());
            stmt.setString(8, vehicle.getType());
            stmt.setString(9, vehicle.getColor());
            stmt.setInt(10, vehicle.getOdometer());
            stmt.setDouble(11, vehicle.getPrice());
            stmt.setDouble(12, contract.getSalesTax());
            stmt.setDouble(13, contract.getRecordingFee() + contract.getProcessingFee());
            stmt.setDouble(14, contract.getTotalPrice());
            stmt.setBoolean(15, contract.isFinance());
            stmt.setDouble(16, contract.getMonthlyPayment());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error saving sales contract: " + e.getMessage());
            e.printStackTrace();
        }
    }
}


