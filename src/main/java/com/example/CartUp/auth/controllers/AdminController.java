package com.example.CartUp.auth.controllers;

import com.example.CartUp.auth.dtos.register.RegisterRequest;
import com.example.CartUp.auth.dtos.register.RegisterResponse;
import com.example.CartUp.auth.services.AdminService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RolesAllowed("ADMIN")
@RestController
public class AdminController {
    private final AdminService service;
    public AdminController(AdminService service){
        this.service = service;
    }

    @PostMapping("/admin/register")
    public ResponseEntity<RegisterResponse> adminRegister(
            @RequestBody RegisterRequest request
            ){
       return ResponseEntity.ok(service.registerAdmin(request));
    }
}
