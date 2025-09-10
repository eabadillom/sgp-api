package com.ferbo.sgp.api.mapper;

import com.ferbo.sgp.api.dto.IncapacidadDTO;
import com.ferbo.sgp.api.model.Incapacidad;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 *
 * @author alberto
 */
@Mapper(componentModel = "spring")
public interface IncapacidadMapper 
{
    @Mapping(source = "idIncapacidad", target = "idIncapacidad")
    @Mapping(source = "idEmpleadoInc.nombre", target = "nombreInc")
    @Mapping(source = "idEmpleadoInc.primeroAp", target = "primerApInc")
    @Mapping(source = "idEmpleadoInc.segundoAp", target = "segundoApInc")
    @Mapping(source = "tipoIncapacidad.descripcion", target = "descipcion")
    @Mapping(source = "folio", target = "folio")
    @Mapping(source = "estatusSolicitud.clave", target = "clave")
    @Mapping(source = "fechaCaptura", target = "fechaCaptura")
    IncapacidadDTO toDTO(Incapacidad incapacidad);
    
    @Mapping(source = "idIncapacidad", target = "idIncapacidad")
    @Mapping(source = "nombreInc", target = "idEmpleadoInc.nombre")
    @Mapping(source = "primerApInc", target = "idEmpleadoInc.primeroAp")
    @Mapping(source = "segundoApInc", target = "idEmpleadoInc.segundoAp")
    @Mapping(source = "descipcion", target = "tipoIncapacidad.descripcion")
    @Mapping(source = "folio", target = "folio")
    @Mapping(source = "clave", target = "estatusSolicitud.clave")
    @Mapping(source = "fechaCaptura", target = "fechaCaptura")
    Incapacidad toEntity(IncapacidadDTO incapacidadDTO);
    
}
