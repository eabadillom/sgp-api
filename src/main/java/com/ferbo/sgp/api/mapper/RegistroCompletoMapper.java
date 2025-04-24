package com.ferbo.sgp.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ferbo.sgp.api.dto.RegistroCompletoDTO;
import com.ferbo.sgp.api.model.RegistroAsistencia;

@Mapper(componentModel = "spring", uses = RegistroParcialMapper.class)
public interface RegistroCompletoMapper {

    @Mapping(source = "status.codigo", target = "codigoRegistro")
    @Mapping(source = "empleado.nombre", target = "nombreEmpleado")
    @Mapping(source = "empleado.primeroAp", target = "primerApEmpleado")
    @Mapping(source = "empleado.segundoAp", target = "segundoApEmpleado")
    @Mapping(source = "empleado.informacionEmpresa.planta.descripcion", target = "plantaEmpleado")
    RegistroCompletoDTO toDTO(RegistroAsistencia registroAsistencia);

    @Mapping(source = "codigoRegistro", target = "status.codigo")
    @Mapping(source = "nombreEmpleado", target = "empleado.nombre")
    @Mapping(source = "primerApEmpleado", target = "empleado.primeroAp")
    @Mapping(source = "segundoApEmpleado", target = "empleado.segundoAp")
    @Mapping(source = "plantaEmpleado" , target = "empleado.informacionEmpresa.planta.descripcion")
    RegistroAsistencia toEntity(RegistroCompletoDTO dto);

}
