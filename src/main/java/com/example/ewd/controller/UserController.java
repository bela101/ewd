package com.example.ewd.controller;

import com.example.ewd.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/current/id")
    public ResponseEntity<Long> getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof User) {
            return ResponseEntity.ok(((User) auth.getPrincipal()).getId());
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/current/username")
    public ResponseEntity<String> getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof User) {
            return ResponseEntity.ok(((User) auth.getPrincipal()).getUsername());
        }
        return ResponseEntity.badRequest().build();
    }
}