package com.chakray.userapi.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.chakray.userapi.helper.TimeHelper;
import com.chakray.userapi.util.Validators;

public class User {
    // Fields
    private UUID id;
    private String email;
    private String name;
    private String phone;
    private String password;
    private String tax_id;
    private String created_at;
    private List<Address> addresses = new ArrayList<>();

    // Constructors
    public User() { }

    public User(
            String email,
            String name,
            String phone,
            String password, 
            String taxId) {
        // Validate phone
        if(!Validators.isValidPhone(phone))
                throw new IllegalArgumentException("Invalid phone number format");
        // Validate tax id
        if(!Validators.isValidTaxId(taxId))
                throw new IllegalArgumentException("Invalid tax id format");

        this.id = UUID.randomUUID();
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.tax_id = taxId;
        this.created_at = TimeHelper.getTimeFromMadagascar();
        this.addresses = new ArrayList<>();
    }

    // Getters
    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public String getTax_id() {
        return tax_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    // public methods

    /**
     * Adds an address to the user
     * @param address The address to add
     */
    public void addAddress(Address address) {
        // Check if the address is not null
        if (address != null)
            // Add the address to the list of addresses
            addresses.add(address);
    }

    /**
     * Updates the user
     * @param name The new name of the user
     */
    public void update(
        String name, 
        String email, 
        String phone) {
        // Validate phone
        if(!Validators.isValidPhone(phone))
                throw new IllegalArgumentException("Invalid phone number format");
        // Update the name of the user
        this.name = name;
        // Update the email of the user
        this.email = email;
        // Update the phone of the user
        this.phone = phone;
    }
}
