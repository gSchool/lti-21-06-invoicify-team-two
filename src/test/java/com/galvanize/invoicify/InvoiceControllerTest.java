package com.galvanize.invoicify;

import com.galvanize.invoicify.controllers.InvoiceController;
import com.galvanize.invoicify.models.*;
import com.galvanize.invoicify.repositories.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InvoiceControllerTest {
    private InvoiceController invoiceController;

    @Mock
    private InvoiceRepository invoiceRepository;
    @Mock
    private PasswordEncoder encoder;
    @Mock
    private CompanyRepository companyRepository;
    @Mock
    private BillingRecordRepository billingRecordRepository;
    @Mock
    private Authentication auth;

    //create
    @Test
    public void testCreateInvoice() {
        User admin = new User("user", encoder.encode("password"));
        Company company1 = new Company("ABC Company");
        Date d1 = new Date(2021 - 07 - 21);
        List<InvoiceLineItem> invoiceLineItems = new ArrayList<InvoiceLineItem>();
        Invoice inv1 = new Invoice(company1, d1, admin, "Invoice test", invoiceLineItems);
        when(invoiceRepository.save(any(Invoice.class))).thenReturn(new Invoice(company1, d1, admin, "test description", invoiceLineItems));
        invoiceController = new InvoiceController(invoiceRepository, companyRepository, billingRecordRepository);
        Long[] recordIds = {7L};
        List<BillingRecord> list = new ArrayList<BillingRecord>();
        FlatFeeBillingRecord record1 = new FlatFeeBillingRecord(admin, "Faxes", company1, 300);
        list.add(record1);
        when(companyRepository.findById(any(Long.class))).thenReturn(java.util.Optional.of(company1));
        when(billingRecordRepository.findByIdIn(recordIds)).thenReturn(list);
        Invoice inv3 = invoiceController.createInvoice(auth, 6L, new InvoiceView("test description", recordIds));
        Invoice actual = new Invoice(company1, d1, admin, "test description", invoiceLineItems);
        assertThat(actual.getInvoiceDescription().equals(inv3.getInvoiceDescription()));
    }

    //LIST
    @Test
    public void testListInvoices() {
        ArrayList<Invoice> invoices = new ArrayList<Invoice>();
        invoices.add(new Invoice());
        invoices.add(new Invoice());
        when(invoiceRepository.findAll()).thenReturn(invoices);
        invoiceController = new InvoiceController(invoiceRepository, companyRepository, billingRecordRepository);
        Iterable<Invoice> actual = invoiceController.listInvoices();
        assertThat(actual.spliterator().getExactSizeIfKnown()).isEqualTo(2);
    }

}
