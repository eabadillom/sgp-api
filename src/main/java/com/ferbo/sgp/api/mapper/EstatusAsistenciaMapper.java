package com.ferbo.sgp.api.mapper;

import com.ferbo.sgp.api.dto.EstatusAsistenciaDTO;
import com.ferbo.sgp.api.model.EstadoRegistro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EstatusAsistenciaMapper 
{
    @Mapping(source = "codigo", target = "codigo")
    @Mapping(source = "descripcion", target = "descripcion")
    EstatusAsistenciaDTO toDTO(EstadoRegistro estadoRegistro);
    
    @Mapping(source = "codigo", target = "codigo")
    @Mapping(source = "descripcion", target = "descripcion")
    EstadoRegistro toEntity(EstatusAsistenciaDTO dto);
}
