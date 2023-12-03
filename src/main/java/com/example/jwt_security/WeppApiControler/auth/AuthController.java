package com.example.jwt_security.WeppApiControler.auth;

import com.example.jwt_security.Business.Abstracts.AuthService;
import com.example.jwt_security.Entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping(value = "/auth")
    public ResponseEntity<Users> createUser(@RequestBody Users dto) {
        return new ResponseEntity<>(service.getAuthService().createuser(dto), HttpStatus.CREATED);
    }
/*
    @PostMapping(value = "/auth/authenticate")
    public ResponseEntity<TokenResponse> authenticate(@RequestBody Login dto) {
        return new ResponseEntity<>(service.getAuthService().authenticate(dto), HttpStatus.OK);
    }*/
}