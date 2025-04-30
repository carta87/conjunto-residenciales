package com.microservice.auth.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reserva")
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "id_espacio_comunal", nullable = false)
    private SpaceCommunalEntity spaceCommunalEntity;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDateTime dateInit;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDateTime dateEnd;

    @Column(name = "estado", nullable = false, length = 50)
    private String state;

    @Column(name = "monto", precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "numero_confirmacion", length = 100, unique = true)
    private String numConfirmation;

}
