package com.ferbo.sgp.api.mapper;

import com.ferbo.sgp.api.dto.SolicitudPermisoDTO;
import com.ferbo.sgp.api.model.SolicitudPermiso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 *
 * @author alberto
 */
@Mapper(componentModel = "spring")
public interface SolicitudPermisoMapper 
{
    @Mapping(source = "idSolicitud", target = "idSolicitud")
    @Mapping(source = "empleadoSol.nombre", target = "nombreEmpleado")
    @Mapping(source = "empleadoSol.primeroAp", target = "primerApEmpleado")
    @Mapping(source = "empleadoSol.segundoAp", target = "segundoApEmpleado")
    @Mapping(source = "fechaInicio", target = "fechaInicio")
    @Mapping(source = "fechaFin", target = "fechaFin")
    @Mapping(source = "estatusSolicitud.clave", target = "claveSolicitud")
    SolicitudPermisoDTO toDTO(SolicitudPermiso solicitudPermiso);
    
    @Mapping(source = "idSolicitud", target = "idSolicitud")
    @Mapping(source = "nombreEmpleado", target = "empleadoSol.nombre")
    @Mapping(source = "primerApEmpleado", target = "empleadoSol.primeroAp")
    @Mapping(source = "segundoApEmpleado", target = "empleadoSol.segundoAp")
    @Mapping(source = "fechaInicio", target = "fechaInicio")
    @Mapping(source = "fechaFin", target = "fechaFin")
    @Mapping(source = "claveSolicitud", target = "estatusSolicitud.clave")  
    SolicitudPermiso toEntity(SolicitudPermisoDTO dto);
    
}
