package com.microservice.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank
    private String password;
    @NotBlank
    private String email;
    @NotBlank
    private String firsName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String country;
    @NotBlank
    private String numDocument;
    @NotBlank
    private Date dateBirth;
    @NotBlank
    private String phone;
    @NotBlank
    private ApartmentDTO apartmentDTO;
}
