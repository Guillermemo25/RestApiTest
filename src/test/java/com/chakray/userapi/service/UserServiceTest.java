package com.chakray.userapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class UserServiceTest {
    
    @Test
    public void testGetAllUsers() throws Exception {
        // Arrange
        var userService = new UserService();
        // Act
        var users = userService.getAllUsers();
        // Assert
        assertNotNull(users, "The users list is null");
        assertFalse(users.isEmpty(), "The users list is empty");
        assertEquals(3, users.size(), "The users list has the different number of users");
    }
}
