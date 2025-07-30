package com.ferbo.sgp.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ferbo.sgp.api.dto.RegistroDTO;
import com.ferbo.sgp.api.model.RegistroAsistencia;

@Mapper(componentModel = "spring")
public interface RegistroMapper {

    @Mapping(source = "status.codigo", target = "codigoRegistro")
    @Mapping(source = "empleado.nombre", target = "nombreEmpleado")
    @Mapping(source = "empleado.primeroAp", target = "primerApEmpleado")
    @Mapping(source = "empleado.segundoAp", target = "segundoApEmpleado")
    RegistroDTO toDTO(RegistroAsistencia registroAsistencia);

    @Mapping(source = "codigoRegistro", target = "status.codigo")
    @Mapping(source = "nombreEmpleado", target = "empleado.nombre")
    @Mapping(source = "primerApEmpleado", target = "empleado.primeroAp")
    @Mapping(source = "segundoApEmpleado", target = "empleado.segundoAp")
    RegistroAsistencia toEntity(RegistroDTO dto);
}
