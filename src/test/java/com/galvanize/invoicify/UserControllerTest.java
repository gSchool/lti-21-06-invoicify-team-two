package com.galvanize.invoicify;

import com.galvanize.invoicify.controllers.CompanyController;
import com.galvanize.invoicify.controllers.SessionController;
import com.galvanize.invoicify.controllers.UserController;
import com.galvanize.invoicify.models.Company;
import com.galvanize.invoicify.models.User;
import com.galvanize.invoicify.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
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

    //CREATE
    @Test
    public void testCreateSession() {
        when(auth.getPrincipal()).thenReturn(new User("admin", "admin"));
        sessionController = new SessionController(userDetails, authenticator);
        User actual = sessionController.getLoggedInUserId(auth);
        assertThat(actual.getUsername()).isEqualTo("admin");
    }

    //LIST
    @Test
    public void testListUsers() {
        ArrayList<User> users = new ArrayList<User>();
        users.add(new User());
        users.add(new User());
        when(userRepository.findAll()).thenReturn(users);
        userController = new UserController(userRepository, encoder);
        Iterable<User> actual = userController.listUsers();
        assertThat(actual.spliterator().getExactSizeIfKnown()).isEqualTo(2);
    }

    @Test
    public void testModifyUserCredentials() {
        User user = new User("admin", "admin");
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(user));
        user.setUsername("test");
        when(userRepository.save(any(User.class))).thenReturn(user);
        userController = new UserController(userRepository, encoder);
        User actual = userController.updateUser(auth, user, 1L);
        assertThat(actual.getUsername()).isEqualTo(user.getUsername());

    }

    @Test
    public void testUserGetById() {
        User user = new User("admin", "admin");
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(user));
        userController = new UserController(userRepository, encoder);
        User actual = userController.getUserById(auth, 1L);
        assertThat(actual).isEqualTo(user);

    }
}
