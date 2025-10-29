package com.chakray.userapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Model for standard error response")
public class ErrorResponse {

    @Schema(description = "Error code", example = "400")
    private int statusCode;

    @Schema(description = "Descriptive message of the error", example = "Invalid request")
    private String message;

    @Schema(description = "Additional details of the error", example = "The request body is missing a required field")
    private String details;

    @Schema(description = "Temporal mark of the error", example = "2025-10-29T12:34:56")
    private String timestamp;
    
    public ErrorResponse() { }

    public ErrorResponse(int statusCode, String message, String details, String timestamp) {
        this.statusCode = statusCode;
        this.message = message;
        this.details = details;
        this.timestamp = timestamp;
    }

    // Getters and Setters

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
