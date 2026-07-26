package com.example.CartUp.auth.controllers;

import com.example.CartUp.auth.dtos.login.LoginRequest;
import com.example.CartUp.auth.dtos.login.LoginResponse;
import com.example.CartUp.auth.dtos.refresh_token.RefreshTokenRequest;
import com.example.CartUp.auth.dtos.refresh_token.RefreshTokenResponse;
import com.example.CartUp.auth.dtos.register.RegisterRequest;
import com.example.CartUp.auth.dtos.register.RegisterResponse;
import com.example.CartUp.auth.services.AuthenticationService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(
           @Valid @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request){
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshTokenResponse> refresh(@RequestBody RefreshTokenRequest request){
        return ResponseEntity.ok(authenticationService.refreshToken(request));
    }


}
