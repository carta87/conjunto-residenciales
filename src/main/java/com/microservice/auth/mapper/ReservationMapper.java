package com.microservice.auth.mapper;

import com.microservice.auth.dto.ReservationDTO;
import com.microservice.auth.jpa.entity.ReservationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    @Mapping(source = "dateInit", target = "fechaInicio")
    @Mapping(source = "dateEnd", target = "fechaFin")
    @Mapping(source = "state", target = "estado")
    @Mapping(source = "amount", target = "monto")
    @Mapping(source = "numConfirmation", target = "numeroConfirmacion")
    ReservationDTO toDTO(ReservationEntity entity);

    @Mapping(source = "fechaInicio", target = "dateInit")
    @Mapping(source = "fechaFin", target = "dateEnd")
    @Mapping(source = "estado", target = "state")
    @Mapping(source = "monto", target = "amount")
    @Mapping(source = "numeroConfirmacion", target = "numConfirmation")
    ReservationEntity toEntity(ReservationDTO dto);
}