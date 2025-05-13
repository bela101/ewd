package com.example.ewd;

import com.example.ewd.entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void getId() {
        User user = new User();
        user.setId(1L);
        assertEquals(1L, user.getId());
    }

    @Test
    void getUsername() {
    }

    @Test
    void getEmail() {
    }

    @Test
    void getPassword() {
    }

    @Test
    void setId() {
    }

    @Test
    void setUsername() {
    }

    @Test
    void setEmail() {
    }

    @Test
    void setPassword() {
    }
}