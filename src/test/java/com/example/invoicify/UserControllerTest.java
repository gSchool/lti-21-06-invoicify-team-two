package com.example.invoicify;

import com.galvanize.invoicify.controllers.SessionController;
import com.galvanize.invoicify.controllers.UserController;
import com.galvanize.invoicify.models.User;
import com.galvanize.invoicify.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {


    private UserController userController;
    private SessionController sessionController;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder encoder;

    @Mock
    private UserDetailsService userDetails;
    @Mock
    private AuthenticationManager authenticator;

    @Mock
    Authentication auth;

//
//    //CREATE
//    @Test
//    public void testCreateUser() {
//
//        when(userRepository.save(any(User.class))).thenReturn(new User("admin","admin"));
//
////        userController = new UserController(userRepository,encoder);
//        User actual = userController.createUser(new User("admin","admin"));
//
//        assertThat(actual.getUsername()).isEqualTo("admin");
//    }

    //CREATE
    @Test
    public void testSession() {

        when(auth.getPrincipal()).thenReturn(new User("admin","admin"));
        sessionController = new SessionController(userDetails,authenticator);
        User actual = sessionController.getLoggedInUserId(auth);
        assertThat(actual.getUsername()).isEqualTo("admin");
    }
/*
    //READ
    @Test
    public void testGetCustomerById() {

        //MockitoAnnotations.initMocks(this);

        when(customerRepository.findById(1L)).thenReturn(Optional.of(new Customer("jean-marc", "julien")));

        customerController = new CustomerController(customerRepository);
        Optional<Customer> actual = customerController.getCustomerById(1L);

        assertThat(actual.get().getFirstName()).isEqualTo("jean-marc");
    }


    //UPDATE
    @Test
    public void testUpdateCustomer() {

        //MockitoAnnotations.initMocks(this);

        when(customerRepository.findById(1L)).thenReturn(Optional.of(new Customer("jean-marc", "julien")));
        when(customerRepository.save(any(Customer.class))).thenReturn(new Customer("jean-marc","julien"));

        customerController = new CustomerController(customerRepository);
        Optional<Customer> actual = customerController.getCustomerById(1L);
        Customer actual2 = customerController.create(actual.get());

        assertThat(actual2.getFirstName()).isEqualTo("jean-marc");
    }

    //DELETE
    @Test
    public void testDeleteCustomer() {

        MockitoAnnotations.initMocks(this);

        Customer user = new Customer();
        user.setId(1L);
        user.setFirstName("jean-marc");
        user.setLastName("julien");

        when(customerRepository.findById(user.getId())).thenReturn(Optional.of(user));
        customerController = new CustomerController(customerRepository);

        Optional<Customer> actual = customerController.deleteCustomerById(user.getId());

        assertThat(actual.get().getFirstName()).isEqualTo("jean-marc");

    }

    //LIST
    @Test
    public void testListCustomers() {

        ArrayList<Customer> customers = new ArrayList<Customer>();
        customers.add(new Customer("jean-marc","julien"));
        customers.add(new Customer("hunt","applegate"));
        when(customerRepository.findAll()).thenReturn(customers);

        customerController = new CustomerController(customerRepository);
        Iterable<Customer> actual = customerController.getAll();

        assertThat(actual.spliterator().getExactSizeIfKnown()).isEqualTo(2);
    }
*/

}
