package com.example.CartUp.auth.services.impl;

import com.example.CartUp.auth.dtos.register.RegisterRequest;
import com.example.CartUp.auth.dtos.register.RegisterResponse;
import com.example.CartUp.auth.entities.User;
import com.example.CartUp.auth.enums.Role;
import com.example.CartUp.auth.exceptions.UserAlreadyExistException;
import com.example.CartUp.auth.repositories.UserRepository;
import com.example.CartUp.auth.services.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @Override
    public RegisterResponse registerAdmin(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistException(request.getEmail());
        }
        User user = User.builder()
                .firstName(request.getFirstName())
                .secondName(request.getSecondName())
                .email(request.getEmail())
                .role(Role.ADMIN)
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(user);
        return RegisterResponse.builder().message("Account added Successfully").build();
    }

}
