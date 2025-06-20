package com.pluralsight.CarDealership.dao;

import com.pluralsight.CarDealership.model.SalesContract;

public interface SalesDao {
    void save(SalesContract contract);
}