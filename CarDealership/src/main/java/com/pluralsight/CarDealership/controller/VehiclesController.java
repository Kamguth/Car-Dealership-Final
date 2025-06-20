package com.pluralsight.CarDealership.controller;

import com.pluralsight.CarDealership.dao.VehicleDao;
import com.pluralsight.CarDealership.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehiclesController {

    @Autowired
    private VehicleDao vehicleDao;

    @GetMapping
    public List<Vehicle> getVehicles(
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String make,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) Integer minYear,
            @RequestParam(required = false) Integer maxYear,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) Integer minMiles,
            @RequestParam(required = false) Integer maxMiles,
            @RequestParam(required = false) String type
    ) {
        return vehicleDao.getAllVehicles();
    }

    @PostMapping
    public void addVehicle(@RequestBody Vehicle vehicle) {
        vehicleDao.addVehicle(vehicle);
    }

    @PutMapping("/{vin}")
    public void updateVehicle(@PathVariable String vin, @RequestBody Vehicle updated) {
        vehicleDao.updateVehicle(vin, updated);
    }

    @DeleteMapping("/{vin}")
    public void deleteVehicle(@PathVariable String vin) {
        vehicleDao.deleteVehicle(vin);
    }
}
