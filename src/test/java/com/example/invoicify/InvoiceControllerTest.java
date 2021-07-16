package com.example.invoicify;

import com.galvanize.invoicify.controllers.CompanyController;
import com.galvanize.invoicify.controllers.InvoiceController;
import com.galvanize.invoicify.controllers.UserController;
import com.galvanize.invoicify.models.Company;
import com.galvanize.invoicify.models.Invoice;
import com.galvanize.invoicify.models.InvoiceLineItem;
import com.galvanize.invoicify.models.User;
import com.galvanize.invoicify.repositories.CompanyRepository;
import com.galvanize.invoicify.repositories.InvoiceRepository;
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

@ExtendWith(MockitoExtension.class)
public class InvoiceControllerTest {

    private InvoiceController invoiceController;

    @Mock
    private InvoiceRepository invoiceRepository;


    @Test
    public void testInvoiceList() throws Exception {

        Company company = new Company("Google Inc");
        Date date = new Date(2021, 07, 14);
        User createdBy = new User("Me", "");
        String invoiceDescription = "Me";

//        when(invoiceRepository.save(any(Invoice.class))).thenReturn(new Invoice(1L, company, date, createdBy,  invoiceDescription, invoiceList));
//       when(invoiceRepository.findAll()).thenReturn((Iterable<InvoiceLineItem>) invoiceList);

    }
/*
    @Test
    public void testCreateCompany(){
        when(invoice.save(any(Company.class))).thenReturn(new Company("ABC-Company"));
        companyController = new CompanyController(companyRepository);
        Company actual = companyController.createCompany(new Company("ABC-Company"));
        assertThat(actual.getName()).isEqualTo("ABC-Company");

    }

    //READ
    @Test
    public void testGetCompanyById() {

        //MockitoAnnotations.initMocks(this);

        when(companyRepository.findById(1L)).thenReturn(Optional.of(new Company("ABC-Company")));

        companyController = new CompanyController(companyRepository);
        Optional<Company> actual = companyController.getCompanyById(1L);

        assertThat(actual.get().getName()).isEqualTo("ABC-Company");
    }
    //UPDATE
    @Test
    public void testUpdateCompany() {

        //MockitoAnnotations.initMocks(this);

        when(companyRepository.findById(1L)).thenReturn(Optional.of(new Company("ABC-Company")));
        when(companyRepository.save(any(Company.class))).thenReturn(new Company("XYZ-Company"));

        companyController = new CompanyController(companyRepository);
        Optional<Company> actual = Optional.ofNullable(companyController.updateCompany(1L, new Company("XYZ-Company")));
        Company actual2 = companyController.createCompany(actual.get());

        assertThat(actual2.getName()).isEqualTo("XYZ-Company");
    }
    //DELETE
    @Test
    public void testDeleteCompany() {

        //  MockitoAnnotations.initMocks(this);

        Company company = new Company("ABC-Company");

        when(companyRepository.findById(company.getId())).thenReturn(Optional.of(company));
        companyController = new CompanyController(companyRepository);

        Optional<Company> actual = companyController.deleteCompanyById(company.getId());

        assertThat(actual.get().getName()).isEqualTo("ABC-Company");

    }

    //LIST
    @Test
    public void testListCustomers() {
        ArrayList<Company> companies = new ArrayList<Company>();
        companies.add(new Company("ABC-Company"));
        companies.add(new Company("XYZ-Company"));
        when(companyRepository.findAll()).thenReturn(companies);

        companyController = new CompanyController(companyRepository);

        Iterable<Company> actual = companyController.getAll();

        assertThat(actual.spliterator().getExactSizeIfKnown()).isEqualTo(2);

    }
 */
}
