package com.ferbo.sgp.api.mapper;

import com.ferbo.sgp.api.dto.RegistroAsistenciaDTO;
import com.ferbo.sgp.api.model.RegistroAsistencia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RegistroAsistenciaMapper 
{
    @Mapping(source = "id", target = "id")
    //@Mapping(source = "empleado.nombre", target = "empleadoAsisDTO.nombre")
    //@Mapping(source = "empleado.primeroAp", target = "empleadoAsisDTO.primerApEmpleado")
    //@Mapping(source = "empleado.segundoAp", target = "empleadoAsisDTO.segundoApEmpleado")
    @Mapping(source = "fechaEntrada", target = "fechaEntrada")
    @Mapping(source = "status.codigo", target = "codigoRegistro")
    RegistroAsistenciaDTO toDTO(RegistroAsistencia registroAsistencia);
    
    @Mapping(source = "id", target = "id")
    //@Mapping(source = "empleadoAsisDTO.nombre", target = "empleado.nombre")
    //@Mapping(source = "empleadoAsisDTO.primerApEmpleado", target = "empleado.primeroAp")
    //@Mapping(source = "empleadoAsisDTO.segundoApEmpleado", target = "empleado.segundoAp")
    @Mapping(source = "fechaEntrada", target = "fechaEntrada")
    @Mapping(source = "codigoRegistro", target = "status.codigo")
    RegistroAsistencia toEntity(RegistroAsistenciaDTO dto);
    
}
