package com.ferbo.sgp.api.mapper;

import com.ferbo.sgp.api.dto.TipoIncapacidadDTO;
import com.ferbo.sgp.api.model.TipoIncapacidad;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 *
 * @author alberto
 */
@Mapper(componentModel = "spring")
public interface TipoIncapacidadMapper 
{
    @Mapping(source = "idTpIncapacidad.", target = "idTpIncapacidad")
    @Mapping(source = "clave", target = "clave")
    @Mapping(source = "descripcion", target = "descripcion")
    TipoIncapacidadDTO toDTO(TipoIncapacidad tipoIncapacidad);
    
    @Mapping(source = "idTpIncapacidad.", target = "idTpIncapacidad")
    @Mapping(source = "clave", target = "clave")
    @Mapping(source = "descripcion", target = "descripcion")
    TipoIncapacidad toEntity(TipoIncapacidadDTO tipoIncapacidadDTO);
    
}
