package com.ferbo.sgp.api.mapper;

import com.ferbo.sgp.api.dto.SistemaDTO;
import com.ferbo.sgp.api.model.Sistema;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SistemaMapper {
    
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "rol", target = "rol")
    SistemaDTO toDTO(Sistema sistema);
    
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "rol", target = "rol")
    Sistema toEntity(SistemaDTO dto);
    
}
