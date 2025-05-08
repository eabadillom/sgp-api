package com.ferbo.sgp.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ferbo.sgp.api.dto.IncidenciaDTO;
import com.ferbo.sgp.api.model.Incidencia;

@Mapper(componentModel = "spring")
public interface IncidenciaMapper 
{
    @Mapping(source = "id", target = "idIncidencia")
    @Mapping(source = "estatus.clave", target = "codigoEstadoIncidencia")
    @Mapping(source = "tipo.clave", target = "codigoTipoIncidencia")
    @Mapping(source = "solicitudPermiso.idSolicitud", target = "solicitudPermiso")
    @Mapping(source = "empleadoSol.nombre", target = "nombreSolicitante")
    @Mapping(source = "empleadoSol.primeroAp", target = "primerApSolicitante")
    @Mapping(source = "empleadoSol.segundoAp", target = "segundoApSolicitante")
    @Mapping(source = "fechaCaptura", target = "fechaCaptura")
    @Mapping(ignore = true, target = "solicitudPrenda")
    @Mapping(ignore = true, target = "solicitudArticulo")
    IncidenciaDTO toDTO(Incidencia incidencia);
    
    @Mapping(source = "idIncidencia", target = "id")
    @Mapping(source = "codigoEstadoIncidencia", target = "estatus.clave")
    @Mapping(source = "codigoTipoIncidencia", target = "tipo.clave")
    @Mapping(source = "solicitudPermiso", target = "solicitudPermiso.idSolicitud")
    @Mapping(source = "nombreSolicitante", target = "empleadoSol.nombre")
    @Mapping(source = "primerApSolicitante", target = "empleadoSol.primeroAp")
    @Mapping(source = "segundoApSolicitante", target = "empleadoSol.segundoAp")
    @Mapping(source = "fechaCaptura", target = "fechaCaptura")
    Incidencia  toEntity(IncidenciaDTO incidenciaDTO);    
}
