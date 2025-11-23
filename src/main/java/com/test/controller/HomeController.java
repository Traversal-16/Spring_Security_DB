package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.model.User;
import com.test.repository.UserRepository;

@RestController

public class HomeController 
{
	@Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;


    // ===========================
    // REGISTER NEW USER
    // ===========================
    @PostMapping("/register")
    public String register(@RequestBody User user) {

        // encode password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // set default role
        if (user.getRole() == null) {
            user.setRole("ROLE_USER");
        }

        repo.save(user);
        return "User registered successfully!";
    }


    // ===========================
    // LOGIN USER
    // ===========================
    @PostMapping("/login")
    public String login(@RequestBody User user) {

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );

        if (auth.isAuthenticated()) {
            return "Login successful!";
        } else {
            return "Invalid credentials!";
        }
    }


    // ===========================
    // SECURED ENDPOINT (test)
    // ===========================
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome! You are inside a secured endpoint.";
    }
}
