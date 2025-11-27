package com.ferbo.sgp.api.mapper;

import com.ferbo.sgp.api.dto.IncidenciaPermisoDTO;
import com.ferbo.sgp.api.model.DiaPermiso;
import com.ferbo.sgp.api.model.Incidencia;
import com.ferbo.sgp.api.model.SolicitudPermiso;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 *
 * @author alberto
 */
@Mapper(componentModel = "spring")
public interface IncidenciaPermisoMapper 
{
    @Mapping(source = "id", target = "idIncidencia")
    @Mapping(source = "estatus.clave", target = "codigoEstado")
    @Mapping(source = "empleadoSol.nombre", target = "nombreSolicitante")
    @Mapping(source = "empleadoSol.primeroAp", target = "primerApSolicitante")
    @Mapping(source = "empleadoSol.segundoAp", target = "segundoApSolicitante")
    @Mapping(expression = "java(mapFechas(incidencia))", target = "periodo")
    @Mapping(source = "solicitudPermiso.descripcionRechazo", target = "descripcionRechazo")
    IncidenciaPermisoDTO toDTO(Incidencia incidencia);
    
    @Mapping(source = "idIncidencia", target = "id")
    @Mapping(source = "codigoEstado", target = "estatus.clave")
    @Mapping(source = "nombreSolicitante", target = "empleadoSol.nombre")
    @Mapping(source = "primerApSolicitante", target = "empleadoSol.primeroAp")
    @Mapping(source = "segundoApSolicitante", target = "empleadoSol.segundoAp")
    @Mapping(expression = "java(toSolicitudPermiso(incidenciaPermisoDTO))", target = "solicitudPermiso")
    Incidencia  toEntity(IncidenciaPermisoDTO incidenciaPermisoDTO);  
    
    default List<OffsetDateTime> mapFechas(Incidencia incidencia) 
    {
        List<OffsetDateTime> fechas = null;
        
        SolicitudPermiso permiso = incidencia.getSolicitudPermiso();

        if (permiso == null) {
            return Collections.emptyList();
        }

        String tipo = permiso.getTipoSolicitud().getClave();
        
        switch(tipo){
            case "V":
                if (permiso.getDiasPermiso() != null) {
                    fechas = permiso.getDiasPermiso()
                            .stream()
                            .map(DiaPermiso::getFecha)
                            .sorted()
                            .collect(Collectors.toList());
                }
                break;
            case "P":
                fechas = Collections.singletonList(permiso.getFechaInicio());
                break;
            default:
                fechas = new ArrayList();
                break;
        }

        return fechas;
    }
    
    default SolicitudPermiso toSolicitudPermiso(IncidenciaPermisoDTO dto) 
    {
        if (dto == null) {
            return null;
        }

        SolicitudPermiso permiso = new SolicitudPermiso();
        List<OffsetDateTime> fechas = dto.getPeriodo();

        if (fechas != null && !fechas.isEmpty()) {

            List<OffsetDateTime> ordenadas = fechas.stream()
                    .sorted()
                    .collect(Collectors.toList());

            permiso.setFechaInicio(ordenadas.get(0));
            permiso.setFechaFin(ordenadas.get(ordenadas.size() - 1));

            if (ordenadas.size() > 1) {
                List<DiaPermiso> dias = new ArrayList<>();

                for (OffsetDateTime f : ordenadas) {
                    DiaPermiso dp = new DiaPermiso();
                    dp.setFecha(f);
                    dp.setSolicitud(permiso);
                    dias.add(dp);
                }

                permiso.setDiasPermiso(dias);
            } else {
                permiso.setFechaInicio(fechas.get(0));
                permiso.setFechaFin(fechas.get(0));
                permiso.setDiasPermiso(null);
            }
        }
        permiso.setDescripcionRechazo(dto.getDescripcionRechazo());

        return permiso;
    }

}
