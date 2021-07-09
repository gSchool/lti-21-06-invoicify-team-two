package com.galvanize.invoicify.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
public abstract  class BillingRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    protected User createdBy;


    protected boolean inUse=false;

    protected String description;

    @JsonManagedReference
    @OneToOne(mappedBy = "billingRecord")
    protected InvoiceLineItem lineItem;

    @ManyToOne
    protected Company client;

    public BillingRecord() {
    }

    public BillingRecord(User createdBy, String description, Company client) {
        this.createdBy = createdBy;
        this.description = description;
        this.client = client;
    }
    public abstract double getTotal();


}