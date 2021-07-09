package com.galvanize.invoicify.models;

import javax.persistence.Entity;

@Entity
public class FlatFeeBillingRecord extends BillingRecord {
    private double amount;

    public FlatFeeBillingRecord() {
    }

    public FlatFeeBillingRecord(double amount) {
        this.amount = amount;
    }

    public FlatFeeBillingRecord(User createdBy, String description, Company client, double amount) {
        super(createdBy, description, client);
        this.amount = amount;
    }

    @Override
    public double getTotal() {
        return this.amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "FlatFeeBillingRecord{" +
                "id=" + id +
                ", createdBy=" + createdBy +
                ", inUse=" + inUse +
                ", description='" + description + '\'' +
                ", lineItem=" + lineItem +
                ", client=" + client +
                ", amount=" + amount +
                '}';
    }
}
