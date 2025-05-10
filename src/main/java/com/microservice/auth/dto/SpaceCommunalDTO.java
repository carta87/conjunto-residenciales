package com.microservice.auth.dto;

import com.microservice.auth.jpa.entity.StateSpaceEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpaceCommunalDTO {

    private Long id;

    private String nombre;

    private String descripcion;

    private StateSpaceEnum estado;

}
