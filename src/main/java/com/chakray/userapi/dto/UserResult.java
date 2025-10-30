package com.chakray.userapi.dto;

import java.util.List;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Represents a user in the system.")
public class UserResult {
    @Schema(description = "The unique identifier of the user.", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    public UUID id;
    @Schema(description = "The user email.", example = "test@test.com")
    public String email;
    @Schema(description = "The user name.", example = "Test")
    public String name;
    @Schema(description = "The user phone.", example = "55 555 55 555")
    public String phone;
    @Schema(description = "The user tax ID.", example = "XXXX000000XXX")
    public String tax_id;
    @Schema(description = "The timestamp when the user was created.", example = "2023-10-10T10:00:00+03:00")
    public String created_at;
    @Schema(description = "The list of addresses associated with the user.")
    public List<AddressResult> addresses;

    public UserResult() { }
}
