package com.pluralsight.CarDealership.dao;

import com.pluralsight.CarDealership.model.LeaseContract;
import com.pluralsight.CarDealership.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class LeaseImpl implements LeaseDao {

    private final DataSource dataSource;

    @Autowired
    public LeaseImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(LeaseContract contract) {
        String sql = """
            INSERT INTO LeaseContracts (
                vin, lease_date, customer_name, customer_email,
                year, make, model, type, color,
                price, expected_value, lease_fee, total_lease_price, monthly_payment
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)""";

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
            stmt.setDouble(10, vehicle.getPrice());
            stmt.setDouble(11, contract.getExpectedEndingValue());
            stmt.setDouble(12, contract.getLeaseFee());
            stmt.setDouble(13, contract.getTotalPrice());
            stmt.setDouble(14, contract.getMonthlyPayment());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error saving lease contract: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
