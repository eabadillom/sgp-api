package com.ferbo.sgp.api.mapper;

import com.ferbo.sgp.api.dto.EmpleadoAsisDTO;
import com.ferbo.sgp.api.model.Empleado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmpleadoAsisMapper 
{
    
    @Mapping(source = "idEmpleado", target = "id")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "primeroAp", target = "primerAp")
    @Mapping(source = "segundoAp", target = "segundoAp")
    EmpleadoAsisDTO toDTO(Empleado empleado);
    
    @Mapping(source = "id", target = "idEmpleado")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "primerAp", target = "primeroAp")
    @Mapping(source = "segundoAp", target = "segundoAp")
    Empleado toEntity(EmpleadoAsisDTO empleadoDTO);
    
}
