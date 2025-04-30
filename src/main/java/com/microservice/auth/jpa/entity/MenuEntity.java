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
@Table(name = "menu_principal")
public class MenuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "menuEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComponentEntity> componentEntityList;

}
