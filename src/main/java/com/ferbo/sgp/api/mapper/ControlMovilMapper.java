package com.ferbo.sgp.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ferbo.sgp.api.dto.ControlMovilDTO;
import com.ferbo.sgp.api.model.ControlMovil;

@Mapper(componentModel = "spring")
public interface ControlMovilMapper {

    @Mapping(source = "expiracion", target = "expiracion")
    @Mapping(source = "sistema.id", target = "sistema")
    ControlMovilDTO toDto(ControlMovil entity);

    @Mapping(source = "expiracion", target = "expiracion")
    @Mapping(target = "sistema", ignore = true)
    ControlMovil toEntity(ControlMovilDTO dto);

}