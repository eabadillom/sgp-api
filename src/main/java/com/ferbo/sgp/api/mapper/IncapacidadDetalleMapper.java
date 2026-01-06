package com.ferbo.sgp.api.mapper;

import com.ferbo.sgp.api.dto.IncapacidadDetalleDTO;
import com.ferbo.sgp.api.model.Incapacidad;
import com.ferbo.sgp.api.tool.DateUtil;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
    @Mapping(expression = "java(mapFechas(incapacidad))", target = "periodo")
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
    @Mapping(target = "fechaInicio", expression = "java(obtenerFechas(incapacidadDetalleDTO).get(\"fechaIni\"))")
    @Mapping(target = "fechaFin", expression = "java(obtenerFechas(incapacidadDetalleDTO).get(\"fechaFin\"))")
    @Mapping(source = "estatusIncapacidad", target = "estatusSolicitud.descripcion")
    Incapacidad toEntity(IncapacidadDetalleDTO incapacidadDetalleDTO);
    
    default List<OffsetDateTime> mapFechas(Incapacidad incapacidad) 
    {
        List<OffsetDateTime> fechas = null;
        
        if (incapacidad == null) {
            return Collections.emptyList();
        }

        fechas = DateUtil.generarArreglosFechas(incapacidad.getFechaInicio(), incapacidad.getFechaFin());

        return fechas;
    }
    
    default Map<String, OffsetDateTime> obtenerFechas(IncapacidadDetalleDTO dto) 
    {
        if (dto == null || dto.getPeriodo() == null || dto.getPeriodo().isEmpty()) {
            return new HashMap<>();
        }
        
        List<OffsetDateTime> ordenadas = dto.getPeriodo().stream()
                .sorted()
                .collect(Collectors.toList());

        Map<String, OffsetDateTime> fechas = new HashMap<>();
        fechas.put("fechaIni", ordenadas.get(0));
        fechas.put("fechaFin", ordenadas.get(ordenadas.size() - 1));
        
        return fechas;
    }
    
}
