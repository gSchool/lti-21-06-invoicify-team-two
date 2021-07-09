package com.galvanize.invoicify.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class InvoiceLineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonBackReference
    @OneToOne
    private BillingRecord billingRecord;

    private Date createdOn;

    @ManyToOne
    private User createdBy;

    @JsonBackReference(value = "secondParent")
    @ManyToOne
    private Invoice invoice;

    public InvoiceLineItem() {
    }

    public InvoiceLineItem(BillingRecord billingRecord, Date createdOn, User createdBy, Invoice invoice) {
        this.billingRecord = billingRecord;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.invoice = invoice;
    }

    public Long getId() {
        return id;
    }

    public BillingRecord getBillingRecord() {
        return billingRecord;
    }

    public void setBillingRecord(BillingRecord billingRecord) {
        this.billingRecord = billingRecord;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    @Override
    public String toString() {
        return "InvoiceLineItem{" +
                "id=" + id +
                ", billingRecord=" + billingRecord +
                ", createdOn=" + createdOn +
                ", createdBy=" + createdBy +
                ", invoice=" + invoice +
                '}';
    }
}
