package com.galvanize.invoicify.repositories;

import com.galvanize.invoicify.models.Company;
import com.galvanize.invoicify.models.User;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Long> {

}
