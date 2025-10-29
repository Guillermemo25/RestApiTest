package com.chakray.userapi.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AddressTest {
    
    @Test
    public void testAddressModel() throws Exception {
        // Arrange
        int id = 1;
        String name = "Test";
        String street = "Test";
        String countryCode = "UK";
        // Act
        var address = new Address(id, name, street, countryCode);
        // Assert
        // Correct types
        assertTrue((Object)address.getId() instanceof Integer, "The address ID is not an int");
        assertTrue(address.getName() instanceof String, "The address name is not a String");
        assertTrue(address.getStreet() instanceof String, "The address street is not a String");
        assertTrue(address.getCountryCode() instanceof String, "The address country code is not a String");
        // Correct values
        assertEquals(id, address.getId(), "The address ID is not the same");
        assertEquals(name, address.getName(), "The address name is not the same");
        assertEquals(street, address.getStreet(), "The address street is not the same");
        assertEquals(countryCode, address.getCountryCode(), "The address country code is not the same");
    }
}
