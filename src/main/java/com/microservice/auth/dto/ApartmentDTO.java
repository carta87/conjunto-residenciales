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
    private String ciudad;
    @NotBlank
    private String numeroApartamento;
    @NotBlank
    private String numeroTorre;
}
