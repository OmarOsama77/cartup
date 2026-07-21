package com.example.CartUp.auth.services.impl;

import com.example.CartUp.auth.entities.User;
import com.example.CartUp.auth.repositories.UserRepository;
import com.example.CartUp.auth.services.UserService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public String findUserEmailById(UUID id) {
        return userRepository.findUserEmailById(id).orElseThrow();
    }

    @Override
    public boolean isUserExistByEmail(String email) {
       return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }


}
