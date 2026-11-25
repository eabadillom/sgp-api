package com.ferbo.sgp.api.mapper;

import com.ferbo.sgp.api.dto.PlantaDTO;
import com.ferbo.sgp.api.model.Planta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlantaMapper 
{
    @Mapping(source = "id", target = "id")
    @Mapping(source = "descripcion", target = "descripcion")
    PlantaDTO toDTO(Planta planta);
    
    @Mapping(source = "id", target = "id")
    @Mapping(source = "descripcion", target = "descripcion")
    Planta toEntity(PlantaDTO plantaDTO);
    
}
