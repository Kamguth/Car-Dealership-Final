package com.pluralsight.CarDealership.controller;

import com.pluralsight.CarDealership.dao.SalesDao;
import com.pluralsight.CarDealership.model.SalesContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sales")
public class SalesContractsController {

    @Autowired
    private SalesDao salesDao;

    @PostMapping
    public void addSalesContract(@RequestBody SalesContract contract) {
        salesDao.save(contract);
    }

    // Optional if you implement byId lookup:
    @GetMapping("/{id}")
    public SalesContract getSalesContractById(@PathVariable int id) {
        return salesDao.getById(id); // you'll need to implement this
    }
}
