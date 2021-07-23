package com.galvanize.invoicify.controllers;

import com.galvanize.invoicify.models.*;
import com.galvanize.invoicify.repositories.BillingRecordRepository;
import com.galvanize.invoicify.repositories.CompanyRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/billing-record")
public class BillingRecordController {
    private final BillingRecordRepository billingRecordRepository;
    private final CompanyRepository companyRepository;

    public BillingRecordController(BillingRecordRepository billingRecordRepository, CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
        this.billingRecordRepository = billingRecordRepository;
    }

    @GetMapping()
    public Iterable<BillingRecord> getBillingRecords(Authentication auth) {
        return billingRecordRepository.findAll();
    }

    @GetMapping("/{id}")
    public Iterable<BillingRecord> getBillingRecordsById(Authentication auth, @PathVariable Long id) {
        Company company = companyRepository.findById(id).get();
        return billingRecordRepository.findAllByClient(company);
    }

    @PostMapping("/flat-fee/{id}")
    public FlatFeeBillingRecord addFlatFeeBillingRecord(Authentication auth, @PathVariable Long id, @RequestBody FlatFeeBillingRecord body) {
        //get currently logged in user
        User user = (User) auth.getPrincipal();
        //get a company by ID
        Company company = companyRepository.findById(id).get();
        if (company != null) {
            //set the company for the billing record
            body.setClient(company);
            //set the user for the billing record
            body.setCreatedBy(user);

            //save the  billing record and return
            return billingRecordRepository.save(body);
        }
        return null;
    }

    @PostMapping("/rate-based/{id}")
    public RateBasedBillingRecord addRateBasedBillingRecord(Authentication auth, @PathVariable Long id, @RequestBody RateBasedBillingRecord body) {
        //get currently logged in user
        User user = (User) auth.getPrincipal();
        //get a company by ID
        Company company = companyRepository.findById(id).get();
        if (company != null) {
            //set the company for the billing record
            body.setClient(company);
            //set the user for the billing record
            body.setCreatedBy(user);
            //save the  billing record and return
            return billingRecordRepository.save(body);
        }
        return null;
    }
}