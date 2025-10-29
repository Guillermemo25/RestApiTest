package com.chakray.userapi.models;

public class Address {
    // Fields
    private int id;
    private String name;
    private String street;
    private String countryCode;

    // Constructors
    public Address() { }
    
    public Address(
            int id,
            String name,
            String street,
            String countryCode) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.countryCode = countryCode;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStreet() {
        return street;
    }

    public String getCountryCode() {
        return countryCode;
    }
}