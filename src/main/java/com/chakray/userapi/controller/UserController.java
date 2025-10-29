package com.chakray.userapi.controller;

import java.time.LocalDate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chakray.userapi.dto.ErrorResponse;
import com.chakray.userapi.dto.UserResult;
import com.chakray.userapi.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Get all users
     * @return List of users
     */
    @Operation(summary = "Get all users", description = "Returns a list of all users in the system.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved the list of users.",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = UserResult.class)
            )
        ),
        @ApiResponse(
            responseCode = "204",
            description = "No users found",
            content = @Content(mediaType = "application/json")
        ),
        @ApiResponse(
            responseCode = "400", 
            description = "Invalid request",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "500", 
            description = "Internal server error",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class)
            )
        )
    })

    @GetMapping(value = "/users")
    public ResponseEntity<?> getAllUsers()
    {
        try
        {
            // Get the users from the service
            var users = userService.getAllUsers();
            // Verify if the list is empty
            if (users.isEmpty())
                // Return an error response
                return ResponseEntity.noContent().build();
            // Return the users as a list of UserResult
            return ResponseEntity.ok(users);
        }
        catch (Exception e)
        {
            var error = new ErrorResponse(
                500, 
                "Internal server error", 
                e.getMessage(), 
                // TODO Change time zone
                LocalDate.now().toString());
            // Return an error response
            return ResponseEntity.internalServerError().body(error);
        }
    }

}
