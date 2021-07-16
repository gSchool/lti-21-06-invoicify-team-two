package com.galvanize.invoicify.repositories;

import com.galvanize.invoicify.models.BillingRecord;
import org.springframework.data.repository.CrudRepository;

public interface BillingRecordRepository  extends CrudRepository<BillingRecord, Long>  {
}
