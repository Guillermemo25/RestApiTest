package com.chakray.userapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.chakray.userapi.models.User;

public class UserServiceTest {
    
    @Test
    public void testAddUser() throws Exception {
        // Arrange
        var userService = new UserService();
        var newUser = new User(
            "test@mail.com",
            "testUser",
            "1234567890",
            "password",
            "AARR990101XXX");
        // Act
        var addedUser = userService.addUser(newUser);
        // Assert
        assertNotNull(addedUser, "The added user is null");
        var user = userService.getUserById(addedUser.getId());
        assertNotNull(user, "The user is null");
        assertEquals(addedUser, user);
    }
    
    @Test
    public void testAddUser_UserDuplicated() throws Exception {
        // Arrange
        var userService = new UserService();
        var users = userService.getAllUsers(null, null);
        var existingUser = users.get(0);
        var duplicatedUser = new User(
            existingUser.getEmail(),
            "newUserName",
            "1111111111",
            "newPassword",
            "AARR990101XXX");
        // Act & Assert
        try {
            userService.addUser(duplicatedUser);
        } catch (IllegalArgumentException e) {
            assertEquals(userService.UserExists, e.getMessage());
        }
    }

    @Test
    public void testAddUser_NullUser() throws Exception {
        // Arrange
        var userService = new UserService();
        // Act & Assert
        try {
            userService.addUser(null);
        } catch (IllegalArgumentException e) {
            assertEquals(userService.UserNotNull, e.getMessage());
        }
    }
    
    @Test
    public void testGetAllUsers() throws Exception {
        // Arrange
        var userService = new UserService();
        // Act
        var users = userService.getAllUsers(null, null);
        // Assert
        assertNotNull(users, "The users list is null");
        assertFalse(users.isEmpty(), "The users list is empty");
        assertEquals(3, users.size(), "The users list has the different number of users");
    }

    @Test
    public void testGetUserById() throws Exception {
        // Arrange
        var userService = new UserService();
        var users = userService.getAllUsers(null, null);
        var existingUser = users.get(0);
        // Act
        var user = userService.getUserById(existingUser.getId());
        // Assert
        assertNotNull(user, "The user is null");
        assertEquals(existingUser, user, "The user is different from the existing user");
    }

    @Test
    public void testGetUserById_NotFound() throws Exception {
        // Arrange
        var userService = new UserService();
        var nonExistingUserId = UUID.randomUUID();
        // Act
        var user = userService.getUserById(nonExistingUserId);
        // Assert
        assertNull(user, "The user is not null");
    }

    @Test
    public void testUpdateUser() throws Exception {
        // Arrange
        var userService = new UserService();
        var users = userService.getAllUsers(null, null);
        var existingUser = users.get(0);
        var updatedName = "updatedName";
        var updatedEmail = "updateTest@mail.com";
        var updatedPhone = "1111111111";
        // Act
        var user = userService.updateUser(
            existingUser.getId(),
            updatedName, 
            updatedEmail, 
            updatedPhone);
        // Assert
        assertNotNull(user, "The updated user is null");
        assertEquals(updatedName, user.getName(), "The user name was not updated");
        assertEquals(updatedEmail, user.getEmail(), "The user email was not updated");
        assertEquals(updatedPhone, user.getPhone(), "The user phone was not updated");
    }

    @Test
    public void testUpdateUser_NotFound() throws Exception {
        // Arrange
        var userService = new UserService();
        var nonExistingUserId = UUID.randomUUID();
        // Act
        var user = userService.updateUser(
            nonExistingUserId,
            "name", 
            "test@mail.com",
            "1111111111");
        // Assert
        assertNull(user, "The user is not null");
    }

    @Test
    public void testDeleteUser() throws Exception {
        // Arrange
        var userService = new UserService();
        var users = userService.getAllUsers(null, null);
        var existingUser = users.get(0);
        // Act
        var deletedUser = userService.deleteUser(existingUser.getId());
        // Assert
        assertNotNull(deletedUser, "The deleted user is null");
        assertEquals(existingUser, deletedUser, "The deleted user is different from the existing user");
        var user = userService.getUserById(existingUser.getId());
        assertNull(user, "The user was not deleted");
    }

    @Test
    public void testDeleteUser_NotFound() throws Exception {
        // Arrange
        var userService = new UserService();
        var nonExistingUserId = UUID.randomUUID();
        // Act
        var deletedUser = userService.deleteUser(nonExistingUserId);
        // Assert
        assertNull(deletedUser, "The deleted user is not null");
    }
}
