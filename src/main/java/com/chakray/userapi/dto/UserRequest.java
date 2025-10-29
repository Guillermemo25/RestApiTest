package com.chakray.userapi.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Represents a user in the system.")
public class UserRequest {
    @Schema(description = "The user name.", example = "Test")
    public String name;
    @Schema(description = "The user email.", example = "test@test.com")
    public String email;
    @Schema(description = "The user phone.", example = "55 555 55 555")
    public String phone;
    @Schema(description = "The user password.", example = "password")
    public String password;
    @Schema(description = "The user tax ID.", example = "123456789")
    public String taxId;
    @Schema(description = "List of user's addresses.", type = "array", implementation = AddressRequest.class, example = "[{\"id\": 1, \"name\": \"Home\", \"street\": \"Main St 123\", \"countryCode\": \"UK\"}]")
    public List<AddressRequest> addresses;

    public UserRequest() {
    }
}
