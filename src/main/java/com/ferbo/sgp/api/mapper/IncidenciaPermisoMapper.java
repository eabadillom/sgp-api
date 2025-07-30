package com.ferbo.sgp.api.mapper;

import com.ferbo.sgp.api.dto.IncidenciaPermisoDTO;
import com.ferbo.sgp.api.model.Incidencia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 *
 * @author alberto
 */
@Mapper(componentModel = "spring")
public interface IncidenciaPermisoMapper 
{
    @Mapping(source = "id", target = "idIncidencia")
    @Mapping(source = "estatus.clave", target = "codigoEstado")
    @Mapping(source = "empleadoSol.nombre", target = "nombreSolicitante")
    @Mapping(source = "empleadoSol.primeroAp", target = "primerApSolicitante")
    @Mapping(source = "empleadoSol.segundoAp", target = "segundoApSolicitante")
    @Mapping(source = "solicitudPermiso.fechaInicio", target = "fechaInicio")
    @Mapping(source = "solicitudPermiso.fechaFin", target = "fechaFin")
    @Mapping(source = "solicitudPermiso.descripcionRechazo", target = "descripcionRechazo")
    IncidenciaPermisoDTO toDTO(Incidencia incidencia);
    
    @Mapping(source = "idIncidencia", target = "id")
    @Mapping(source = "codigoEstado", target = "estatus.clave")
    @Mapping(source = "nombreSolicitante", target = "empleadoSol.nombre")
    @Mapping(source = "primerApSolicitante", target = "empleadoSol.primeroAp")
    @Mapping(source = "segundoApSolicitante", target = "empleadoSol.segundoAp")
    @Mapping(source = "fechaInicio", target = "solicitudPermiso.fechaInicio")
    @Mapping(source = "fechaFin", target = "solicitudPermiso.fechaFin")
    @Mapping(source = "descripcionRechazo", target = "solicitudPermiso.descripcionRechazo")
    Incidencia  toEntity(IncidenciaPermisoDTO incidenciaPermisoDTO);  
    
}
