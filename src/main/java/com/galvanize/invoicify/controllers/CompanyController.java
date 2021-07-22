package com.galvanize.invoicify.controllers;

import org.springframework.security.core.Authentication;
import com.galvanize.invoicify.models.Company;
import com.galvanize.invoicify.repositories.CompanyRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyRepository companyRepository;

    public CompanyController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @PostMapping()
    public Company createCompany(Authentication auth, @RequestBody Company company) {
        return this.companyRepository.save(company);
    }

    @GetMapping("/{id}")
    public Optional<Company> getCompanyById(Authentication auth, @PathVariable Long id) {
        return this.companyRepository.findById(id);
    }

    @PutMapping("/{id}")
    public Company updateCompany(Authentication auth, @PathVariable long id, @RequestBody Company company) {
        Company newCompany = this.companyRepository.findById(id).get();
        newCompany.setName(company.getName());
        newCompany.setInvoices(company.getInvoices());
        return this.companyRepository.save(newCompany);
    }

    @DeleteMapping("/{id}")
    public Optional<Company> deleteCompanyById(Authentication auth, @PathVariable Long id) {
        this.companyRepository.deleteById(id);
        return this.companyRepository.findById(id); // we want to make sure that id is deleted
    }

    @GetMapping()
    public Iterable<Company> getAll(Authentication auth) {
        return this.companyRepository.findAll();
    }

}
