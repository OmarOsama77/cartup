package com.example.CartUp.auth.services;

import com.example.CartUp.auth.entities.User;

import java.util.UUID;

public interface UserService {

    String findUserEmailById(UUID id);
    boolean isUserExistByEmail(String email);
    void saveUser(User user);
}
