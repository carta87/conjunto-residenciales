package com.microservice.auth.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "apartamento")
public class ApartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cuidad")
    private String country;

    @Column(name = "numero_apartamento")
    private String nunApartment;

    @Column(name = "numero_torre")
    private String numTower;
}
