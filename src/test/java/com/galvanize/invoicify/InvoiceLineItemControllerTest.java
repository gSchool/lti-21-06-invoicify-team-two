package com.galvanize.invoicify;

import com.galvanize.invoicify.controllers.InvoiceLineItemController;
import com.galvanize.invoicify.models.*;
import com.galvanize.invoicify.repositories.InvoiceLineItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class InvoiceLineItemControllerTest {

   private InvoiceLineItemController invoiceLineItemController;

   @Mock
    private InvoiceLineItemRepository invoiceLineItemRepository;

   //create
   @Test
    public void testCreateInvoiceLineItem () throws Exception{
       Date d1 = new Date (2021-07-21);
       InvoiceLineItem invline1 = new InvoiceLineItem();

       Invoice invoice1 = new Invoice();
       invoice1.setCompany(new Company("ABC-Company"));
       invoice1.setCreatedBy(new User("bob", "password"));
       invoice1.setCreatedOn(d1);
       invoice1.setInvoiceDescription("Test Invoice Line Item in Invoice Message");
       invline1.setInvoice(invoice1 );
       FlatFeeBillingRecord flatFeeBillingRecord = new FlatFeeBillingRecord();
       flatFeeBillingRecord.setCreatedBy(new User("bob", "password"));
       flatFeeBillingRecord.setDescription("In Line Item test class");
       flatFeeBillingRecord.setClient(new Company("ABC-Company"));
       flatFeeBillingRecord.setAmount(2500.00);
       invline1.setBillingRecord(flatFeeBillingRecord);

       invline1.setCreatedBy(new User());
       invline1.setCreatedOn(d1);
      when(invoiceLineItemRepository.save(any(InvoiceLineItem.class))).thenReturn(invline1);
   when(invoiceLineItemRepository.save(any(InvoiceLineItem.class))).thenReturn()

    //  when(invoiceLineItemRepository.save(any(InvoiceLineItem.class))).thenReturn(new InvoiceLineItem(invline1.getBillingRecord(),invline1.getCreatedOn(),invline1.getCreatedBy(),invline1.getInvoice()));


   }

   //read
  /* @Test
   public void testReadInvoiceLineItem () throws Exception{



   }
   //update
   @Test
   public void testUpdateInvoiceLineItem () throws Exception{



   }
    //delete
    @Test
    public void testDeleteInvoiceLineItem () throws Exception{



    }
    //list
    @Test
    public void testListInvoiceLineItem () throws Exception{



    }*/
}
