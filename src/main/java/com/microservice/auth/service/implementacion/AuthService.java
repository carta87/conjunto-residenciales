package com.microservice.auth.service.implementacion;

import com.microservice.auth.dto.AuthResponse;
import com.microservice.auth.dto.LoginRequest;
import com.microservice.auth.dto.RegisterRequest;
import com.microservice.auth.jpa.entity.*;
import com.microservice.auth.jpa.repository.IApartmentRepository;
import com.microservice.auth.jpa.repository.IPermissionRepository;
import com.microservice.auth.jpa.repository.IRoleRepository;
import com.microservice.auth.jpa.repository.IUserRepository;
import com.microservice.auth.util.Constantes;
import com.microservice.auth.util.JwtUtil;
import com.microservice.auth.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final JwtUtil jwtUtil;
    private final IUserRepository iUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final IRoleRepository iRoleRepository;
    private final IApartmentRepository iApartmentRepository;
    private final IPermissionRepository iPermissionRepository;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        String token = jwtUtil.getToken(iUserRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException(Constantes.USUARIO_NO_ENCONTRADO)));
        return AuthResponse.builder()
                .email(loginRequest.getEmail())
                .status(Boolean.TRUE)
                .token(token)
                .message(Constantes.TOKEN_CREADO)
                .build();
    }

    @Override
    @Transactional
    public AuthResponse register(RegisterRequest registerRequest)  {
        // Buscar o crear el permiso "READ" por defecto
        PermissionEntity readPermission = iPermissionRepository.findByName(Constantes.PERMISO_READ)
                .orElseGet(() -> iPermissionRepository.save(new PermissionEntity(null, Constantes.PERMISO_READ)));

        // Buscar o crear el rol
        RoleEntity userRole = iRoleRepository.findByRoleEnum(RoleEnum.USER)
                .orElseGet(() -> {
                    RoleEntity newRole = RoleEntity.builder()
                            .roleEnum(RoleEnum.USER)
                            .permissionList(new HashSet<>(Set.of(readPermission)))
                            .build();
                    return iRoleRepository.save(newRole);
                });

        ApartmentEntity apartment = ApartmentEntity.builder()
                .country(registerRequest.getApartmentDTO().getCountry())
                .numTower(registerRequest.getApartmentDTO().getNumTower())
                .nunApartment(registerRequest.getApartmentDTO().getNunApartment())
                .build();

        apartment = iApartmentRepository.save(apartment);

        SimpleDateFormat formatter = new SimpleDateFormat(Constantes.FORMATO_DD_MM_YYYY);
        formatter.setLenient(false);

        UserEntity userEntity = null;
        try {
            userEntity = UserEntity.builder()
                    .password(passwordEncoder.encode(registerRequest.getPassword()))
                    .fistName(registerRequest.getFirsName())
                    .lastName(registerRequest.getLastName())
                    .numDocument(registerRequest.getNumDocument())
                    .email(registerRequest.getEmail())
                    .phone(registerRequest.getPhone())
                    .dateBirth(formatter.parse(registerRequest.getDateBirth()))
                    .isEnable(Boolean.TRUE)
                    .isEnable(Boolean.TRUE)
                    .accountNoExpired(Boolean.TRUE)
                    .credentialNoExpired(Boolean.TRUE)
                    .accountNoLocked(Boolean.TRUE)
                    .roles(Set.of(userRole))// Asociar el rol creado
                    .apartmentEntity(apartment)
                    .build();
        } catch (ParseException e) {
            throw new IllegalArgumentException(Constantes.ERROR_FORMATO_FECHA_NACIMIENTO);
        }
        iUserRepository.save(userEntity);

        return AuthResponse.builder()
                .email(registerRequest.getEmail())
                .token(jwtUtil.getToken(userEntity))
                .message(Constantes.USUARIO_CREADO)
                .status(Boolean.TRUE)
                .build();
    }
}
