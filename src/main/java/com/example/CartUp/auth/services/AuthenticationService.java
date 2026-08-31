package com.example.CartUp.auth.services;

import com.example.CartUp.auth.dto.request.LoginRequest;
import com.example.CartUp.auth.dto.request.RefreshTokenRequest;
import com.example.CartUp.auth.dto.request.RegisterRequest;
import com.example.CartUp.auth.dto.response.GetUserResponse;
import com.example.CartUp.auth.dto.response.LoginResponse;
import com.example.CartUp.auth.dto.response.RefreshTokenResponse;
import com.example.CartUp.auth.dto.response.RegisterResponse;
import com.example.CartUp.auth.entities.User;
import com.example.CartUp.auth.enums.Role;
import com.example.CartUp.auth.mappers.AuthMappers;
import com.example.CartUp.auth.repositories.UserRepository;
import com.example.CartUp.auth.security.JwtService;
import com.example.CartUp.shared.exceptions.ApplicationException;
import com.example.CartUp.shared.exceptions.enums.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final UserRepository userRepository;


    public RegisterResponse register(RegisterRequest request, Role role) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new ApplicationException(ErrorCode.USER_ALREADY_EXISTS);
        }
        User user = User.builder()
                .firstName(request.getFirstName())
                .secondName(request.getSecondName())
                .email(request.getEmail())
                .role(role)
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(user);
        return AuthMappers.toRegisterResponse(user.getId());
    }


    public LoginResponse login(LoginRequest request) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

            String accessToken = jwtService.generateToken(request.getEmail());

            String refreshToken = refreshTokenService.createRefreshToken(request.getEmail());
            return AuthMappers.toLoginResponse(accessToken, refreshToken);
        } catch (Exception e) {
            throw new ApplicationException(ErrorCode.LOGIN_FAILED);
        }
    }


    public RefreshTokenResponse refreshToken(RefreshTokenRequest request) {
        //either return a new access token or deny the current refreshToken
        boolean isRefreshTokenExpired = refreshTokenService.isRefreshTokenExpiredAndCleanup(request.getToken());
        if (isRefreshTokenExpired) {
            throw new ApplicationException(ErrorCode.INVALID_REFRESH_TOKEN);
        } else {
            UUID userId = refreshTokenService.extractUserIdFromToken(request.getToken());
            String userEmail = userRepository.findUserEmailById(userId).orElseThrow(() -> new ApplicationException(ErrorCode.EMAIL_NOT_FOUND));
            String accessToken = jwtService.generateToken(userEmail);
            return AuthMappers.toRefreshTokenResponse(accessToken, request.getToken());
        }
    }


    public void deleteUser(User user) {
        if (!userRepository.existsById(user.getId())) {
            throw new ApplicationException(ErrorCode.USER_NOT_FOUND);
        }
        userRepository.deleteById(user.getId());
    }
    public GetUserResponse getUser(UUID userId){
        User user  = userRepository.findById(userId)
                .orElseThrow(()->new ApplicationException(ErrorCode.USER_NOT_FOUND));
        if(!user.getId().equals(userId)){
           throw  new ApplicationException(ErrorCode.USER_NOT_FOUND);
        }
        return AuthMappers.toGetUserResponse(user);
    }

}
