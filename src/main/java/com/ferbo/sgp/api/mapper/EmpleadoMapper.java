package com.ferbo.sgp.api.mapper;

import com.ferbo.sgp.api.dto.EmpleadoDTO;
import com.ferbo.sgp.api.model.Empleado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmpleadoMapper 
{
    @Mapping(source = "numeroEmpleado", target = "numero")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "primeroAp", target = "primerApellido")
    @Mapping(source = "segundoAp", target = "segundoApellido")
    @Mapping(source = "empleado.informacionEmpresa.puesto.descripcion", target = "puesto")
    EmpleadoDTO toDTO(Empleado empleado);
    
    @Mapping(source = "numero", target = "numeroEmpleado")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "primerApellido", target = "primeroAp")
    @Mapping(source = "segundoApellido", target = "segundoAp")
    @Mapping(source = "puesto", target = "informacionEmpresa.puesto.descripcion")
    Empleado toEntity(EmpleadoDTO empleadoDTO);
    
}
