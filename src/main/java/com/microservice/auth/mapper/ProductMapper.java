package com.microservice.auth.mapper;

import com.microservice.auth.dto.ProductDTO;
import com.microservice.auth.jpa.entity.ProductEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {CommentMapper.class})
public interface ProductMapper {

    @Mappings({
            @Mapping(target = "name", source = "nombre"),
            @Mapping(target = "description", source = "descripcion"),
            @Mapping(target = "imageUrl", source = "imagenUrl"),
            @Mapping(target = "imageBase64", source = "imagenBase64"),
            @Mapping(target = "active", source = "estado", defaultValue = "true"),
            @Mapping(target = "price", source = "price"),
            @Mapping(target = "comments", source = "comentarios")
    })
    ProductEntity toEntity(ProductDTO dto);

    @Mappings({
            @Mapping(target = "nombre", source = "name"),
            @Mapping(target = "descripcion", source = "description"),
            @Mapping(target = "imagenUrl", source = "imageUrl"),
            @Mapping(target = "imagenBase64", source = "imageBase64"),
            @Mapping(target = "estado", source = "active"),
            @Mapping(target = "price", source = "price"),
            @Mapping(target = "comentarios", source = "comments")
    })
    ProductDTO toDTO(ProductEntity entity);
}
