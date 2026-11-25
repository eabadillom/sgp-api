package com.ferbo.sgp.api.mapper;

import com.ferbo.sgp.api.dto.EmpresaDTO;
import com.ferbo.sgp.api.model.Empresa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmpresaMapper 
{
    @Mapping(source = "id", target = "id")
    @Mapping(source = "descripcion", target = "descripcion")
    EmpresaDTO toDTO(Empresa empresa);
    
    @Mapping(source = "id", target = "id")
    @Mapping(source = "descripcion", target = "descripcion")
    Empresa toEntity(EmpresaDTO empresaDTO);
    
}
