package com.microservice.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApartmentDTO {

    @NotBlank
    private String country;
    @NotBlank
    private String nunApartment;
    @NotBlank
    private String numTower;
}
