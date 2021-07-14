package com.example.invoicify;

import com.galvanize.invoicify.controllers.SessionController;
import com.galvanize.invoicify.models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SessionControllerTest {
    @Mock
    private SessionController sessionController;
    @Mock
    private UserDetailsService userDetails;
    @Mock
    private UserDetails details;
    @Mock
    private AuthenticationManager authenticator;
    @Mock
    Authentication auth;    //CREATE


    @Test
    public void testSession() {
        when(auth.getPrincipal()).thenReturn(new User("admin", "admin"));
        sessionController = new SessionController(userDetails, authenticator);
        User actual = sessionController.getLoggedInUserId(auth);
        assertThat(actual.getUsername()).isEqualTo("admin");
    }

    @Test
    public void testLoginSession() {
        UserDetails details2 = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public String getPassword() {
                return null;
            }

            @Override
            public String getUsername() {
                return "admin";
            }

            @Override
            public boolean isAccountNonExpired() {
                return false;
            }

            @Override
            public boolean isAccountNonLocked() {
                return false;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return false;
            }

            @Override
            public boolean isEnabled() {
                return false;
            }
        };
        when(userDetails.loadUserByUsername(any(String.class)))
                .thenReturn(details2);
        sessionController = new SessionController(userDetails, authenticator);
        User user = new User("admin", "admin");
        UserDetails actual = sessionController.login(user);
        assertThat(actual.getUsername()).isEqualTo("admin");
    }
/*
    @Test
    public void testSessionLogIn() {
        User user = new User("admin","admin");
        when(sessionController.login(user)
        ).thenReturn(details);        sessionController = new SessionController(userDetails,authenticator);
        User actual = sessionController.getLoggedInUserId(auth);        assertThat(actual.getId()).isEqualTo(1);
//      assertThat(actual.getPassword()).isEqualTo(1);
        assertThat(actual.getUsername()).isEqualTo("admin");
        assertThat(actual.isAccountNonExpired()).isEqualTo(true);
        assertThat(actual.isAccountNonLocked()).isEqualTo(true);
        assertThat(actual.getAuthorities()).isEqualTo(null);
        assertThat(actual.isEnabled()).isEqualTo(true);
    }
*/
}