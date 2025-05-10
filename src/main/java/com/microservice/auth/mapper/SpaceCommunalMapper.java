package com.microservice.auth.mapper;

import com.microservice.auth.dto.SpaceCommunalDTO;
import com.microservice.auth.jpa.entity.SpaceCommunalEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SpaceCommunalMapper {

    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "descripcion", target = "description")
    @Mapping(source = "estado", target = "stateEnum") // Asegúrate que exista el campo "estado" en el DTO
    SpaceCommunalEntity mapToEntity(SpaceCommunalDTO spaceCommunalDTO);

    List<SpaceCommunalDTO> mapToDto(List<SpaceCommunalEntity> spaceCommunalEntityList);

    @Mapping(source = "name", target = "nombre")
    @Mapping(source = "description", target = "descripcion")
    @Mapping(source = "stateEnum", target = "estado")
    SpaceCommunalDTO mapToDto(SpaceCommunalEntity spaceCommunalEntity);
}
