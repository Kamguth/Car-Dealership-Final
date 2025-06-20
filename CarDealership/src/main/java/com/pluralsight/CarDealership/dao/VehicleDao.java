package com.pluralsight.CarDealership.dao;

import com.pluralsight.CarDealership.model.Vehicle;

import java.util.List;

public interface VehicleDao {
    List<Vehicle> getAllVehicles();
    Vehicle getVehicleByVin(String vin);
    List<Vehicle> getVehiclesByDealershipId(int dealershipId);
    void markVehicleAsSold(String vin);

    void addVehicle(Vehicle vehicle);

    void updateVehicle(String vin, Vehicle updated);

    void deleteVehicle(String vin);
}
