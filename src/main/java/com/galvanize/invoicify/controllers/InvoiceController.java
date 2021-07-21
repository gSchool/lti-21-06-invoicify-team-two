package com.galvanize.invoicify.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.invoicify.models.*;
import com.galvanize.invoicify.repositories.BillingRecordRepository;
import com.galvanize.invoicify.repositories.InvoiceLineItemRepository;
import com.galvanize.invoicify.repositories.InvoiceRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@RestController
public class InvoiceController {
    private final InvoiceRepository invoiceRepository;
    private final BillingRecordRepository billingRecordRepository;
    private final InvoiceLineItemRepository invoiceLineItemRepository;
    public InvoiceController(InvoiceRepository invoiceRepository, BillingRecordRepository billingRecordRepository, InvoiceLineItemRepository invoiceLineItemRepository){
        this.invoiceRepository=invoiceRepository;
        this.billingRecordRepository = billingRecordRepository;
        this.invoiceLineItemRepository = invoiceLineItemRepository;
    }

    @PostMapping("/api/invoice/{id}")
    public Invoice createInvoice(Authentication authentication, @PathVariable Long id, @RequestBody InvoiceView body) {


        System.out.println(body.toString());

        User createdBy = (User)authentication.getPrincipal();

        BillingRecord billingRecord;
        Invoice invoice = new Invoice();
        List<InvoiceLineItem> bills = new ArrayList<>();
        Date date = new Date(System.currentTimeMillis());

        for (int i = 0; i < body.getRecordIds().length; i++) {
            InvoiceLineItem invoiceLineItem = new InvoiceLineItem();
            billingRecord = billingRecordRepository.findById(body.getRecordIds()[i]).get();
            invoiceLineItem.setBillingRecord(billingRecord);
            invoiceLineItem.setCreatedBy(createdBy);
            invoiceLineItem.setCreatedOn(date);
            bills.add(invoiceLineItem);
            invoice = new Invoice(billingRecord.getClient(), date, createdBy,body.getInvoiceDescription(), bills);
//            invoiceLineItem.setInvoice(invoice);
            invoiceLineItemRepository.save(invoiceLineItem);

        }
        System.out.println(invoice.toString());
        return invoiceRepository.save(invoice);
    }

    @GetMapping("/api/invoice")
    public Iterable<Invoice> listInvoices() {

//        if(auth !=null && auth.isAuthenticated()) {
        return this.invoiceRepository.findAll();
//        }
//        return null;
    }
}
