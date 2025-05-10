package com.microservice.auth.dto;

import com.microservice.auth.jpa.entity.SpaceCommunalEntity;
import com.microservice.auth.jpa.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {

    private Long id;

    private UserEntity userEntity;

    private SpaceCommunalEntity spaceCommunalEntity;

    private LocalDateTime fechaInicio;

    private LocalDateTime fechaFin;

    private String estado;

    private BigDecimal monto;

    private String numeroConfirmacion;
}
