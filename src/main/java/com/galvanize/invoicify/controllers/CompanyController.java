package com.galvanize.invoicify.controllers;

import com.galvanize.invoicify.models.Company;
import com.galvanize.invoicify.models.User;
import com.galvanize.invoicify.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CompanyController {

    private final CompanyRepository companyRepository;


    public CompanyController(CompanyRepository companyRepository){
        this.companyRepository=companyRepository;
    }

    @PostMapping("/api/company")
    public Company createCompany(@RequestBody Company company) {
       return this.companyRepository.save(company);

    }
    @GetMapping("/api/company/{id}")
    public Optional<Company> getCompanyById(@PathVariable Long id) {
     return this.companyRepository.findById(id);
    }

   @PutMapping("/api/company/{id}")
   public Company updateCompany(@PathVariable long id, @RequestBody Company company){
        Company newCompany =this.companyRepository.findById(id).get();
        newCompany.setName(company.getName());
        newCompany.setInvoices(company.getInvoices());
        return this.companyRepository.save(newCompany);
   }



    @DeleteMapping("/api/company/{id}")
    public Optional<Company> deleteCompanyById(@PathVariable Long id) {
      this.companyRepository.deleteById(id);
      return this.companyRepository.findById(id); // we want to make sure that id is deleted
    }
    @GetMapping("/api/company")
    public Iterable<Company> getAll() {
       return this.companyRepository.findAll();
    }


}
