package com.microservice.auth.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "servicio")
public class ServiceEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String name;

    @Column(name = "precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "tipo", nullable = false, length = 50)
    private String type;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private UserEntity userEntity;

}
