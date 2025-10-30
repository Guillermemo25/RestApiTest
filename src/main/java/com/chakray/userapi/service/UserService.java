package com.chakray.userapi.service;

import java.util.ArrayList;
import java.util.Comparator;
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
                        // Get the users list
                        List<User> users = this.users.stream().toList();
                        // Check if the filter is not null or empty
                        if (filter != null && !filter.isEmpty()) {
                                users = getUserByFilter(filter);
                        }

                        // Check if the sortedBy is not null or empty
                        if (sortedBy != null && !sortedBy.isEmpty()) {
                                users = getUsersSortedBy(sortedBy);
                        }
                        // Return the users list
                        return users;
                } catch (Exception e) {
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
                                // Check if the user id matches the id
                                if (user.getId().equals(id)) {
                                        // Return the user founded
                                        return user;
                                }
                        }
                        // Return null
                        return null;
                } catch (Exception e) {
                        // Throw the exception
                        throw new Exception(e.getMessage());
                }
        }

        /**
         * Gets a user by taxId and userName
         * 
         * @param taxId    The taxId of the user
         * @param userName The userName of the user
         * @return The user founded
         */
        public User getUserLogin(String taxId, String userName) throws Exception {
                try {
                        // Check if the taxId is null
                        for (User user : users) {
                                // Check if the user taxId matches the taxId
                                if (user.getTax_id().equals(taxId) && user.getName().equals(userName)) {
                                        // Return the user founded
                                        return user;
                                }
                        }
                        // Return null
                        return null;
                } catch (Exception e) {
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
                } catch (Exception e) {
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
                } catch (Exception e) {
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

        /**
         * Validates if a user already exists in the users list
         * 
         * @param user The user to validate
         * @throws IllegalArgumentException if the user already exists
         */
        private void ValidateExistsUser(User user) throws IllegalArgumentException {
                if (users.stream().anyMatch(u -> Objects.equals(u.getTax_id(), user.getTax_id()) &&
                                !Objects.equals(u.getId(), user.getId())))
                        throw new IllegalArgumentException(UserExists);
        }

        /**
         * Gets the filter values from the filter string
         * 
         * @param filter The filter string
         * @return The filter values
         */
        private String[] GetFilterValues(String filter) {
                // Check if the filter is null or empty
                if (filter == null || filter.isEmpty())
                        return new String[0];

                String regex = "^(email|id|name|phone|tax_id|created_at)\\+(co|eq|sw|ew)\\+(.+)$";
                // Check if the filter is a valid filter
                if (!filter.matches(regex))
                        throw new IllegalArgumentException(
                                        "Invalid filter. The stucture must be [field]+[operator]+[value]");

                // Split the filter by +
                String[] parts = filter.split("\\+");
                // Remove the empty strings
                return parts;
        }

        /**
         * Gets the users sorted by a field
         * 
         * @param sortedBy The field to sort the users by
         * @return The sorted users list
         */
        private List<User> getUsersSortedBy(String sortedBy) {
                // Sort the users list
                switch (sortedBy) {
                        case "email":
                                return users.stream()
                                                .sorted(Comparator.comparing(User::getEmail))
                                                .toList();
                        case "name":
                                return users.stream()
                                                .sorted(Comparator.comparing(User::getName))
                                                .toList();
                        case "phone":
                                return users.stream()
                                                .sorted(Comparator.comparing(User::getPhone))
                                                .toList();
                        case "tax_id":
                                return users.stream()
                                                .sorted(Comparator.comparing(User::getTax_id))
                                                .toList();
                        case "created_at":
                                return users.stream()
                                                .sorted(Comparator.comparing(User::getCreated_at))
                                                .toList();
                        case "id":
                                return users.stream()
                                                .sorted(Comparator.comparing(User::getId))
                                                .toList();
                        default:
                                throw new IllegalArgumentException(
                                                "Invalid sortedBy. The field must be one of the following: email, name, phone, tax_id, created_at, id");
                }
        }

        private List<User> getUserByFilter(String filter) {
                String[] filterValues = GetFilterValues(filter);
                String field = filterValues[0];
                String operator = filterValues[1];
                String value = filterValues[2];

                switch (field) {
                        case "email":
                                switch (operator) {
                                        case "co":
                                                users = users.stream().filter(u -> u.getEmail().contains(value))
                                                                .toList();
                                                break;
                                        case "sw":
                                                users = users.stream().filter(u -> u.getEmail().startsWith(value))
                                                                .toList();
                                                break;
                                        case "ew":
                                                users = users.stream().filter(u -> u.getEmail().endsWith(value))
                                                                .toList();
                                                break;
                                        case "eq":
                                                users = users.stream().filter(u -> u.getEmail().equals(value)).toList();
                                                break;
                                        default:
                                                break;
                                }
                                break;
                        case "name":
                                switch (operator) {
                                        case "co":
                                                users = users.stream().filter(u -> u.getName().contains(value))
                                                                .toList();
                                                break;
                                        case "sw":
                                                users = users.stream().filter(u -> u.getName().startsWith(value))
                                                                .toList();
                                                break;
                                        case "ew":
                                                users = users.stream().filter(u -> u.getName().endsWith(value))
                                                                .toList();
                                                break;
                                        case "eq":
                                                users = users.stream().filter(u -> u.getName().equals(value)).toList();
                                        default:
                                                break;
                                }
                                break;
                        case "phone":
                                switch (operator) {
                                        case "co":
                                                users = users.stream().filter(u -> u.getPhone().contains(value))
                                                                .toList();
                                                break;
                                        case "sw":
                                                users = users.stream().filter(u -> u.getPhone().startsWith(value))
                                                                .toList();
                                                break;
                                        case "ew":
                                                users = users.stream().filter(u -> u.getPhone().endsWith(value))
                                                                .toList();
                                                break;
                                        case "eq":
                                                users = users.stream().filter(u -> u.getPhone().equals(value)).toList();
                                        default:
                                                break;
                                }
                                break;
                        case "tax_id":
                                switch (operator) {
                                        case "co":
                                                users = users.stream().filter(u -> u.getTax_id().contains(value))
                                                                .toList();
                                                break;
                                        case "sw":
                                                users = users.stream().filter(u -> u.getTax_id().startsWith(value))
                                                                .toList();
                                                break;
                                        case "ew":
                                                users = users.stream().filter(u -> u.getTax_id().endsWith(value))
                                                                .toList();
                                                break;
                                        case "eq":
                                                users = users.stream().filter(u -> u.getTax_id().equals(value))
                                                                .toList();
                                        default:
                                                break;
                                }
                                break;
                        case "created_at":
                                switch (operator) {
                                        case "co":
                                                users = users.stream().filter(u -> u.getCreated_at().contains(value))
                                                                .toList();
                                                break;
                                        case "sw":
                                                users = users.stream().filter(u -> u.getCreated_at().startsWith(value))
                                                                .toList();
                                                break;
                                        case "ew":
                                                users = users.stream().filter(u -> u.getCreated_at().endsWith(value))
                                                                .toList();
                                                break;
                                        case "eq":
                                                users = users.stream().filter(u -> u.getCreated_at().equals(value))
                                                                .toList();
                                                break;
                                        default:
                                                break;
                                }
                                break;
                        case "id":
                                switch (operator) {
                                        case "co":
                                                users = users.stream().filter(u -> u.getId().toString().contains(value))
                                                                .toList();
                                                break;
                                        case "sw":
                                                users = users.stream()
                                                                .filter(u -> u.getId().toString().startsWith(value))
                                                                .toList();
                                                break;
                                        case "ew":
                                                users = users.stream().filter(u -> u.getId().toString().endsWith(value))
                                                                .toList();
                                                break;
                                        case "eq":
                                                UUID uuidValue = UUID.fromString(value);
                                                users = users.stream().filter(u -> u.getId().equals(uuidValue))
                                                                .toList();
                                                break;
                                        default:
                                                break;
                                }
                                break;
                }
                return users;
        }
}
