package com.pluralsight.CarDealership.dao;

import com.pluralsight.CarDealership.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class VehicleImpl implements com.pluralsight.CarDealership.dao.VehicleDao {

    private final DataSource dataSource;

    @Autowired
    public VehicleImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM Vehicles";

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                vehicles.add(mapRowToVehicle(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicles;
    }

    @Override
    public Vehicle getVehicleByVin(String vin) {
        String sql = "SELECT * FROM Vehicles WHERE VIN = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vin);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapRowToVehicle(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Vehicle> getVehiclesByDealershipId(int dealershipId) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT v.* FROM Vehicles v JOIN Inventory i ON v.VIN = i.VIN WHERE i.dealership_id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, dealershipId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                vehicles.add(mapRowToVehicle(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicles;
    }

    @Override
    public void markVehicleAsSold(String vin) {
        String sql = "UPDATE Vehicles SET sold = true WHERE VIN = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vin);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Vehicle mapRowToVehicle(ResultSet rs) throws SQLException {
        return new Vehicle(
                rs.getString("VIN"),
                rs.getInt("year"),
                rs.getString("make"),
                rs.getString("model"),
                rs.getString("type"),
                rs.getString("color"),
                rs.getInt("mileage"),
                rs.getDouble("price"),
                rs.getBoolean("sold")
        );
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        String sql = "INSERT INTO Vehicles (VIN, year, make, model, type, color, mileage, price, sold) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vehicle.getVin());
            stmt.setInt(2, vehicle.getYear());
            stmt.setString(3, vehicle.getMake());
            stmt.setString(4, vehicle.getModel());
            stmt.setString(5, vehicle.getType());
            stmt.setString(6, vehicle.getColor());
            stmt.setInt(7, vehicle.getOdometer());
            stmt.setDouble(8, vehicle.getPrice());
            stmt.setBoolean(9, false); // New vehicles default to unsold

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateVehicle(String vin, Vehicle updated) {
        String sql = "UPDATE Vehicles SET year = ?, make = ?, model = ?, type = ?, color = ?, mileage = ?, price = ? WHERE VIN = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, updated.getYear());
            stmt.setString(2, updated.getMake());
            stmt.setString(3, updated.getModel());
            stmt.setString(4, updated.getType());
            stmt.setString(5, updated.getColor());
            stmt.setInt(6, updated.getOdometer());
            stmt.setDouble(7, updated.getPrice());
            stmt.setString(8, vin);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteVehicle(String vin) {
        String sql = "DELETE FROM Vehicles WHERE VIN = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vin);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}

