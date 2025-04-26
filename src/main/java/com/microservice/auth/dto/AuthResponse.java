package com.microservice.auth.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"email", "message", "status", "token"})
public class AuthResponse {

    private String email;
    private String token;
    private String message;
    private boolean status;
}
