package com.chakray.userapi.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.junit.jupiter.api.Test;

public class UserTest {
    @Test
    public void testUserModel() throws Exception {
        // Arrange
        UUID id = UUID.randomUUID();
        String email = "test@test.com";
        String name = "Test";
        String phone = "123456789";
        String password = "password";
        String taxId = "123456789";
        String createdAt = "2022-01-01";
        // Act
        var user = new User(id, email, name, phone, password, taxId, createdAt);
        // Assert
        assertEquals(id, user.getId(), "The user ID is not the same");
        assertEquals(email, user.getEmail(), "The user email is not the same");
        assertEquals(name, user.getName(), "The user name is not the same");
        assertEquals(phone, user.getPhone(), "The user phone is not the same");
        assertEquals(password, user.getPassword(), "The user password is not the same");
        assertEquals(taxId, user.getTax_id(), "The user tax ID is not the same");
        assertEquals(createdAt, user.getCreated_at(), "The user created at is not the same");
        assertNotNull(user.getAddresses(), "The user addresses list is null");
        assertEquals(0, user.getAddresses().size(), "The user addresses list has the different number of addresses");
    }

    @Test
    public void testAddAddress() throws Exception {
        // Arrange
        UUID id = UUID.randomUUID();
        String email = "test@test.com";
        String name = "Test";
        String phone = "123456789";
        String password = "password";
        String taxId = "123456789";
        String createdAt = "2022-01-01";
        var user = new User(id, email, name, phone, password, taxId, createdAt);
        // Act
        var address = new Address(1, "test", "test", "UK");
        user.addAddress(address);
        // Assert
        assertEquals(1, user.getAddresses().size(), "The user addresses list has the different number of addresses");
        assertEquals(address, user.getAddresses().get(0), "The user addresses list has the different address");
    }
}
