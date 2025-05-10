package com.microservice.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long id;
    @NotBlank
    private String nombre;
    private String descripcion;
    private String imagenUrl;
    private String imagenBase64;
    private String estado;
    private BigDecimal price;
    private List<CommentDTO> comentarios;
}
