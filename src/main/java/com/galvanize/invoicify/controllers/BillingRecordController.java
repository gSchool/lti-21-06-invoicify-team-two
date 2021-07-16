package com.galvanize.invoicify.controllers;

import com.galvanize.invoicify.models.BillingRecord;
import com.galvanize.invoicify.repositories.BillingRecordRepository;
import com.galvanize.invoicify.repositories.CompanyRepository;

import java.util.List;

public class BillingRecordController {
    private final BillingRecordRepository billingRecordRepository;


    public BillingRecordController(BillingRecordRepository billingRecordRepository){

        this.billingRecordRepository = billingRecordRepository;

    }

    public Iterable <BillingRecord> getBillingRecords() {
        return billingRecordRepository.findAll();
    }
}
