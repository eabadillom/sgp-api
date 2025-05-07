package com.ferbo.sgp.api.mapper;

import com.ferbo.sgp.api.dto.SolicitudPermisoDetalleDTO;
import com.ferbo.sgp.api.model.SolicitudPermiso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 *
 * @author alberto
 */
@Mapper(componentModel = "spring", uses = SolicitudPermisoMapper.class)
public interface SolicitudPermisoDetalleMapper 
{
    @Mapping(source = "idSolicitud", target = "idSolicitud")
    @Mapping(source = "empleadoSol.nombre", target = "nombreEmpleado")
    @Mapping(source = "empleadoSol.primeroAp", target = "primerApEmpleado")
    @Mapping(source = "empleadoSol.segundoAp", target = "segundoApEmpleado")
    @Mapping(source = "fechaInicio", target = "fechaInicio")
    @Mapping(source = "fechaFin", target = "fechaFin")
    @Mapping(source = "estatusSolicitud.clave", target = "claveSolicitud")
    @Mapping(source = "descripcionRechazo", target = "descripcionRechazo")
    SolicitudPermisoDetalleDTO toDTO(SolicitudPermiso solicitudPermiso);
    
    @Mapping(source = "idSolicitud", target = "idSolicitud")
    @Mapping(source = "nombreEmpleado", target = "empleadoSol.nombre")
    @Mapping(source = "primerApEmpleado", target = "empleadoSol.primeroAp")
    @Mapping(source = "segundoApEmpleado", target = "empleadoSol.segundoAp")
    @Mapping(source = "fechaInicio", target = "fechaInicio")
    @Mapping(source = "fechaFin", target = "fechaFin")
    @Mapping(source = "claveSolicitud", target = "estatusSolicitud.clave")
    @Mapping(source = "descripcionRechazo", target = "descripcionRechazo")
    SolicitudPermiso toEntity(SolicitudPermisoDetalleDTO dto);
    
}
