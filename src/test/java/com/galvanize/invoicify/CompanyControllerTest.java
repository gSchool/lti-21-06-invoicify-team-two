package com.galvanize.invoicify;

import com.galvanize.invoicify.controllers.CompanyController;
import com.galvanize.invoicify.controllers.UserController;
import com.galvanize.invoicify.models.Company;
import com.galvanize.invoicify.models.User;
import com.galvanize.invoicify.repositories.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CompanyControllerTest {

    private CompanyController companyController;
    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private AuthenticationManager authenticator;

    @Mock
    private Authentication auth;

    @Test
    public void testCreateCompany(){
        when(companyRepository.save(any(Company.class))).thenReturn(new Company("ABC-Company"));
        companyController = new CompanyController(companyRepository);
        Company actual = companyController.createCompany(auth,new Company("ABC-Company"));
        assertThat(actual.getName()).isEqualTo("ABC-Company");
    }

    //READ
    @Test
    public void testGetCompanyById() {
        when(companyRepository.findById(1L)).thenReturn(Optional.of(new Company("ABC-Company")));
        companyController = new CompanyController(companyRepository);
        Optional<Company> actual = companyController.getCompanyById(auth,1L);
        assertThat(actual.get().getName()).isEqualTo("ABC-Company");
    }

    //UPDATE
    @Test
    public void testUpdateCompany() {
        when(companyRepository.findById(1L)).thenReturn(Optional.of(new Company("ABC-Company")));
        when(companyRepository.save(any(Company.class))).thenReturn(new Company("XYZ-Company"));
        companyController = new CompanyController(companyRepository);
        Optional<Company> actual = Optional.ofNullable(companyController.updateCompany(auth, 1L, new Company("XYZ-Company")));
        Company actual2 = companyController.updateCompany(auth, 1L, actual.get());
        assertThat(actual2.getName()).isEqualTo("XYZ-Company");
    }

    //DELETE
    @Test
    public void testDeleteCompany() {
        Company company = new Company("ABC-Company");
        when(companyRepository.findById(company.getId())).thenReturn(Optional.of(company));
        companyController = new CompanyController(companyRepository);
        Optional<Company> actual = companyController.deleteCompanyById(auth, company.getId());
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
        Iterable<Company> actual = companyController.getAll(auth);
        assertThat(actual.spliterator().getExactSizeIfKnown()).isEqualTo(2);
    }

}

