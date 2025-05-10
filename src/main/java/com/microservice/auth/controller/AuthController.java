package com.microservice.auth.controller;

import com.microservice.auth.dto.AuthResponse;
import com.microservice.auth.dto.LoginRequest;
import com.microservice.auth.dto.RegisterRequest;
import com.microservice.auth.service.implementacion.AuthServiceImpl;
import com.microservice.auth.util.Constantes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.authentication.password.CompromisedPasswordDecision;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Controller for Authentication")
public class AuthController {

    private final AuthServiceImpl authServiceImpl;
    private final CompromisedPasswordChecker passwordChecker;

    @Operation(
            summary = "Login User",
            description = "Authenticate a user and return the authentication token along with user details.",
            tags = {"Authentication"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "request login",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LoginRequest.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful authentication",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AuthResponse.class)
                            )
                    )
            }
    )
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authServiceImpl.login(loginRequest));
    }


    @Operation(
            summary = "Register User",
            description = "Registro de usuario",
            tags = {"Authentication"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "request register",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RegisterRequest.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful authentication",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AuthResponse.class)
                            )
                    )
            }
    )
    @PostMapping("/register")
    public  ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest registerRequest)  {
        CompromisedPasswordDecision decision =passwordChecker.check(registerRequest.getPassword());
        if (decision.isCompromised()) {
            throw new IllegalArgumentException(Constantes.CONTRASENA_REPETIDA);
        }
        return ResponseEntity.ok(authServiceImpl.register(registerRequest));
    }
}
