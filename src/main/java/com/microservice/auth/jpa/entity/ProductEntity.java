package com.microservice.auth.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "producto")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String name;

    @Column(name = "descripcion", length = 255)
    private String description;

    @Column(name = "precio", nullable = false)
    private BigDecimal price;

    @Column(name = "estado", nullable = false)
    private Boolean active;

    @Column(name = "url_imagen")
    private String imageUrl;

    @Column(name = "imagen", columnDefinition = "LONGTEXT")
    private String imageBase64;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentEntity> comments;
}
