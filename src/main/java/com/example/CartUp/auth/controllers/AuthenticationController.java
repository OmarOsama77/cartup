package com.example.CartUp.auth.controllers;

import com.example.CartUp.auth.dto.response.DeleteUserResponse;
import com.example.CartUp.auth.dto.request.LoginRequest;
import com.example.CartUp.auth.dto.response.LoginResponse;
import com.example.CartUp.auth.dto.request.RefreshTokenRequest;
import com.example.CartUp.auth.dto.response.RefreshTokenResponse;
import com.example.CartUp.auth.dto.request.RegisterRequest;
import com.example.CartUp.auth.dto.response.RegisterResponse;
import com.example.CartUp.auth.entities.User;
import com.example.CartUp.auth.enums.Role;
import com.example.CartUp.auth.services.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
        return ResponseEntity.ok(authenticationService.register(request, Role.USER));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request){
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshTokenResponse> refresh(@RequestBody RefreshTokenRequest request){
        return ResponseEntity.ok(authenticationService.refreshToken(request));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<DeleteUserResponse> deleteUser(@PathVariable UUID id){
        return ResponseEntity.ok(authenticationService.deleteUser(id));
    }



    @PostMapping("/register/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RegisterResponse> adminRegister(
            @Valid @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request, Role.ADMIN));
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUser(
            @AuthenticationPrincipal User user
    ){
        authenticationService.deleteUser(user.getId());
        return ResponseEntity.noContent().build();
    }

}
