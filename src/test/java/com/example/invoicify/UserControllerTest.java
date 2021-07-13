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

    //CREATE
    @Test
    public void testSession() {

        when(auth.getPrincipal()).thenReturn(new User("admin","admin"));
        sessionController = new SessionController(userDetails,authenticator);
        User actual = sessionController.getLoggedInUserId(auth);
        assertThat(actual.getUsername()).isEqualTo("admin");
    }
}
