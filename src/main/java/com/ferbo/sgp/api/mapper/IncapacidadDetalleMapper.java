package com.ferbo.sgp.api.mapper;

import com.ferbo.sgp.api.dto.IncapacidadDetalleDTO;
import com.ferbo.sgp.api.model.Incapacidad;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 *
 * @author alberto
 */
@Mapper(componentModel = "spring")
public interface IncapacidadDetalleMapper 
{
    @Mapping(source = "idIncapacidad", target = "idIncapacidad")
    @Mapping(source = "idEmpleadoInc.nombre", target = "nombreInc")
    @Mapping(source = "idEmpleadoInc.primeroAp", target = "primerApInc")
    @Mapping(source = "idEmpleadoInc.segundoAp", target = "segundoApInc")
    @Mapping(source = "tipoIncapacidad.descripcion", target = "tipoIncapacidad")
    @Mapping(source = "controlIncapacidad.descripcion", target = "controlIncapacidad")
    @Mapping(source = "secuelaRiesgoTrabajo.descripcion", target = "riesgoTrabajo")
    @Mapping(source = "tipoRiesgo.descripcion", target = "tipoRiesgo")
    @Mapping(source = "folio", target = "folio")
    @Mapping(source = "diasAutorizados", target = "diasAutorizados")
    @Mapping(source = "fechaInicio", target = "fechaIni")
    @Mapping(source = "fechaFin", target = "fechaFin")
    @Mapping(source = "estatusSolicitud.descripcion", target = "estatusIncapacidad")
    IncapacidadDetalleDTO toDTO(Incapacidad incapacidad);
    
    @Mapping(source = "idIncapacidad", target = "idIncapacidad")
    @Mapping(source = "nombreInc", target = "idEmpleadoInc.nombre")
    @Mapping(source = "primerApInc", target = "idEmpleadoInc.primeroAp")
    @Mapping(source = "segundoApInc", target = "idEmpleadoInc.segundoAp")
    @Mapping(source = "tipoIncapacidad", target = "tipoIncapacidad.descripcion")
    @Mapping(source = "controlIncapacidad", target = "controlIncapacidad.descripcion")
    @Mapping(source = "riesgoTrabajo", target = "secuelaRiesgoTrabajo.descripcion")
    @Mapping(source = "tipoRiesgo", target = "tipoRiesgo.descripcion")
    @Mapping(source = "folio", target = "folio")
    @Mapping(source = "diasAutorizados", target = "diasAutorizados")
    @Mapping(source = "fechaIni", target = "fechaInicio")
    @Mapping(source = "fechaFin", target = "fechaFin")
    @Mapping(source = "estatusIncapacidad", target = "estatusSolicitud.descripcion")
    Incapacidad toEntity(IncapacidadDetalleDTO incapacidadDetalleDTO);
    
}
