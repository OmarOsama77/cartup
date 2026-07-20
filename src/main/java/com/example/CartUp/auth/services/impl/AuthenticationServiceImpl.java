package com.example.CartUp.auth.services.impl;

import com.example.CartUp.auth.dtos.login.LoginRequest;
import com.example.CartUp.auth.dtos.login.LoginResponse;
import com.example.CartUp.auth.dtos.refresh_token.RefreshTokenDto;
import com.example.CartUp.auth.dtos.register.RegisterRequest;
import com.example.CartUp.auth.dtos.register.RegisterResponse;
import com.example.CartUp.auth.entities.RefreshToken;
import com.example.CartUp.auth.exceptions.LoginFailedException;
import com.example.CartUp.shared.entities.User;
import com.example.CartUp.auth.enums.Role;
import com.example.CartUp.auth.repositories.UserRepository;
import com.example.CartUp.auth.security.JwtService;
import com.example.CartUp.auth.services.AuthenticationService;
import com.example.CartUp.auth.services.RefreshTokenService;
import com.example.CartUp.auth.exceptions.UserAlreadyExistException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager,JwtService jwtService,RefreshTokenService refreshTokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
    }

    @Override
    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistException(request.getEmail());
        }
        User user = User.builder()
                .firstName(request.getFirstName())
                .secondName(request.getSecondName())
                .email(request.getEmail())
                .role(Role.USER)
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(user);
        return RegisterResponse.builder().message("Account added Successfully").build();
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));

            String accessToken = jwtService.generateToken(request.getEmail());

            String refreshToken = refreshTokenService.createRefreshToken(request.getEmail());
            return LoginResponse.builder().accessToken(accessToken).refreshToken(refreshToken).build();
        } catch (Exception e) {
            throw new LoginFailedException("Invalid email or password");
        }
    }

    @Override
    public RefreshTokenDto refreshToken(RefreshTokenDto request) {
        RefreshToken refreshToken = refreshTokenService.verifyExpiration(request.getToken());
      return RefreshTokenDto.builder().token(refreshToken.getToken()).build();
    }

}
