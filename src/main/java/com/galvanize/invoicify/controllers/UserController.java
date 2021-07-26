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

    /**
     * <p> Creates a new User based on a username and an encrypted password. </p>
     *
     * @param user
     * @return created User
     */
    @PostMapping
    public User createUser(@RequestBody User user) {
        String password = user.getPassword();
        String encryptedPassword = encoder.encode(password);
        user.setPassword(encryptedPassword);
        userRepository.save(user);
        return user;
    }

    /**
     * <p> Returns all users. </p>
     *
     * @return list of User
     */
    @GetMapping
    public Iterable<User> listUsers() {
        return userRepository.findAll();
    }

    /**
     * <p> Finds a User based on ID and updates username/password. </p>
     *
     * @param auth
     * @param user
     * @param id
     * @return updated User
     */
    @PutMapping("/{id}")
    public User updateUser(Authentication auth, @RequestBody User user, @PathVariable Long id) {
        User currentUserData = this.userRepository.findById(id).get();
        currentUserData.setUsername(user.getUsername());
        String encryptedPassword = encoder.encode(user.getPassword());
        currentUserData.setPassword(encryptedPassword);
        return userRepository.save(currentUserData);
    }

    /**
     * <p> Returns a User based on an ID. </p>
     *
     * @param auth
     * @param id
     * @return User
     */
    @GetMapping("/{id}")
    public User getUserById(Authentication auth, @PathVariable Long id) {
        return this.userRepository.findById(id).get();
    }
}