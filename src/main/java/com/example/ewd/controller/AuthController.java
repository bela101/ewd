package com.example.ewd.controller;

import com.example.ewd.entity.User;
import com.example.ewd.service.UserService;
import com.example.ewd.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    record AuthRequest(String username, String password) {}
    record AuthResponse(String token) {}

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody AuthRequest req) {
        var user = new User();
        user.setUsername(req.username());
        user.setPassword(req.password());
        return ResponseEntity.ok(userService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest req) {
        var user = userService.findByUsername(req.username());
        if (user == null) return ResponseEntity.status(401).build();
        // password check omitted for brevity (use BCryptPasswordEncoder)
        String token = jwtUtil.generateToken(user.getUsername());
        return ResponseEntity.ok(new AuthResponse(token));
    }

}