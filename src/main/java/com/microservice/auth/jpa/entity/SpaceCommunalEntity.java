package com.microservice.auth.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "espacio_comunal")
public class SpaceCommunalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String name;

    @Column(name = "descripcion", length = 255)
    private String description;

    @Column(name = "estado", nullable = false)
    @Enumerated(EnumType.STRING)
    private StateSpaceEnum stateEnum;

    @OneToMany(mappedBy = "spaceCommunalEntity")
    private List<ReservationEntity> reservationEntityList;


}
