package com.pluralsight.CarDealership.dao;

import com.pluralsight.CarDealership.model.LeaseContract;

public interface LeaseDao {
    void save(LeaseContract contract);
}