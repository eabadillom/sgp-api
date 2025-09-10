package com.ferbo.sgp.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ferbo.sgp.api.dto.SolicitudPrendaDTO;
import com.ferbo.sgp.api.model.SolicitudPrenda;

@Mapper(componentModel = "spring")
public interface SolicitudPrendaMapper {

    @Mapping(source = "estatusSolicitud.clave", target = "estatusSolicitud")
    @Mapping(source = "empleadoSolicitante.nombre", target = "nombreSolicitante")
    @Mapping(source = "empleadoSolicitante.primeroAp", target = "primerApSolicitante")
    @Mapping(source = "empleadoSolicitante.segundoAp", target = "segundoApSolicitante")
    @Mapping(source = "empleadoRevisor.numeroEmpleado", target = "numeroRevisor")
    @Mapping(source = "prenda.descripcion", target = "descripcion")
    @Mapping(source = "prenda.precio", target = "precio")
    @Mapping(source = "prenda.detalle", target = "detalle")
    @Mapping(source = "prenda.activo", target = "activo")
    @Mapping(source = "talla.descripcion", target = "talla")
    SolicitudPrendaDTO toDTO(SolicitudPrenda solicitudPrenda);

    @Mapping(source = "estatusSolicitud", target = "estatusSolicitud.clave")
    @Mapping(source = "nombreSolicitante", target = "empleadoSolicitante.nombre")
    @Mapping(source = "primerApSolicitante", target = "empleadoSolicitante.primeroAp")
    @Mapping(source = "segundoApSolicitante", target = "empleadoSolicitante.segundoAp")
    @Mapping(source = "numeroRevisor", target = "empleadoRevisor.numeroEmpleado")
    @Mapping(source = "descripcion", target = "prenda.descripcion")
    @Mapping(source = "precio", target = "prenda.precio")
    @Mapping(source = "detalle", target = "prenda.detalle")
    @Mapping(source = "activo", target = "prenda.activo")
    @Mapping(source = "talla", target = "talla.descripcion")
    SolicitudPrenda toEntity(SolicitudPrendaDTO solicitudPrendaDTO);
}
