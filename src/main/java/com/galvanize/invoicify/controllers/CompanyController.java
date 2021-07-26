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

    /**
     * <p> Creates a new Company. </p>
     *
     * @param auth
     * @param company
     * @return created company
     */
    @PostMapping()
    public Company createCompany(Authentication auth, @RequestBody Company company) {
        return this.companyRepository.save(company);
    }

    /**
     * <p> Returns a Company based on an ID or null if not found. </p>
     *
     * @param auth
     * @param id
     * @return company
     */
    @GetMapping("/{id}")
    public Optional<Company> getCompanyById(Authentication auth, @PathVariable Long id) {
        return this.companyRepository.findById(id);
    }

    /**
     * <p> Finds a Company by ID and updates the description. </p>
     *
     * @param auth
     * @param id
     * @param company
     * @return updated company
     */
    @PutMapping("/{id}")
    public Company updateCompany(Authentication auth, @PathVariable long id, @RequestBody Company company) {
        Company newCompany = this.companyRepository.findById(id).get();
        newCompany.setName(company.getName());
        newCompany.setInvoices(company.getInvoices());
        return this.companyRepository.save(newCompany);
    }

    /**
     * <p> Deletes a Company by ID.</p>
     *
     * @param auth
     * @param id
     * @return deleted company
     */
    @DeleteMapping("/{id}")
    public Optional<Company> deleteCompanyById(Authentication auth, @PathVariable Long id) {
        Optional<Company> deleted = companyRepository.findById(id);
        this.companyRepository.deleteById(id);
        return deleted;
    }

    /**
     * <p> Returns all companies. </p>
     *
     * @param auth
     * @return list of Company
     */
    @GetMapping()
    public Iterable<Company> getAll(Authentication auth) {
        return this.companyRepository.findAll();
    }
}
