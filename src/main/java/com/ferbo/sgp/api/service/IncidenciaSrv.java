package com.ferbo.sgp.api.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import com.ferbo.sgp.api.dto.IncidenciaDTO;
import com.ferbo.sgp.api.mapper.IncidenciaMapper;
import com.ferbo.sgp.api.model.Incidencia;
import com.ferbo.sgp.api.repository.EstatusIncidenciaRepo;
import com.ferbo.sgp.api.repository.IncidenciaRepo;
import com.ferbo.sgp.api.tool.DateUtil;

@Service
public class IncidenciaSrv {

    @Autowired
    IncidenciaRepo incidenciaRepo;

    @Autowired
    IncidenciaMapper incidenciaMapper;

    @Autowired
    EstatusIncidenciaRepo estatusIncidenciaRepo;

    public List<IncidenciaDTO> obtenerIncidenciaTipoEstatusEnPeriodo(String claveTipo, String claveEstatus,
            String fechaInicial, String fechaFinal) throws RuntimeException {

        OffsetDateTime fechaInicio = DateUtil.stringToOffSetTime(fechaInicial);
        OffsetDateTime fechaFin = DateUtil.stringToOffSetTime(fechaFinal);

        fechaInicio = DateUtil.resetOffSetTime(fechaInicio);
        fechaFin = DateUtil.resetOffSetTime(fechaFin);

        if (fechaInicio.isAfter(fechaFin)) {
            OffsetDateTime fechaAux = fechaFin;
            fechaFin = fechaInicio;
            fechaInicio = fechaAux;
        }

        List<IncidenciaDTO> incidenciasDTO = incidenciaRepo
                .findByTipoEstatusEnPeriodo(claveTipo, claveEstatus, fechaInicial, fechaFinal).stream()
                .map(this::convertir).collect(Collectors.toList());

        if (incidenciasDTO.isEmpty()) {
            throw new RuntimeException("No existen registros");
        }

        return incidenciasDTO;
    }

    public IncidenciaDTO actualizarEstatusIncidencia(Integer id, IncidenciaDTO body) {

        Incidencia incidencia = incidenciaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe incidencia con ese identificador"));

        incidencia.setFechaModificacion(OffsetDateTime.now());
        incidencia.setEstatus(estatusIncidenciaRepo.findByClave(body.getCodigoEstadoincidencia()).orElseThrow(
                () -> new RuntimeException("No existe estus con esa clave: " + body.getCodigoEstadoincidencia())));

        incidenciaRepo.save(incidencia);

        return incidenciaMapper.toDTO(incidencia);

    }

    public IncidenciaDTO convertir(Incidencia incidencia) {
        return incidenciaMapper.toDTO(incidencia);
    }
}
