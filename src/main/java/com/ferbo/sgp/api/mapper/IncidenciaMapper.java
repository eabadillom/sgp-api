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
    @Mapping(source = "solicitudPrenda.id", target = "solicitudPrenda")
    @Mapping(source = "solicitudArticulo.id", target = "solicitudArticulo")
    @Mapping(source = "empleadoRevisa.numeroEmpleado", target = "numeroRevisor")
    IncidenciaDTO toDTO(Incidencia incidencia);
    
    @Mapping(source = "idIncidencia", target = "id")
    @Mapping(source = "codigoEstadoIncidencia", target = "estatus.clave")
    @Mapping(source = "codigoTipoIncidencia", target = "tipo.clave")
    @Mapping(source = "solicitudPermiso", target = "solicitudPermiso.idSolicitud")
    @Mapping(source = "nombreSolicitante", target = "empleadoSol.nombre")
    @Mapping(source = "primerApSolicitante", target = "empleadoSol.primeroAp")
    @Mapping(source = "segundoApSolicitante", target = "empleadoSol.segundoAp")
    @Mapping(source = "fechaCaptura", target = "fechaCaptura")
    @Mapping(source = "solicitudPrenda", target = "solicitudPrenda.id")
    @Mapping(source = "solicitudArticulo", target = "solicitudArticulo.id")
    @Mapping(source = "numeroRevisor", target = "empleadoRevisa.numeroEmpleado")
    Incidencia  toEntity(IncidenciaDTO incidenciaDTO);    
}
