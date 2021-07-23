// package com.galvanize.invoicify.configuration;

// import com.galvanize.invoicify.models.*;
// import com.galvanize.invoicify.repositories.BillingRecordRepository;
// import com.galvanize.invoicify.repositories.CompanyRepository;
// import com.galvanize.invoicify.repositories.InvoiceRepository;
// import com.galvanize.invoicify.repositories.UserRepository;
// import org.apache.catalina.Store;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.crypto.password.PasswordEncoder;

// import java.sql.Date;

// @Configuration
// public class SeedData {

// 	public SeedData(UserRepository userRepository, CompanyRepository companyRepository, BillingRecordRepository recordRepository, InvoiceRepository invoiceRepository, PasswordEncoder encoder) {
// 		User admin = userRepository.save(new User("admin", encoder.encode("admin")));
// 	}

// }
