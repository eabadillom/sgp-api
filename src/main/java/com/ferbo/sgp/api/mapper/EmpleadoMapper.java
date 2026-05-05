package com.ferbo.sgp.api.mapper;

import com.ferbo.sgp.api.dto.EmpleadoDTO;
import com.ferbo.sgp.api.model.Empleado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmpleadoMapper 
{
    @Mapping(source = "numeroEmpleado", target = "numeroUsuario")
    @Mapping(source = "nombre", target = "nombreUsuario")
    @Mapping(source = "primeroAp", target = "primerApUsuario")
    @Mapping(source = "segundoAp", target = "segundoApUsuario")
    @Mapping(source = "empleado.informacionEmpresa.puesto.descripcion", target = "puesto")
    EmpleadoDTO toDTO(Empleado empleado);
    
    @Mapping(source = "numeroUsuario", target = "numeroEmpleado")
    @Mapping(source = "nombreUsuario", target = "nombre")
    @Mapping(source = "primerApUsuario", target = "primeroAp")
    @Mapping(source = "segundoApUsuario", target = "segundoAp")
    @Mapping(source = "puesto", target = "informacionEmpresa.puesto.descripcion")
    Empleado toEntity(EmpleadoDTO empleadoDTO);
    
}
