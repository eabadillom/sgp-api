package com.ferbo.sgp.api.mapper;

import com.ferbo.sgp.api.dto.IncapacidadGuardarDetalleDTO;
import com.ferbo.sgp.api.model.Incapacidad;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 *
 * @author alberto
 */
@Mapper(componentModel = "spring")
public interface IncapacidadGuardarDetalleMapper 
{
    @Mapping(source = "idIncapacidad", target = "idIncapacidad")
    @Mapping(source = "idEmpleadoInc.idEmpleado", target = "idEmpleadoInc")
    @Mapping(source = "idEmpleadoRev.idEmpleado", target = "idEmpleadoRev")
    @Mapping(source = "tipoIncapacidad.idTpIncapacidad", target = "tipoIncapacidad")
    @Mapping(source = "controlIncapacidad.idControlIncapacidad", target = "controlIncapacidad")
    @Mapping(source = "secuelaRiesgoTrabajo.idSecRiesgoTrabajo", target = "riesgoTrabajo")
    @Mapping(source = "tipoRiesgo.idTipoRiesgo", target = "tipoRiesgo")
    @Mapping(source = "folio", target = "folio")
    @Mapping(source = "diasAutorizados", target = "diasAutorizados")
    @Mapping(source = "fechaInicio", target = "fechaInicio")
    @Mapping(source = "descripcion", target = "descripcion")
    @Mapping(source = "estatusSolicitud.idEstatusSolicitud", target = "estatusIncapacidad")
    IncapacidadGuardarDetalleDTO toDTO(Incapacidad incapacidad);
    
    @Mapping(source = "idIncapacidad", target = "idIncapacidad")
    @Mapping(source = "idEmpleadoInc", target = "idEmpleadoInc.idEmpleado")
    @Mapping(source = "idEmpleadoRev", target = "idEmpleadoRev.idEmpleado")
    @Mapping(source = "tipoIncapacidad", target = "tipoIncapacidad.idTpIncapacidad")
    @Mapping(source = "controlIncapacidad", target = "controlIncapacidad.idControlIncapacidad")
    @Mapping(source = "riesgoTrabajo", target = "secuelaRiesgoTrabajo.idSecRiesgoTrabajo")
    @Mapping(source = "tipoRiesgo", target = "tipoRiesgo.idTipoRiesgo")
    @Mapping(source = "folio", target = "folio")
    @Mapping(source = "diasAutorizados", target = "diasAutorizados")
    @Mapping(source = "fechaInicio", target = "fechaInicio")
    @Mapping(source = "descripcion", target = "descripcion")
    @Mapping(source = "estatusIncapacidad", target = "estatusSolicitud.idEstatusSolicitud")
    Incapacidad toEntity(IncapacidadGuardarDetalleDTO incapacidadGuardarDetalleDTO);
    
}
