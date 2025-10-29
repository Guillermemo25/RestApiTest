package com.chakray.userapi.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class User {
    // Fields
    private UUID id;
    private String email;
    private String name;
    private String phone;
    private String password;
    private String tax_id;
    private String created_at;
    private List<Address> addresses;

    // Constructors
    public User() { }

    public User(
            UUID id,
            String email,
            String name,
            String phone,
            String password, 
            String taxId,
            String createdAt) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.tax_id = taxId;
        this.created_at = createdAt;
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

    // Public methods

    /**
     * Adds an address to the user
     * @param address The address to add
     */
    public void addAddress(Address address) {
        addresses.add(address);
    }
}
