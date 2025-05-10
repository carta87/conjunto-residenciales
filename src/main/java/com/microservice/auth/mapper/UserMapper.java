package com.microservice.auth.mapper;

import com.microservice.auth.dto.UserDTO;
import com.microservice.auth.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = ApartmentMapper.class)
public interface UserMapper {

    // De Entity a DTO
    @Mappings({
            @Mapping(source = "fistName", target = "nombres"),
            @Mapping(source = "lastName", target = "apellidos"),
            @Mapping(source = "numDocument", target = "numeroDocumento"),
            @Mapping(source = "apartmentEntity", target = "apartmentDTO"),
            @Mapping(source = "dateBirth", target = "dateBirth"),
            @Mapping(source = "phone", target = "telefono"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "password", target = "password")
    })
    UserDTO toDTO(UserEntity entity);

    // De DTO a Entity
    @Mappings({
            @Mapping(source = "nombres", target = "fistName"),
            @Mapping(source = "apellidos", target = "lastName"),
            @Mapping(source = "numeroDocumento", target = "numDocument"),
            @Mapping(source = "apartmentDTO", target = "apartmentEntity"),
            @Mapping(source = "dateBirth", target = "dateBirth"),
            @Mapping(source = "telefono", target = "phone"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "password", target = "password")
    })
    UserEntity toEntity(UserDTO dto);
}
