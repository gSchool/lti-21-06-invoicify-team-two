package com.galvanize.invoicify.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

//@Document(collection ="Invoice") // this is dbmongo

@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @ManyToOne
    private Company company;

    private Date createdOn;

    @ManyToOne
    private User createdBy;

    private String invoiceDescription;

    @JsonManagedReference(value = "secondParent")
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<InvoiceLineItem> lineItems;

    public Invoice() {
    }

    public Invoice(long id, Company company, Date createdOn, User createdBy, String invoiceDescription, List<InvoiceLineItem> lineItems) {
        this.id = id;
        this.company = company;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.invoiceDescription = invoiceDescription;
        this.lineItems = lineItems;
    }

    public long getId() {
        return id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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

    public String getInvoiceDescription() {
        return invoiceDescription;
    }

    public void setInvoiceDescription(String invoiceDescription) {
        this.invoiceDescription = invoiceDescription;
    }

    public List<InvoiceLineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<InvoiceLineItem> lineItems) {
        this.lineItems = lineItems;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", company=" + company +
                ", createdOn=" + createdOn +
                ", createdBy=" + createdBy +
                ", invoiceDescription='" + invoiceDescription + '\'' +
                ", lineItems=" + lineItems +
                '}';
    }
}

