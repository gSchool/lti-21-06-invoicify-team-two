package com.example.invoicify;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.invoicify.controllers.BillingRecordController;
import com.galvanize.invoicify.models.BillingRecord;
import com.galvanize.invoicify.models.Company;
import com.galvanize.invoicify.models.FlatFeeBillingRecord;
import com.galvanize.invoicify.models.User;
import com.galvanize.invoicify.repositories.BillingRecordRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BillingRecordControllerTest {

    private BillingRecordController billingRecordController;

    @Mock
    private BillingRecordRepository billingRecordRepository;

//    @Test
//    public void testGetBillingRecords() throws JsonProcessingException {
//
//        List<BillingRecord> billRecords = new ArrayList<BillingRecord>();
//        User createdBy = new User("admin", "$2a$10$Yrz280CrCS51JrmToyUyGeHE99uVTtqazk0tOm2/nIiovk/FC5N9a");
//        String description = "Faxes";
//        Company client = new Company("Vapianos Ltd.");
//        double amount = 300;
//        FlatFeeBillingRecord flatFeeBillingRecord = new FlatFeeBillingRecord(createdBy, description, client, amount);
//        billRecords.add(flatFeeBillingRecord);
//        when(billingRecordRepository.findAll()).thenReturn(billRecords);
//        billingRecordController = new BillingRecordController(billingRecordRepository);
//        List <BillingRecord> actual = (List<BillingRecord>) billingRecordController.getBillingRecords();
//        String expected = "[FlatFeeBillingRecord{id= 0, createdBy=createdBy{id=null, password=$2a$10$Yrz280CrCS51JrmToyUyGeHE99uVTtqazk0tOm2/nIiovk/FC5N9a, username=admin, accountNonExpired=true, accountNonLocked=true, credentialsNonExpired=true, authorities=null, enabled=true}, inUse=false, description='Faxes', lineItem=null, client=Company{id=null, name='Vapianos Ltd.', invoices=null}, amount=300.0}]";
//        assertThat(""+actual).isEqualTo(expected);
//
//    }
//
//    @Test
//    public void testGetBillingRecordsByCompanyId() throws JsonProcessingException {
//
//        List<BillingRecord> billRecords = new ArrayList<BillingRecord>();
//        User createdBy = new User("admin", "$2a$10$Yrz280CrCS51JrmToyUyGeHE99uVTtqazk0tOm2/nIiovk/FC5N9a");
//        String description = "Faxes";
//        Company client = new Company("Vapianos Ltd.");
//        double amount = 300;
//        FlatFeeBillingRecord flatFeeBillingRecord = new FlatFeeBillingRecord(createdBy, description, client, amount);
//        billRecords.add(flatFeeBillingRecord);
//        when(billingRecordRepository.findById(0L)).thenReturn(Optional.of(billRecords.get(0)));
//        billingRecordController = new BillingRecordController(billingRecordRepository);
//        BillingRecord actual = billingRecordController.getBillingRecordsById(0L);
//        String expected = "Optional[FlatFeeBillingRecord{id= 0, createdBy=createdBy{id=null, password=$2a$10$Yrz280CrCS51JrmToyUyGeHE99uVTtqazk0tOm2/nIiovk/FC5N9a, username=admin, accountNonExpired=true, accountNonLocked=true, credentialsNonExpired=true, authorities=null, enabled=true}, inUse=false, description='Faxes', lineItem=null, client=Company{id=null, name='Vapianos Ltd.', invoices=null}, amount=300.0}]";
//        assertThat(""+actual).isEqualTo(expected);
//    }
//
//
//    @Test
//    public void testAddFlatFeeBillingRecord() throws JsonProcessingException {
//
//        List<BillingRecord> billRecords = new ArrayList<BillingRecord>();
//        User createdBy = new User("admin", "$2a$10$Yrz280CrCS51JrmToyUyGeHE99uVTtqazk0tOm2/nIiovk/FC5N9a");
//        String description = "Faxes";
//        Company client = new Company("Vapianos Ltd.");
//        double amount = 300;
//        FlatFeeBillingRecord flatFeeBillingRecord = new FlatFeeBillingRecord(createdBy, description, client, amount);
//        billRecords.add(flatFeeBillingRecord);
//        when(billingRecordRepository.save(any(FlatFeeBillingRecord.class))).thenReturn((FlatFeeBillingRecord) billRecords.get(0));
//        billingRecordController = new BillingRecordController(billingRecordRepository);
//        Optional<BillingRecord> actual = Optional.ofNullable(billingRecordController.addFlatFeeBillingRecord(0L, flatFeeBillingRecord));
//        String expected = "Optional[FlatFeeBillingRecord{id= 0, createdBy=createdBy{id=null, password=$2a$10$Yrz280CrCS51JrmToyUyGeHE99uVTtqazk0tOm2/nIiovk/FC5N9a, username=admin, accountNonExpired=true, accountNonLocked=true, credentialsNonExpired=true, authorities=null, enabled=true}, inUse=false, description='Faxes', lineItem=null, client=Company{id=null, name='Vapianos Ltd.', invoices=null}, amount=300.0}]";
//        assertThat(""+actual).isEqualTo(expected);
//    }



}

