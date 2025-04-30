package com.microservice.auth.jpa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * esta entidad contiene la segunda version para almacenar usuario y por separado tendra roles
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class UserEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "apellidos")
    private String lastName;

    @Column(name = "nombres")
    private String fistName;

    @Column(name = "numero_documento")
    private String numDocument;

    @OneToOne
    @JoinColumn(name = "apartment_id", referencedColumnName = "id")
    private ApartmentEntity apartmentEntity;

    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dateBirth;

    @Column(name = "telefono")
    private String phone;

    @Email
    @Column(unique = true)
    private String email;

    private String password;

    @OneToMany(mappedBy = "userEntity")
    private List<ServiceEntity> services;

    @OneToMany(mappedBy = "userEntity")
    private List<CommuniqueEntity> communiqueEntityList;

    @OneToMany(mappedBy = "userEntity")
    private List<TransactionEntity> transactionEntityList;

    @OneToMany(mappedBy = "userEntity")
    private List<ReservationEntity> reservationEntityList;

    @Column(name = "is_enable")
    private boolean isEnable;

    @Column(name = "account_no_expired")
    private boolean accountNoExpired;

    @Column(name = "account_no_locked")
    private boolean accountNoLocked;

    @Column(name = "credential_no_expired")
    private boolean credentialNoExpired;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity =RoleEntity.class, cascade = CascadeType.PERSIST)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles = new HashSet<>();
}
