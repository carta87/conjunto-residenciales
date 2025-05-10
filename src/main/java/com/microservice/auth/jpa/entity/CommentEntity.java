package com.microservice.auth.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comentario")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "contenido", length = 500)
    private String content;

    @Column(name = "positivo")
    private Boolean positive;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)//TODO: validar relacion
    private ProductEntity product;
}
