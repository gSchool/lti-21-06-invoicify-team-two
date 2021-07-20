package com.galvanize.invoicify.controllers;

import com.galvanize.invoicify.repositories.InvoiceLineItemRepository;

public class InvoiceLineItemController {

private InvoiceLineItemRepository invoiceLineItemRepository;

    public InvoiceLineItemController(InvoiceLineItemRepository invoiceLineItemRepository) {
        this.invoiceLineItemRepository = invoiceLineItemRepository;
    }
}
