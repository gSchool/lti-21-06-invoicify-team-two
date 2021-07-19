package com.galvanize.invoicify.controllers;

import com.galvanize.invoicify.models.BillingRecord;
import com.galvanize.invoicify.models.Company;
import com.galvanize.invoicify.models.FlatFeeBillingRecord;
import com.galvanize.invoicify.models.User;
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
    public Iterable <BillingRecord> getBillingRecords() {
        return billingRecordRepository.findAll();
    }
    @GetMapping("/billing-record/{id}")
    public BillingRecord getBillingRecordsById(@PathVariable Long id) {
        return billingRecordRepository.findById(id).get();
    }
    @PostMapping("/billing-record/flat-fee/{id}")
    public FlatFeeBillingRecord  addFlatFeeBillingRecord(Authentication auth, @PathVariable Long id, @RequestBody FlatFeeBillingRecord body){
        //get currently logged in user
        User user = (User)auth.getPrincipal();
        //get a company by ID
        Company company = companyRepository.findById(id).get();
        //set the company for the billing record
        body.setClient(company);
        //set the user for the billing record
        body.setCreatedBy(user);
        //save the  billing record and return
        billingRecordRepository.save(body);
        return billingRecordRepository.save(body);

    }
}
