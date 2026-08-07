package com.example.CartUp.auth.services;

import com.example.CartUp.auth.dtos.deleteuser.DeleteUserResponse;
import com.example.CartUp.auth.dtos.login.LoginRequest;
import com.example.CartUp.auth.dtos.login.LoginResponse;
import com.example.CartUp.auth.dtos.refresh_token.RefreshTokenRequest;
import com.example.CartUp.auth.dtos.refresh_token.RefreshTokenResponse;
import com.example.CartUp.auth.dtos.register.RegisterRequest;
import com.example.CartUp.auth.dtos.register.RegisterResponse;
import com.example.CartUp.auth.exceptions.InvalidRefreshTokenException;
import com.example.CartUp.auth.exceptions.LoginFailedException;
import com.example.CartUp.auth.entities.User;
import com.example.CartUp.auth.enums.Role;
import com.example.CartUp.auth.exceptions.UserNotFoundException;
import com.example.CartUp.auth.repositories.UserRepository;
import com.example.CartUp.auth.security.JwtService;
import com.example.CartUp.auth.exceptions.UserAlreadyExistException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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



    public RegisterResponse register(RegisterRequest request,Role role) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistException(request.getEmail());
        }
        User user = User.builder()
                .firstName(request.getFirstName())
                .secondName(request.getSecondName())
                .email(request.getEmail())
                .role(role)
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(user);
        return RegisterResponse.builder().message("Account added Successfully").build();
    }


    public LoginResponse login(LoginRequest request) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

            String accessToken = jwtService.generateToken(request.getEmail());

            String refreshToken = refreshTokenService.createRefreshToken(request.getEmail());
            return LoginResponse.builder().accessToken(accessToken).refreshToken(refreshToken).build();
        } catch (Exception e) {
            throw new LoginFailedException("Invalid email or password");
        }
    }


    public RefreshTokenResponse refreshToken(RefreshTokenRequest request) {
        //either return a new access token or deny the current refreshToken
        boolean isRefreshTokenExpired = refreshTokenService.isRefreshTokenExpired(request.getToken());
        if (isRefreshTokenExpired) {
            throw new InvalidRefreshTokenException();
        } else {
            UUID userId = refreshTokenService.extractUserIdFromToken(request.getToken());
            String userEmail =  userRepository.findUserEmailById(userId).orElseThrow(()-> new UsernameNotFoundException(""));
            String accessToken = jwtService.generateToken(userEmail);
            return RefreshTokenResponse.builder().accessToken(accessToken).refreshToken(request.getToken()).build();
        }
    }


    public DeleteUserResponse deleteUser(UUID userId) {
        //First make sure this user is in db
        if(!userRepository.existsById(userId)){
            throw new UserNotFoundException();
        }
        userRepository.deleteById(userId);

        return DeleteUserResponse.builder().message("User deleted successfully").build();
    }

}
