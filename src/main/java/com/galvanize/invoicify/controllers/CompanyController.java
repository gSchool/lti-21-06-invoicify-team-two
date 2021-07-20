package com.galvanize.invoicify.controllers;

import org.springframework.security.core.Authentication;
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
    public Company createCompany(Authentication auth,@RequestBody Company company) {
        System.out.println("creating a company" + company.toString());
//        if(auth !=null && auth.isAuthenticated()){
            return this.companyRepository.save(company);
//        }
//       return null;
    }
    @GetMapping("/api/company/{id}")
    public Optional<Company> getCompanyById(Authentication auth, @PathVariable Long id) {
        System.out.println("getting a company by id" + id);
//        if(auth !=null && auth.isAuthenticated()) {
            return this.companyRepository.findById(id);
//        }
//        return null;
    }

   @PutMapping("/api/company/{id}")
   public Company updateCompany(Authentication auth, @PathVariable long id, @RequestBody Company company){
       System.out.println("updating a company" + id + " " + company.toString());
//       if(auth !=null && auth.isAuthenticated()) {
           Company newCompany = this.companyRepository.findById(id).get();
           newCompany.setName(company.getName());
           newCompany.setInvoices(company.getInvoices());
           return this.companyRepository.save(newCompany);
//       }
//       return null;
   }



    @DeleteMapping("/api/company/{id}")
    public Optional<Company> deleteCompanyById(Authentication auth, @PathVariable Long id) {
        System.out.println("deleting a company " + id);
//        if(auth !=null && auth.isAuthenticated()) {
            this.companyRepository.deleteById(id);
            return this.companyRepository.findById(id); // we want to make sure that id is deleted
//        }
//        return null;
    }
    @GetMapping("/api/company")
    public Iterable<Company> getAll(Authentication auth) {
        System.out.println("getting all companies");

//        if(auth !=null && auth.isAuthenticated()) {
            return this.companyRepository.findAll();
//        }
//        return null;
    }


}
