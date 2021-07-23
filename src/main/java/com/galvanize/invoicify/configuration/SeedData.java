package com.galvanize.invoicify.configuration;

import com.galvanize.invoicify.models.*;
import com.galvanize.invoicify.repositories.BillingRecordRepository;
import com.galvanize.invoicify.repositories.CompanyRepository;
import com.galvanize.invoicify.repositories.InvoiceRepository;
import com.galvanize.invoicify.repositories.UserRepository;
import org.apache.catalina.Store;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Date;

@Configuration
public class SeedData {

	public SeedData(UserRepository userRepository, CompanyRepository companyRepository, BillingRecordRepository recordRepository, InvoiceRepository invoiceRepository, PasswordEncoder encoder) {
		User admin = userRepository.save(new User("admin", encoder.encode("admin")));
		User userBob = userRepository.save(new User("bob", encoder.encode("password")));
		User userBobby = userRepository.save(new User("bobby", encoder.encode("password")));
		User userSally = userRepository.save(new User("sally", encoder.encode("password")));
		User userCindy = userRepository.save(new User("cindy", encoder.encode("password")));
		Company company1 = companyRepository.save(new Company("ABC Company"));
		FlatFeeBillingRecord record1 = recordRepository.save(new FlatFeeBillingRecord(admin, "Faxes", company1, 300));
		Date d1 = new Date (2021-07-21);
	//	Invoice inv1 = invoiceRepository.save(new Invoice(company1, d1, admin, "Invoice test", null ));
	}




//	public SeedData(BillingRecordRepository billingRecordRepository) {
//		FlatFeeBillingRecord company1 = billingRecordRepository.save(new FlatFeeBillingRecord(new User("admin", "$2a$10$Yrz280CrCS51JrmToyUyGeHE99uVTtqazk0tOm2/nIiovk/FC5N9a"),"test Description",new Company("Vapianos Ltd."),45));
//
//	}

}
