package com.galvanize.invoicify.models;

public class InvoiceView {
    private String inVoiceDescription;
    private Long[] recordsIds;


    public InvoiceView(String inVoiceDescription, Long[] recordsIds) {
        this.inVoiceDescription = inVoiceDescription;
        this.recordsIds = recordsIds;
    }
}
