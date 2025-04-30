package com.microservice.auth.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comunicado")
public class CommuniqueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "titulo", nullable = false, length = 100)
    private String title;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime date;

    @Column(name = "tipo", nullable = false, length = 50)
    private String typeCommunique;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private UserEntity  userEntity;
}
