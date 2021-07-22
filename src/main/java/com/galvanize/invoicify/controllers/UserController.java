package com.galvanize.invoicify.controllers;

import com.galvanize.invoicify.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.galvanize.invoicify.models.User;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder encoder;

	public UserController(UserRepository userRepository, PasswordEncoder encoder) {
		this.userRepository = userRepository;
		this.encoder = encoder;
	}

//	@PutMapping("{id}")
//	public User updateUser(Authentication auth, @RequestBody User user, @PathVariable Long id) {
//		Optional<User> currentUserData = this.userRepository.findById(id);
//		user.setId(currentUserData.get().getId());
//
//		if (user.getPassword() == null) {
//			user.setPassword(currentUserData.get().getPassword());
//		} else {
//			String encryptedPassword = encoder.encode(user.getPassword());
//			user.setPassword(encryptedPassword);
//		}
//
//		return userRepository.save(user);
//	}

	@PostMapping
	public User createUser(@RequestBody User user) {
		String password = user.getPassword();
		String encryptedPassword = encoder.encode(password);
		user.setPassword(encryptedPassword);
		userRepository.save(user);
		return user;
	}

	@GetMapping
	public Iterable<User> listUsers() {
		return userRepository.findAll();
	}

	@PutMapping("/{id}")
	public User updateUser(Authentication auth, @RequestBody User user, @PathVariable Long id) {
		User currentUserData = this.userRepository.findById(id).get();
		currentUserData.setUsername(user.getUsername());
		String encryptedPassword = encoder.encode(user.getPassword());
		currentUserData.setPassword(encryptedPassword);

		return userRepository.save(currentUserData);
	}

}