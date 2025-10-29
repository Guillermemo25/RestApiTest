package com.chakray.userapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Represents a user in the system.")
public class UserResult {
    @Schema(description = "The user email.", example = "test@test.com")
    public String email;
    @Schema(description = "The user name.", example = "Test")
    public String name;
    @Schema(description = "The user phone.", example = "55 555 55 555")
    public String phone;
}
