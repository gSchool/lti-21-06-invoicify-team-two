package com.galvanize.invoicify;

import com.galvanize.invoicify.controllers.CompanyController;
import com.galvanize.invoicify.controllers.InvoiceController;
import com.galvanize.invoicify.controllers.UserController;
import com.galvanize.invoicify.models.Company;
import com.galvanize.invoicify.models.Invoice;
import com.galvanize.invoicify.models.InvoiceLineItem;
import com.galvanize.invoicify.models.User;
import com.galvanize.invoicify.repositories.CompanyRepository;
import com.galvanize.invoicify.repositories.InvoiceRepository;
import com.galvanize.invoicify.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.security.crypto.password.PasswordEncoder;
@ExtendWith(MockitoExtension.class)
public class InvoiceControllerTest {

    private InvoiceController invoiceController;

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private CompanyRepository companyRepository;

    private CompanyController companyController;

    @Mock
    private UserRepository userRepository;

    private UserController userController;
    @Mock
    private PasswordEncoder encoder;

    //create
    @Test
    public void testCreateCompany() {

        User admin = userRepository.save(new User("admin", encoder.encode("admin")));

        Company company1 = companyRepository.save(new Company("ABC Company"));
        Date d1 = new Date(2021 - 07 - 21);
        Invoice inv1 = new Invoice(company1, d1, admin, "Invoice test", null);
        invoiceRepository.save(inv1);
        when(invoiceRepository.save(any(Invoice.class))).thenReturn(new Invoice(company1, d1, admin, "test description", null));
        invoiceController = new InvoiceController(invoiceRepository);
        Invoice actual = invoiceController.createInvoice(new Invoice(company1, inv1.getCreatedOn(), inv1.getCreatedBy(), inv1.getInvoiceDescription(), inv1.getLineItems()));
        assertThat(actual.getInvoiceDescription().equals("test description"));

    }

    //LIST
     @Test
    public void testListInvoices() {

        ArrayList<Invoice> invoices = new ArrayList<Invoice>();
        invoices.add(new Invoice());
        invoices.add(new Invoice());
        when(invoiceRepository.findAll()).thenReturn(invoices);

        invoiceController = new InvoiceController(invoiceRepository);

        Iterable<Invoice> actual = invoiceController.listInvoices();

        assertThat(actual.spliterator().getExactSizeIfKnown()).isEqualTo(2);

    }

}
