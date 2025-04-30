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
@Table(name = "transaccion")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private UserEntity userEntity;

    @Column(name = "monto", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime date;

    @Column(name = "tipo", nullable = false, length = 50)
    private String type;

    @Column(name = "estado", nullable = false, length = 20)
    private String state;

    @Column(name = "detalle", length = 255)
    private String detail;

}
