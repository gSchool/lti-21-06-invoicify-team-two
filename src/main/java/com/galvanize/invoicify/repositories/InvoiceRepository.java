package com.galvanize.invoicify.repositories;

import com.galvanize.invoicify.models.Company;
import com.galvanize.invoicify.models.Invoice;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
}
