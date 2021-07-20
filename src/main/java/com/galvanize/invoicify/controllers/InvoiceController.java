package com.galvanize.invoicify.controllers;

import com.galvanize.invoicify.models.Company;
import com.galvanize.invoicify.models.Invoice;
import com.galvanize.invoicify.repositories.InvoiceRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvoiceController {
    private final InvoiceRepository invoiceRepository;

    public InvoiceController(InvoiceRepository invoiceRepository){
        this.invoiceRepository=invoiceRepository;
    }

    @PostMapping("/api/invoice")
    public Invoice createInvoice(@RequestBody Invoice invoice) {
        System.out.println("creating a invoice" + invoice.toString());
//        if(auth !=null && auth.isAuthenticated()){
        return this.invoiceRepository.save(invoice);
//        }
//       return null;
    }

    @GetMapping("/api/invoice")
    public Iterable<Invoice> listInvoices() {
        System.out.println("getting all companies");

//        if(auth !=null && auth.isAuthenticated()) {
        return this.invoiceRepository.findAll();
//        }
//        return null;
    }
}
