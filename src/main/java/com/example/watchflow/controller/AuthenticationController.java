package com.example.watchflow.controller;


import com.example.watchflow.security.AuthenticationRequest;
import com.example.watchflow.security.AuthenticationRespone;
import com.example.watchflow.service.AuthenticationService;
import com.example.watchflow.security.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationRespone> register (
            @RequestBody RegisterRequest request
    ) throws ParseException {
        AuthenticationRespone auth = service.register(request);
        if (auth != null)
            return ResponseEntity.ok(auth);
        else
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "User already exists with this login or email");
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationRespone> authenticate (
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(service.authenticate(request));
    }

}
