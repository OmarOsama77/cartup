package com.example.CartUp.auth.controllers;

import com.example.CartUp.auth.dtos.delete_user.DeleteUserResponse;
import com.example.CartUp.auth.dtos.login.LoginRequest;
import com.example.CartUp.auth.dtos.login.LoginResponse;
import com.example.CartUp.auth.dtos.register.RegisterRequest;
import com.example.CartUp.auth.dtos.register.RegisterResponse;
import com.example.CartUp.auth.enums.Role;
import com.example.CartUp.auth.services.AuthenticationService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@AllArgsConstructor
public class AdminController {
    private final AuthenticationService service;


    @PostMapping("/admin/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RegisterResponse> adminRegister(
            @Valid @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request, Role.ADMIN));
    }

    @PostMapping("/admin/login")
    public ResponseEntity<LoginResponse> adminLogin(
            @Valid @RequestBody LoginRequest loginRequest
    ) {
        return ResponseEntity.ok(service.login(loginRequest));
    }


    @DeleteMapping("/admin/register/id")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DeleteUserResponse> deleteAdmin(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(service.deleteUser(id));
    }

}
