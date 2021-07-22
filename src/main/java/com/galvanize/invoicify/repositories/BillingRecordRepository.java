package com.galvanize.invoicify.repositories;

import com.galvanize.invoicify.models.BillingRecord;
import com.galvanize.invoicify.models.Company;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BillingRecordRepository  extends CrudRepository<BillingRecord, Long>  {
    List<BillingRecord> findByIdIn(Long[] RecordIds);
    Iterable<BillingRecord> findAllByClient(Company company);
}
