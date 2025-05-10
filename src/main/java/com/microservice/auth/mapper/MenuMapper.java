package com.microservice.auth.mapper;

import com.microservice.auth.dto.ComponentDTO;
import com.microservice.auth.dto.MenuDTO;
import com.microservice.auth.jpa.entity.ComponentEntity;
import com.microservice.auth.jpa.entity.MenuEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MenuMapper {

    // Mapear de MenuEntity a MenuDTO
    @Mapping(source = "componentEntityList", target = "componentDTOList")
    MenuDTO mapToDto(MenuEntity menuEntity);

    // Mapear de ComponentEntity a ComponentDTO
    @Mapping(source = "itemName", target = "nombre")
    @Mapping(source = "state", target = "estado")
    @Mapping(source = "description", target = "descripcion")
    ComponentDTO mapComponentToDto(ComponentEntity componentEntity);

    // Mapeo de lista de componentes
    List<ComponentDTO> mapComponentList(List<ComponentEntity> components);

    // Mapear de MenuDTO a MenuEntity
    @Mapping(source = "componentDTOList", target = "componentEntityList")
    MenuEntity mapToEntity(MenuDTO menuDTO);

    // Mapear de ComponentDTO a ComponentEntity
    @Mapping(source = "nombre", target = "itemName")
    @Mapping(source = "estado", target = "state")
    @Mapping(source = "descripcion", target = "description")
    ComponentEntity mapComponentToEntity(ComponentDTO componentDTO);

    // Mapeo de lista inversa
    List<ComponentEntity> mapComponentDtoList(List<ComponentDTO> components);
}
