package com.galvanize.invoicify.controllers;

import com.galvanize.invoicify.models.*;
import com.galvanize.invoicify.repositories.BillingRecordRepository;
import com.galvanize.invoicify.repositories.CompanyRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BillingRecordController {
    private final BillingRecordRepository billingRecordRepository;
    private final CompanyRepository companyRepository;

    public BillingRecordController(BillingRecordRepository billingRecordRepository, CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
        this.billingRecordRepository = billingRecordRepository;

    }
    @GetMapping("/billing-record")
    public Iterable <BillingRecord> getBillingRecords(Authentication auth) {
        if (auth.isAuthenticated())
            return billingRecordRepository.findAll();
        return null;
    }


    @GetMapping("/billing-record/{id}")
    public BillingRecord getBillingRecordsById(Authentication auth, @PathVariable Long id) {
        if (auth.isAuthenticated())
            return billingRecordRepository.findById(id).get();
        return null;
    }

    @PostMapping("/billing-record/flat-fee/{id}")
    public FlatFeeBillingRecord  addFlatFeeBillingRecord(Authentication auth, @PathVariable Long id, @RequestBody FlatFeeBillingRecord body){
        //get currently logged in user
        User user = (User)auth.getPrincipal();
//        if (auth.isAuthenticated()) {
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
//        }
        return null;
    }

    @PostMapping("/billing-record/rate-based/{id}")
    public RateBasedBillingRecord  addRateBasedBillingRecord(Authentication auth, @PathVariable Long id, @RequestBody RateBasedBillingRecord body){
        //get currently logged in user
        User user = (User)auth.getPrincipal();
        if (auth.isAuthenticated()) {
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
        }
        return null;
    }
}
