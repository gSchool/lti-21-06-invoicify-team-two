
package com.galvanize.invoicify.controllers;

import com.galvanize.invoicify.models.Company;
import com.galvanize.invoicify.models.Invoice;
import com.galvanize.invoicify.repositories.InvoiceRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class InvoiceController {
    private final InvoiceRepository invoiceRepository;

    public InvoiceController(InvoiceRepository invoiceRepository){
        this.invoiceRepository=invoiceRepository;
    }

    @PostMapping("/invoice/{id}")
    public Invoice createInvoice(Authentication auth,@RequestBody Invoice invoice) {
        System.out.println("creating a invoice" + invoice.toString());
        if(auth !=null && auth.isAuthenticated()){
            return this.invoiceRepository.save(invoice);
        }
       return null;
    }

    @GetMapping("/invoice")
    public Iterable<Invoice> listInvoices() {
        System.out.println("getting all companies");

//        if(auth !=null && auth.isAuthenticated()) {
        return this.invoiceRepository.findAll();
//        }
//        return null;
    }
}