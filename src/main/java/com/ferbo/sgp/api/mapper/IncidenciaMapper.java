package com.ferbo.sgp.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ferbo.sgp.api.dto.IncidenciaDTO;
import com.ferbo.sgp.api.model.Incidencia;

@Mapper(componentModel = "spring")
public interface IncidenciaMapper {

    @Mapping(source = "estatus.clave", target = "codigoEstadoIncidencia")
    @Mapping(source = "tipo.clave", target = "codigoTipoIncidencia")
    @Mapping(source = "solicitudPermiso.idSolicitud", target = "solicitudPermiso")
    IncidenciaDTO toDTO(Incidencia incidencia);

    @Mapping(source = "codigoEstadoIncidencia", target = "estatus.clave")
    @Mapping(source = "codigoTipoIncidencia", target = "tipo.clave")
    @Mapping(source = "solicitudPermiso", target = "solicitudPermiso.idSolicitud")
    Incidencia  toEntity(IncidenciaDTO incidenciaDTO);
}
