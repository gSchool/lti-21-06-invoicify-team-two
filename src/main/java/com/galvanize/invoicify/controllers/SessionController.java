package com.galvanize.invoicify.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galvanize.invoicify.models.User;

@RestController
@RequestMapping("/api/session")
public class SessionController {

	private UserDetailsService userDetails;
	private AuthenticationManager authenticator;

	public SessionController(UserDetailsService userDetails, AuthenticationManager authenticator) {
		this.userDetails = userDetails;
		this.authenticator = authenticator;
	}

	/**
	 * <p> Gets currently logged in user. </p>
	 * @param auth
	 * @return logged in User
	 */
	@GetMapping
	public User getLoggedInUserId(Authentication auth) {
		if (auth != null) {
			return (User) auth.getPrincipal();
		}
		return null;
	}

	/**
	 * <p> Logs in a User based on username and password. </p>
	 * @param user
	 * @return logged in User
	 */
	@PutMapping
	public UserDetails login(@RequestBody User user) {
		UserDetails details = userDetails.loadUserByUsername(user.getUsername());
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(details, user.getPassword(),
				details.getAuthorities());
		authenticator.authenticate(token);
		if (token.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(token);
		}
		return details;
	}

	/**
	 * <p> Logs out the current User. </p>
	 * @param auth
	 * @param request
	 * @param response
	 * @return true if successful
	 */
	@DeleteMapping
	public Boolean logout(Authentication auth, HttpServletRequest request, HttpServletResponse response) {
		new SecurityContextLogoutHandler().logout(request, response, auth);
		return true;
	}

}
