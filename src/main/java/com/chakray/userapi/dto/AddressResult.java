package com.chakray.userapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema (description = "Represents an address in the system.")
public class AddressResult {
    @Schema(description = "The address ID.")
    public int id;
    @Schema(description = "The address name.")
    public String name;
    @Schema(description = "The address street.")
    public String street;
    @Schema(description = "The address country code.")
    public String countryCode;

    public AddressResult() { }
    
}
