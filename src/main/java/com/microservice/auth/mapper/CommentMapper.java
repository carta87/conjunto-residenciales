package com.microservice.auth.mapper;

import com.microservice.auth.dto.CommentDTO;
import com.microservice.auth.jpa.entity.CommentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mappings({
            @Mapping(target = "content", source = "contenido"),
            @Mapping(target = "positive", source = "positivo"),
            @Mapping(target = "product", ignore = true) // evitamos bucle de referencias
    })
    CommentEntity toEntity(CommentDTO dto);

    @Mappings({
            @Mapping(target = "contenido", source = "content"),
            @Mapping(target = "positivo", source = "positive")
    })
    CommentDTO toDTO(CommentEntity entity);



}
