package com.microservice.auth.config;

import com.microservice.auth.jpa.entity.PermissionEntity;
import com.microservice.auth.jpa.entity.RoleEntity;
import com.microservice.auth.jpa.entity.RoleEnum;
import com.microservice.auth.jpa.entity.UserEntity;
import com.microservice.auth.jpa.repository.IUserRepository;
import com.microservice.auth.util.Constantes;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class DataBD {

   private final IUserRepository userRepository;
   private final PasswordEncoder passwordEncoder;

    @Bean
    public void configurationDataUserRolePermition() {
        List<UserEntity> entityList= (List<UserEntity>) userRepository.findAll();
        if(entityList.isEmpty()){
            /* Create PERMISSIONS */
            PermissionEntity createPermission = PermissionEntity.builder()
                    .name(Constantes.PERMISO_CREATE)
                    .build();

            PermissionEntity readPermission = PermissionEntity.builder()
                    .name(Constantes.PERMISO_READ)
                    .build();

            PermissionEntity updatePermission = PermissionEntity.builder()
                    .name(Constantes.PERMISO_UPDATE)
                    .build();

            PermissionEntity deletePermission = PermissionEntity.builder()
                    .name(Constantes.PERMISO_DELETE)
                    .build();

            /* Create ROLES */
            RoleEntity roleAdmin = RoleEntity.builder()
                    .roleEnum(RoleEnum.ADMIN)
                    .permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
                    .build();

            RoleEntity roleUser = RoleEntity.builder()
                    .roleEnum(RoleEnum.USER)
                    .permissionList(Set.of(readPermission))
                    .build();

            RoleEntity rolePropietario = RoleEntity.builder()
                    .roleEnum(RoleEnum.PROPIETARIO)
                    .permissionList(Set.of(createPermission, readPermission, updatePermission ))
                    .build();

            /* CREATE USERS */
            UserEntity admin = UserEntity.builder()
                    .email("admin@hotmail.com")
                    .password(passwordEncoder.encode("admin"))
                    .isEnable(Boolean.TRUE)
                    .accountNoExpired(Boolean.TRUE)
                    .accountNoLocked(Boolean.TRUE)
                    .credentialNoExpired(Boolean.TRUE)
                    .roles(Set.of(roleAdmin))
                    .build();

            UserEntity user = UserEntity.builder()
                    .email("user@hotmail.com")
                    .password(passwordEncoder.encode("user"))
                    .isEnable(Boolean.TRUE)
                    .accountNoExpired(Boolean.TRUE)
                    .accountNoLocked(Boolean.TRUE)
                    .credentialNoExpired(Boolean.TRUE)
                    .roles(Set.of(roleUser))
                    .build();

            UserEntity userMiguel = UserEntity.builder()
                    .email("migueltafurr@gmail.com")
                    .password(passwordEncoder.encode("1234"))
                    .isEnable(Boolean.TRUE)
                    .accountNoExpired(Boolean.TRUE)
                    .accountNoLocked(Boolean.TRUE)
                    .credentialNoExpired(Boolean.TRUE)
                    .roles(Set.of(roleAdmin))
                    .build();
            userRepository.saveAll(List.of(admin, user, userMiguel));

            UserEntity userCindy = UserEntity.builder()
                    .email("cindy_arenas@gmail.com")
                    .password(passwordEncoder.encode("1234"))
                    .isEnable(Boolean.TRUE)
                    .accountNoExpired(Boolean.TRUE)
                    .accountNoLocked(Boolean.TRUE)
                    .credentialNoExpired(Boolean.TRUE)
                    .roles(Set.of(rolePropietario))
                    .build();
            userRepository.saveAll(List.of(admin, user, userMiguel, userCindy));

        }
    }
}
