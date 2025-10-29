package com.chakray.userapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.chakray.userapi.models.Address;
import com.chakray.userapi.models.User;

@Service
public class UserService {
    private List<User> users = new ArrayList<>();

    public UserService() {
        // Set the users list
        users = createUsers();
    }

    // ----- Public methods -----

    /**
     * Gets all users
     * 
     * @param sortedBy The field to sort the users by
     * @param filter   The filter to apply to the users
     * @return List of users
     */
    public List<User> getAllUsers() {
        // Return the users list
        return users;
    }

    // ----- Private methods -----

    /**
     * Creates a list of users
     * 
     * @return List of users
     */
    private static List<User> createUsers() {
        List<User> users = new ArrayList<>();
        // Create first user
        var user = new User(
                UUID.randomUUID(),
                "Thomas@mail.com",
                "ThomasTest",
                "11 1111 1111 111",
                "password1",
                "123456789",
                "2005-01-01");
        // Create first address for the user
        var address = new Address(
                1,
                "workaddress",
                "street No. 1",
                "UK");
        // Add the address to the user
        user.addAddress(address);
        // Create second address for the user
        address = new Address(
                2,
                "homeaddress",
                "street No. 2",
                "AU");
        // Add the address to the user
        user.addAddress(address);
        // Add the user to the list of users
        users.add(user);

        // Create second user
        user = new User(
                UUID.randomUUID(),
                "John@mail.com",
                "JohnTest",
                "22 222 222 22",
                "password2",
                "123456789",
                "2010-01-01");
        // Create first address for the user
        address = new Address(
                1,
                "workaddress",
                "street No. 1",
                "UK");
        // Add the address to the user
        user.addAddress(address);
        // Create second address for the user
        address = new Address(
                2,
                "homeaddress",
                "street No. 2",
                "AU");
        // Add the address to the user
        user.addAddress(address);
        // Add the user to the list of users
        users.add(user);

        // Create third user
        user = new User(
                UUID.randomUUID(),
                "Mary@mail.com",
                "MaryTest",
                "33 333 333 33",
                "password3",
                "123456789",
                "2015-01-01");
        // Create first address for the user
        address = new Address(
                1,
                "workaddress",
                "street No. 1",
                "UK");
        // Add the address to the user
        user.addAddress(address);
        // Create second address for the user
        address = new Address(
                2,
                "homeaddress",
                "street No. 2",
                "AU");
        // Add the address to the user
        user.addAddress(address);
        // Add the user to the list of users
        users.add(user);
        return users;
    }

}
