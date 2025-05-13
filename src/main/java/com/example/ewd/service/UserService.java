package com.example.ewd.service;

import com.example.ewd.entity.User;
import com.example.ewd.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public User findByUsername(String username) {
        return userRepo.findByUsername(username).orElse(null);
    }
}
