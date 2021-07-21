package com.galvanize.invoicify.models;

//import javax.persistence.Entity;

import java.util.Arrays;

public class InvoiceView {
    private String invoiceDescription;
    private Long[] recordIds;


    public InvoiceView(String invoiceDescription, Long[] recordIds) {
        this.invoiceDescription = invoiceDescription;
        this.recordIds = recordIds;
    }

    public String getInvoiceDescription() {
        return invoiceDescription;
    }


    public Long[] getRecordIds() {
        return recordIds;
    }


    @Override
    public String toString() {
        return "InvoiceView{" +
                "inVoiceDescription='" + invoiceDescription + '\'' +
                ", recordIds=" + Arrays.toString(recordIds) +
                '}';
    }
}
