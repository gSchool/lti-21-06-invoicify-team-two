package com.galvanize.invoicify.models;

import javax.persistence.Entity;

@Entity
public class RateBasedBillingRecord extends BillingRecord {


    private double rate;
    private double quantity;

    public RateBasedBillingRecord() {
    }

    public RateBasedBillingRecord(double rate, double quantity) {
        this.rate = rate;
        this.quantity = quantity;
    }

    public RateBasedBillingRecord(User createdBy, String description, Company client, double rate, double quantity) {
        super(createdBy, description, client);
        this.rate = rate;
        this.quantity = quantity;
    }

    @Override
    public double getTotal() {
        return 0;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "RateBasedBillingRecord{" +
                "id=" + id +
                ", createdBy=" + createdBy +
                ", inUse=" + inUse +
                ", description='" + description + '\'' +
                ", lineItem=" + lineItem +
                ", client=" + client +
                ", rate=" + rate +
                ", quantity=" + quantity +
                '}';
    }
}
