package com.chakray.userapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Represents a user in the system.")
public class UserResult {
    @Schema(description = "The user ID.")
    public String name;
    @Schema(description = "The user email.")
    public String email;
    @Schema(description = "The user phone.")
    public String phone;
}
