package com.galvanize.invoicify.repositories;

import com.galvanize.invoicify.models.InvoiceLineItem;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceLineItemRepository extends CrudRepository<InvoiceLineItem, Long> {
}
