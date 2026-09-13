package com.FirstProject.StudentManagement.controllers;

import com.FirstProject.StudentManagement.entity.Users;
import com.FirstProject.StudentManagement.utils.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class JWTController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Users user) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getUserPassword()));
        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(user.getUserName())
                .password(user.getUserPassword())
                .roles(user.getRole())
                .build();

        String jwtToken = jwtUtil.generateToken(userDetails);
        return new ResponseEntity<>(jwtToken, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public String signup() {
        return "Signup successful";
    }
}