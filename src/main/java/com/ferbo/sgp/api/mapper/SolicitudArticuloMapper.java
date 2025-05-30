package com.ferbo.sgp.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ferbo.sgp.api.dto.SolicitudArticuloDTO;
import com.ferbo.sgp.api.model.SolicitudArticulo;

@Mapper(componentModel = "spring")
public interface SolicitudArticuloMapper {
    
    @Mapping(source = "estatusSolicitud.clave", target = "estatusSolicitud")
    @Mapping(source = "empleadoSolicitante.nombre", target = "nombreSolicitante")
    @Mapping(source = "empleadoSolicitante.primeroAp", target = "primerApSolicitante")
    @Mapping(source = "empleadoSolicitante.segundoAp", target = "segundoApSolicitante")
    @Mapping(source = "empleadoRevisor.numeroEmpleado", target = "numeroRevisor")
    @Mapping(source = "articulo.descripcion", target = "descripcion")
    @Mapping(source = "articulo.unidad", target = "unidad")
    @Mapping(source = "articulo.detalle", target = "detalle")
    @Mapping(source = "articulo.activo", target = "activo")
    SolicitudArticuloDTO toDTO(SolicitudArticulo solicitudArticulo);

    @Mapping(source = "estatusSolicitud", target = "estatusSolicitud.clave")
    @Mapping(source = "nombreSolicitante", target = "empleadoSolicitante.nombre")
    @Mapping(source = "primerApSolicitante", target = "empleadoSolicitante.primeroAp")
    @Mapping(source = "segundoApSolicitante", target = "empleadoSolicitante.segundoAp")
    @Mapping(source = "numeroRevisor", target = "empleadoRevisor.numeroEmpleado")
    @Mapping(source = "descripcion", target = "articulo.descripcion")
    @Mapping(source = "unidad", target = "articulo.unidad")
    @Mapping(source = "detalle", target = "articulo.detalle")
    @Mapping(source = "activo", target = "articulo.activo")
    SolicitudArticulo toEntity(SolicitudArticuloDTO solicitudArticuloDTO);

}
