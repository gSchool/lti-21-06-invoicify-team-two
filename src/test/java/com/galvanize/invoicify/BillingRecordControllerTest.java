package com.galvanize.invoicify;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.invoicify.controllers.BillingRecordController;
import com.galvanize.invoicify.models.*;
import com.galvanize.invoicify.repositories.BillingRecordRepository;
import com.galvanize.invoicify.repositories.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BillingRecordControllerTest {

    @Mock
    private AuthenticationManager authenticator;
    @Mock
    private Authentication auth;

    @Mock
    private BillingRecordController billingRecordController;
    @Mock
    private BillingRecordRepository billingRecordRepository;
    @Mock
    private CompanyRepository companyRepository;

    @Test
    public void testGetBillingRecords()  {
        List<BillingRecord> billRecords = new ArrayList<BillingRecord>();
        User createdBy = new User("admin", "$2a$10$Yrz280CrCS51JrmToyUyGeHE99uVTtqazk0tOm2/nIiovk/FC5N9a");
        String description = "Faxes";
        Company client = new Company("Vapianos Ltd.");
        double amount = 300.0;
        FlatFeeBillingRecord flatFeeBillingRecord = new FlatFeeBillingRecord(createdBy, description, client, amount);
        billRecords.add(flatFeeBillingRecord);
        when(billingRecordRepository.findAll()).thenReturn(billRecords);
        billingRecordController = new BillingRecordController(billingRecordRepository, companyRepository);
        List <BillingRecord> actual = (List<BillingRecord>) billingRecordController.getBillingRecords(auth);
        String expected = "[FlatFeeBillingRecord{id= 0, createdBy=createdBy{id=null, password=$2a$10$Yrz280CrCS51JrmToyUyGeHE99uVTtqazk0tOm2/nIiovk/FC5N9a, username=admin, accountNonExpired=true, accountNonLocked=true, credentialsNonExpired=true, authorities=null, enabled=true}, inUse=false, description='Faxes', lineItem=null, client=Company{id=null, name='Vapianos Ltd.', invoices=null}, amount=300.0}]";
        assertThat(""+actual).isEqualTo(expected);
    }

    @Test
    public void testGetBillingRecordsByCompanyId()  {
        List<BillingRecord> billRecords = new ArrayList<BillingRecord>();
        User createdBy = new User("admin", "$2a$10$Yrz280CrCS51JrmToyUyGeHE99uVTtqazk0tOm2/nIiovk/FC5N9a");
        String description = "Faxes";
        Company client = new Company("Vapianos Ltd.");
        double amount = 300;
        FlatFeeBillingRecord flatFeeBillingRecord = new FlatFeeBillingRecord(createdBy, description, client, amount);
        billRecords.add(flatFeeBillingRecord);
        when(billingRecordRepository.findAllByClient(client)).thenReturn(Optional.of(billRecords).get());
        billingRecordController = new BillingRecordController(billingRecordRepository, companyRepository);
        Iterable<BillingRecord> actual = billingRecordRepository.findAllByClient(client);
        String expected = "[FlatFeeBillingRecord{id= 0, createdBy=createdBy{id=null, password=$2a$10$Yrz280CrCS51JrmToyUyGeHE99uVTtqazk0tOm2/nIiovk/FC5N9a, username=admin, accountNonExpired=true, accountNonLocked=true, credentialsNonExpired=true, authorities=null, enabled=true}, inUse=false, description='Faxes', lineItem=null, client=Company{id=null, name='Vapianos Ltd.', invoices=null}, amount=300.0}]";
        assertThat(""+actual).isEqualTo(expected);
    }

    @Test
    public void testGetBillingRecordsInId() {
        List<BillingRecord> billRecords = new ArrayList<BillingRecord>();
        Long[] recordIds = {0L};
        User createdBy = new User("admin", "$2a$10$Yrz280CrCS51JrmToyUyGeHE99uVTtqazk0tOm2/nIiovk/FC5N9a");
        String description = "Faxes";
        Company client = new Company("Vapianos Ltd.");
        double amount = 300;
        FlatFeeBillingRecord flatFeeBillingRecord = new FlatFeeBillingRecord(createdBy, description, client, amount);
        billRecords.add(flatFeeBillingRecord);
        when(billingRecordRepository.findByIdIn(recordIds)).thenReturn(billRecords);
        billingRecordController = new BillingRecordController(billingRecordRepository, companyRepository);
        Iterable<BillingRecord> actual = billingRecordRepository.findByIdIn(recordIds);
        String expected = "[FlatFeeBillingRecord{id= 0, createdBy=createdBy{id=null, password=$2a$10$Yrz280CrCS51JrmToyUyGeHE99uVTtqazk0tOm2/nIiovk/FC5N9a, username=admin, accountNonExpired=true, accountNonLocked=true, credentialsNonExpired=true, authorities=null, enabled=true}, inUse=false, description='Faxes', lineItem=null, client=Company{id=null, name='Vapianos Ltd.', invoices=null}, amount=300.0}]";
        assertThat("" + actual).isEqualTo(expected);
    }

    @Test
    public void testAddFlatFeeBillingRecord()  {
        List<BillingRecord> billRecords = new ArrayList<BillingRecord>();
        User createdBy = new User("admin", "$2a$10$Yrz280CrCS51JrmToyUyGeHE99uVTtqazk0tOm2/nIiovk/FC5N9a");
        when(auth.getPrincipal()).thenReturn(createdBy);
        String description = "Faxes";
        Company client = new Company("Vapianos Ltd.");
        when(companyRepository.findById(any(Long.class))).thenReturn(Optional.of(client));
        double amount = 300.0;
        FlatFeeBillingRecord flatFeeBillingRecord = new FlatFeeBillingRecord(createdBy, description, client, amount);
        billRecords.add(flatFeeBillingRecord);
        when(billingRecordRepository.save(any(FlatFeeBillingRecord.class))).thenReturn((FlatFeeBillingRecord) billRecords.get(0));
        billingRecordController = new BillingRecordController(billingRecordRepository, companyRepository);
        BillingRecord actual = billingRecordController.addFlatFeeBillingRecord(auth, 0L, flatFeeBillingRecord);
        String expected = "FlatFeeBillingRecord{id= 0, createdBy=createdBy{id=null, password=$2a$10$Yrz280CrCS51JrmToyUyGeHE99uVTtqazk0tOm2/nIiovk/FC5N9a, username=admin, accountNonExpired=true, accountNonLocked=true, credentialsNonExpired=true, authorities=null, enabled=true}, inUse=false, description='Faxes', lineItem=null, client=Company{id=null, name='Vapianos Ltd.', invoices=null}, amount=300.0}";
        assertThat(""+actual).isEqualTo(expected);
    }

    @Test
    public void testAddRateBasedBillingRecord()  {
        List<BillingRecord> billRecords = new ArrayList<BillingRecord>();
        User createdBy = new User("admin", "$2a$10$Yrz280CrCS51JrmToyUyGeHE99uVTtqazk0tOm2/nIiovk/FC5N9a");
        when(auth.getPrincipal()).thenReturn(createdBy);
        String description = "Faxes";
        Company client = new Company("Vapianos Ltd.");
        when(companyRepository.findById(any(Long.class))).thenReturn(Optional.of(client));
        double rate = 300;
        double quantity = 2;
        RateBasedBillingRecord rateBasedBillingRecord = new RateBasedBillingRecord(createdBy, description, client, rate, quantity);
        billRecords.add(rateBasedBillingRecord);
        when(billingRecordRepository.save(any(RateBasedBillingRecord.class))).thenReturn((RateBasedBillingRecord) billRecords.get(0));
        billingRecordController = new BillingRecordController(billingRecordRepository, companyRepository);
        BillingRecord actual = billingRecordController.addRateBasedBillingRecord(auth, 0L,  rateBasedBillingRecord);
        String expected = "RateBasedBillingRecord{id=0, createdBy=createdBy{id=null, password=$2a$10$Yrz280CrCS51JrmToyUyGeHE99uVTtqazk0tOm2/nIiovk/FC5N9a, username=admin, accountNonExpired=true, accountNonLocked=true, credentialsNonExpired=true, authorities=null, enabled=true}, inUse=false, description='Faxes', lineItem=null, client=Company{id=null, name='Vapianos Ltd.', invoices=null}, rate=300.0, quantity=2.0}";
        assertThat("" + actual).isEqualTo(expected);
    }

}

