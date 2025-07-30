package com.ferbo.sgp.api.mapper;

import org.mapstruct.Mapper;

import com.ferbo.sgp.api.dto.EstadoRegistroDTO;
import com.ferbo.sgp.api.model.EstadoRegistro;

@Mapper(componentModel = "spring")
public interface EstadoRegistroMapper {

    EstadoRegistroDTO toDTO(EstadoRegistro estadoRegistro);

    EstadoRegistro toEntity(EstadoRegistroDTO estadoRegistroDTO);
}
