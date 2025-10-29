package com.chakray.userapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

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
        for (var user : users) {
            // Correct types
            assertTrue(user.getId() instanceof UUID, "The user ID is not a UUID");
            assertTrue(user.getEmail() instanceof String, "The user email is not a String");
            assertTrue(user.getName() instanceof String, "The user name is not a String");
            assertTrue(user.getPhone() instanceof String, "The user phone is not a String");
            assertTrue(user.getPassword() instanceof String, "The user password is not a String");
            assertTrue(user.getTax_id() instanceof String, "The user tax ID is not a String");
            assertTrue(user.getCreated_at() instanceof String, "The user created at is not a String");
            assertTrue(user.getAddresses() instanceof java.util.ArrayList, "The user addresses list is not an ArrayList");
            // Correct values
            assertNotNull(user.getId(), "The user ID is null");
            assertNotNull(user.getEmail(), "The user email is null");
            assertNotNull(user.getName(), "The user name is null");
            assertNotNull(user.getPhone(), "The user phone is null");
            assertNotNull(user.getPassword(), "The user password is null");
            assertNotNull(user.getTax_id(), "The user tax ID is null");
            assertNotNull(user.getCreated_at(), "The user created at is null");
            assertNotNull(user.getAddresses(), "The user addresses list is null");
            assertFalse(user.getAddresses().isEmpty(), "The user addresses list is empty");

        }
    }
}
