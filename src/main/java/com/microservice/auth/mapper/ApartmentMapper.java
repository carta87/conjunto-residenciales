package com.microservice.auth.mapper;

import com.microservice.auth.dto.ApartmentDTO;
import com.microservice.auth.jpa.entity.ApartmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ApartmentMapper {

    @Mapping(source = "country", target = "ciudad")
    @Mapping(source = "nunApartment", target = "numeroApartamento")
    @Mapping(source = "numTower", target = "numeroTorre")
    ApartmentDTO toDTO(ApartmentEntity entity);

    @Mapping(source = "ciudad", target = "country")
    @Mapping(source = "numeroApartamento", target = "nunApartment")
    @Mapping(source = "numeroTorre", target = "numTower")
    ApartmentEntity toEntity(ApartmentDTO dto);
}
