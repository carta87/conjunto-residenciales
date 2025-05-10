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
public class UserDTO {

    private Long id;
    @NotBlank
    private String nombres;
    @NotBlank
    private String apellidos;
    @NotBlank
    private String numeroDocumento;

    private ApartmentDTO apartmentDTO;

    private Date dateBirth;

    private String telefono;
    @NotBlank
    private String email;
    @NotBlank
    private String password;

}
