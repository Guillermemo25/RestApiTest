package com.chakray.userapi.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;

public class UserTest {
    @Test
    public void testUserModel() throws Exception {
        // Arrange
        String email = "test@test.com";
        String name = "Test";
        String phone = "1234567890";
        String password = "password";
        String taxId = "AARR990101XXX";
        // Act
        var user = new User(email, name, phone, password, taxId);
        // Assert
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
        assertEquals(email, user.getEmail(), "The user email is not the same");
        assertEquals(name, user.getName(), "The user name is not the same");
        assertEquals(phone, user.getPhone(), "The user phone is not the same");
        assertEquals(password, user.getPassword(), "The user password is not the same");
        assertEquals(taxId, user.getTax_id(), "The user tax ID is not the same");
        assertNotNull(user.getAddresses(), "The user addresses list is null");
        assertEquals(0, user.getAddresses().size(), "The user addresses list has the different number of addresses");
    }

    @Test
    public void testUserModel_phoneInvalid() throws Exception {
        // Arrange
        String email = "test@mail.com";
        String name = "testUser";
        String phone = "12345";
        String password = "password";
        String taxId = "AARR990101XXX";
        // Act & Assert
        try {
            var user = new User(email, name, phone, password, taxId);
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid phone number format", e.getMessage());
        }
    }

    @Test
    public void testUserModel_taxIdInvalid() throws Exception {
        // Arrange
        String email = "test@mail.com";
        String name = "testUser";
        String phone = "1234567890";
        String password = "password";
        String taxId = "INVALIDTAXID";
        // Act & Assert
        try {
            var user = new User(email, name, phone, password, taxId);
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid tax id format", e.getMessage());
        } 
    }

    @Test
    public void testAddAddress() throws Exception {
        // Arrange
        int id = 1;
        String email = "test@test.com";
        String name = "Test";
        String phone = "1234567890";
        String password = "password";
        String taxId = "AARR990101XXX";
        var user = new User(email, name, phone, password, taxId);
        // Act
        var address = new Address(id, "test", "test", "UK");
        user.addAddress(address);
        // Assert
        assertEquals(id, user.getAddresses().size(), "The user addresses list has the different number of addresses");
        assertEquals(address, user.getAddresses().get(0), "The user addresses list has the different address");
    }
}
