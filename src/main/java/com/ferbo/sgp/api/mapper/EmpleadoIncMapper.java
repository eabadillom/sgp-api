package com.ferbo.sgp.api.mapper;

import com.ferbo.sgp.api.dto.EmpleadoIncDTO;
import com.ferbo.sgp.api.model.Empleado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 *
 * @author alberto
 */
@Mapper(componentModel = "spring")
public interface EmpleadoIncMapper 
{
    @Mapping(source = "idEmpleado", target = "idEmpleado")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "primeroAp", target = "primerApEmpleado")
    @Mapping(source = "segundoAp", target = "segundoApEmpleado")
    EmpleadoIncDTO toDTO(Empleado empleado);
    
    @Mapping(source = "idEmpleado", target = "idEmpleado")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "primerApEmpleado", target = "primeroAp")
    @Mapping(source = "segundoApEmpleado", target = "segundoAp")
    Empleado toEntity(EmpleadoIncDTO empleadoDTO);
    
}
