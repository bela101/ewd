package com.example.ewd.controller;

import com.example.ewd.entity.User;
import com.example.ewd.service.UserService;
import com.example.ewd.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService,
                          JwtUtil jwtUtil,
                          PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    record AuthRequest(String username, String password) {}
    record AuthResponse(String token) {}

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        Credentials creds = extractBasicCredentials(authHeader);
        if (creds == null) {
            return ResponseEntity.status(401).body("Missing or invalid Basic Auth header");
        }

        // Check for existing user
        if (userService.findByUsername(creds.username) != null) {
            return ResponseEntity.status(409).body("Username already taken");
        }

        // Create & save new user with hashed password
        User user = new User();
        user.setUsername(creds.username);
        user.setPassword(creds.password);
        User saved = userService.register(user);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        Credentials creds = extractBasicCredentials(authHeader);
        if (creds == null) {
            return ResponseEntity.status(401).body("Missing or invalid Basic Auth header");
        }

        User user = userService.findByUsername(creds.username);
        if (user == null || !passwordEncoder.matches(creds.password, user.getPassword())) {
            return ResponseEntity.status(401).build();
        }

        String token = jwtUtil.generateToken(user.getUsername());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    //     Helper to parse and decode Basic Auth header into username/password.
    private Credentials extractBasicCredentials(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            return null;
        }
        try {
            String base64 = authHeader.substring(6);
            byte[] decoded = Base64.getDecoder().decode(base64);
            String[] parts = new String(decoded, StandardCharsets.UTF_8).split(":", 2);
            if (parts.length != 2 || parts[0].isEmpty()) {
                return null;
            }
            return new Credentials(parts[0], parts[1]);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private record Credentials(String username, String password) {}

}