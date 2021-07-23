package com.galvanize.invoicify.controllers;

import com.galvanize.invoicify.models.*;
import com.galvanize.invoicify.repositories.BillingRecordRepository;
import com.galvanize.invoicify.repositories.CompanyRepository;
import com.galvanize.invoicify.repositories.InvoiceRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {
    private final InvoiceRepository invoiceRepository;
    private final CompanyRepository companyRepository;
    private final BillingRecordRepository billingRecordRepository;

    public InvoiceController(InvoiceRepository invoiceRepository, CompanyRepository companyRepository, BillingRecordRepository billingRecordRepository) {
        this.invoiceRepository = invoiceRepository;
        this.companyRepository = companyRepository;
        this.billingRecordRepository = billingRecordRepository;
    }

    @PostMapping("/{id}")
    public Invoice createInvoice(Authentication authentication, @PathVariable Long id, @RequestBody InvoiceView body) {
        User createdBy = (User) authentication.getPrincipal();
        List<BillingRecord> records = billingRecordRepository.findByIdIn(body.getRecordIds());
        Invoice invoice = new Invoice();
        invoice.setInvoiceDescription(body.getInvoiceDescription());
        List<InvoiceLineItem> bills = new ArrayList<>();
        Date date = new Date(System.currentTimeMillis());
        InvoiceLineItem invoiceLineItem = new InvoiceLineItem();
        for (BillingRecord record : records) {
            record.setInUse(true);
            invoiceLineItem.setBillingRecord(record);
            invoiceLineItem.setCreatedBy(createdBy);
            invoiceLineItem.setCreatedOn(date);
            invoiceLineItem.setInvoice(invoice);
            bills.add(invoiceLineItem);
        }
        invoice.setLineItems(bills);
        invoice.setCreatedBy(createdBy);
        invoice.setCreatedOn(date);
        invoice.setCompany(companyRepository.findById(id).get());
        return invoiceRepository.save(invoice);
    }

    @GetMapping()
    public Iterable<Invoice> listInvoices() {
        return this.invoiceRepository.findAll();
    }

}
