package com.example.CartUp.auth.services;

import com.example.CartUp.auth.dtos.register.RegisterRequest;
import com.example.CartUp.auth.dtos.register.RegisterResponse;

public interface AdminService {
    public RegisterResponse registerAdmin(RegisterRequest request);
}
