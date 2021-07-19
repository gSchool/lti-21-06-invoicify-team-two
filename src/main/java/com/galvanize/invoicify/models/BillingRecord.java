package com.galvanize.invoicify.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
public abstract  class BillingRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    @ManyToOne
    protected User createdBy;


    protected boolean inUse=false;

    protected String description;

    @JsonManagedReference
    @OneToOne(mappedBy = "billingRecord")
    protected InvoiceLineItem lineItem;

    @ManyToOne
    protected Company client;

    public long getId() {
        return id;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public InvoiceLineItem getLineItem() {
        return lineItem;
    }

    public void setLineItem(InvoiceLineItem lineItem) {
        this.lineItem = lineItem;
    }

    public Company getClient() {
        return client;
    }

    public void setClient(Company client) {
        this.client = client;
    }

    public BillingRecord() {
    }

    public BillingRecord(User createdBy, String description, Company client) {
        this.createdBy = createdBy;
        this.description = description;
        this.client = client;
    }
    public abstract double getTotal();


}