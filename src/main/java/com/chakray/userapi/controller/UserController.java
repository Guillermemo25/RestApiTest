package com.chakray.userapi.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chakray.userapi.dto.ErrorResult;
import com.chakray.userapi.dto.UserRequest;
import com.chakray.userapi.dto.UserResult;
import com.chakray.userapi.dto.UserUpdateRequest;
import com.chakray.userapi.helper.TimeHelper;
import com.chakray.userapi.mapper.UserMapper;
import com.chakray.userapi.models.User;
import com.chakray.userapi.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
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
                schema = @Schema(implementation = ErrorResult.class)
            )
        ),
        @ApiResponse(
            responseCode = "500", 
            description = "Internal server error",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorResult.class)
            )
        )
    })
    @GetMapping()
    public ResponseEntity<?> getAllUsers(
            @RequestParam(required = false) String sortedBy,
            @RequestParam(required = false) String filter) {
        try
        {
            // Get the users from the service
            List<User> users = userService.getAllUsers(sortedBy, filter);
            // Check if the users is empty
            if (users.isEmpty())
                // Return 204 No Content
                return ResponseEntity.noContent().build();
            // Map the users to UserResult
            List<UserResult> usersResult = userMapper.usersToUsersResults(users);
            // Return the users as a list of UserResult
            // TODO Return all properties for testing in postman
            return ResponseEntity.ok(users);
            //return ResponseEntity.ok(usersResult);
        }
        catch (Exception e)
        {
            // Return 500 Internal Server Error
            ErrorResult error = new ErrorResult(
                500, 
                "Internal server error", 
                e.getMessage(), 
                TimeHelper.getTimeFromMadagascar());
            // Return an error response
            return ResponseEntity.internalServerError().body(error);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(
            @PathVariable UUID id) {
        try {
            // Get the user from the service
            User user = userService.getUserById(id);
            // Check if the user is null
            if (user == null)
                // Return 404 Not Found
                return ResponseEntity.notFound().build();
            // Map the users to UserResult
            UserResult userResult = userMapper.userToUserResult(user);
            // Return 200 OK
            return ResponseEntity.ok(userResult);
        } catch (Exception e) {
            // Return 500 Internal Server Error
            ErrorResult error = new ErrorResult(
                500, 
                "Internal server error", 
                e.getMessage(), 
                TimeHelper.getTimeFromMadagascar());
            // Return an error response
            return ResponseEntity.internalServerError().body(error);
        }
    }

    @PostMapping
    public ResponseEntity<?> postMethod(
            @RequestBody UserRequest userRequest) {
        try {
             // Map the users to UserResult
            User userToAdd = userMapper.userRequestToUser(userRequest);
            // Add the user to the service
            User addedUser = userService.addUser(userToAdd);
            // Return 201 Created
            return ResponseEntity.created(URI.create("/users/" + addedUser.getId())).build();
        }
        catch (IllegalArgumentException e) {
            // Return 400 Bad Request
            ErrorResult error = new ErrorResult(
                400, 
                "Bad request", 
                e.getMessage(), 
                TimeHelper.getTimeFromMadagascar());
            // Return an error response
            return ResponseEntity.badRequest().body(error);
        }
        catch (Exception e) {
            ErrorResult error = new ErrorResult(
                500, 
                "Internal server error", 
                e.getMessage(), 
                TimeHelper.getTimeFromMadagascar());
            // Return an error response
            return ResponseEntity.internalServerError().body(error);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchMethod(
            @PathVariable UUID id, 
            @RequestBody UserUpdateRequest body) {
        try {
            // Update the user in the service
            User updatedUserInService = userService.updateUser(
                id, 
                body.name,
                body.email,
                body.phone);
            // Check if the user is null
            if (updatedUserInService == null)
                // Return 404 Not Found
                return ResponseEntity.notFound().build();
            // Map the users to UserResult
            UserResult usersResult = userMapper.userToUserResult(updatedUserInService);
            // Return 200 OK
            return ResponseEntity.ok(usersResult);
        } catch (IllegalArgumentException e) {
            // Return 400 Bad Request
            ErrorResult error = new ErrorResult(
                400, 
                "Bad request", 
                e.getMessage(), 
                TimeHelper.getTimeFromMadagascar());
            // Return an error response
            return ResponseEntity.badRequest().body(error);
        } catch (Exception e) {
            // Return 500 Internal Server Error
            ErrorResult error = new ErrorResult(
                500, 
                "Internal server error", 
                e.getMessage(), 
                TimeHelper.getTimeFromMadagascar());
            // Return an error response
            return ResponseEntity.internalServerError().body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMethod(@PathVariable UUID id) {
        try {
            // Delete the user from the service
            User deletedUser = userService.deleteUser(id);
            // Check if the user is null
            if (deletedUser == null)
                // Return 404 Not Found
                return ResponseEntity.notFound().build();
            // Map the users to UserResult
            UserResult usersResult = userMapper.userToUserResult(deletedUser);
            // Return 200 OK
            return ResponseEntity.ok(usersResult);
        } catch (Exception e) {
            // Return 500 Internal Server Error
            ErrorResult error = new ErrorResult(
                500, 
                "Internal server error", 
                e.getMessage(), 
                TimeHelper.getTimeFromMadagascar());
            // Return an error response
            return ResponseEntity.internalServerError().body(error);
        }
    }

}
