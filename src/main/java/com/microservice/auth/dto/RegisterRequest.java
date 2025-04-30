package com.microservice.auth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RegisterRequest {

    @NotBlank
    private String password;
    @NotBlank
    private String email;
    @NotBlank
    private String nombres;
    @NotBlank
    private String appellidos;
    @NotBlank
    private String numumeroDocumento;
    @NotBlank
    private String fechaNacimiento;
    @NotBlank
    private String telefono;
    private ApartmentDTO datosApartmento;
}
