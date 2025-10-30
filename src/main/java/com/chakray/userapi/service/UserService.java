package com.chakray.userapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.chakray.userapi.models.Address;
import com.chakray.userapi.models.User;

@Service
public class UserService {
        private List<User> users = new ArrayList<>();
        public final String UserExists = "User already exists";
        public final String UserNotNull = "User cannot be null";

        /**
         * Constructor
         */
        public UserService() {
                // Set the users list
                users = createUsers();
        }

        // ----- Public methods -----

        /**
         * Adds a user to the users list
         * 
         * @param user The user to add
         * @return The user that was added
         */
        public User addUser(User user) throws Exception, IllegalArgumentException {
                // Check if the user is null
                if (user == null)
                        throw new IllegalArgumentException(UserNotNull);
                // Check if the user already exists
                ValidateExistsUser(user);
                try {
                        // Create a new user
                        var newUser = new User(
                                        user.getEmail(),
                                        user.getName(),
                                        user.getPhone(),
                                        user.getPassword(),
                                        user.getTax_id());
                        // Add addresses to the new user
                        for (Address address : user.getAddresses()) {
                                // Create a new address
                                var newAddress = new Address(
                                                address.getId(),
                                                address.getName(),
                                                address.getStreet(),
                                                address.getCountryCode());
                                // Add the address to the user
                                newUser.addAddress(newAddress);
                        }
                        // Add the user to the users list
                        users.add(newUser);
                        // Return the user
                        return newUser;
                } catch (Exception e) {
                        // Throw the exception
                        throw new Exception(e.getMessage());
                }
        }

        /**
         * Gets all users
         * 
         * @param sortedBy The field to sort the users by
         * @param filter   The filter to apply to the users
         * @return List of users
         */
        public List<User> getAllUsers(String sortedBy, String filter) throws Exception {
                try {
                        // TODO impplemtent sorting and filtering
                        // Return the users list
                        return users;
                }  catch (Exception e) {
                        // Throw the exception
                        throw new Exception(e.getMessage());
                }
        }

        /**
         * Gets a user by id
         * 
         * @param id The id of the user
         * @return The user founded
         */
        public User getUserById(UUID id) throws Exception {
                try {
                        // Check if the id is null
                        for (User user : users) {
                                if (user.getId().equals(id)) {
                                        return user;
                                }
                        }
                        // Return null
                        return null;
                }  catch (Exception e) {
                        // Throw the exception
                        throw new Exception(e.getMessage());
                }
        }

        /**
         * Updates a user in the users list
         * 
         * @param id    The id of the user to update
         * @param name  The new name of the user
         * @param email The new email of the user
         * @param phone The new phone of the user
         * @return The user that was updated
         */
        public User updateUser(
                        UUID id,
                        String name,
                        String email,
                        String phone) throws Exception {
                try {
                        // Get the user in the users list
                        var user = getUserById(id);
                        // Check if the user is null
                        if (user == null)
                                return null;
                        // Update the user in the users list
                        user.update(
                                name,
                                email,
                                phone);
                        // Return the user
                        return user;
                }  catch (Exception e) {
                        // Throw the exception
                        throw new Exception(e.getMessage());
                }
        }

        /**
         * Deletes a user from the users list
         * 
         * @param id The id of the user to delete
         * @return The user that was deleted
         */
        public User deleteUser(UUID id) throws Exception {
                try {
                        // Get the user in the users list
                        var user = getUserById(id);
                        // Validate if the user is not null
                        if (user != null) {
                                // Delete the user from the users list
                                users.remove(user);
                                // Return the user removed
                                return user;
                        }
                        // If the user was not found, return null 
                        return null;
                }  catch (Exception e) {
                        // Throw the exception
                        throw new Exception(e.getMessage());
                }
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
                                "Thomas@mail.com",
                                "ThomasTest",
                                "1111111111",
                                "password1",
                                "AARR112233XXX");
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
                                "John@mail.com",
                                "JohnTest",
                                "2222222222",
                                "password2",
                                "AARR222222XXX");
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
                                "Mary@mail.com",
                                "MaryTest",
                                "3333333333",
                                "password3",
                                "AARR333333XXX");
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

        private void ValidateExistsUser(User user) throws IllegalArgumentException {
                if (users.stream().anyMatch(u -> 
                Objects.equals(u.getTax_id(), user.getTax_id()) && 
                !Objects.equals(u.getId(), user.getId())))
                        throw new IllegalArgumentException(UserExists);
        }
}
