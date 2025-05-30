package com.ferbo.sgp.api.service.helper;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ferbo.sgp.api.model.Empleado;
import com.ferbo.sgp.api.model.Incidencia;
import com.ferbo.sgp.api.model.SolicitudBase;
import com.ferbo.sgp.api.repository.EstatusIncidenciaRepo;
import com.ferbo.sgp.api.repository.EstatusSolicitudRepo;

@Service
public class SolicitudHelperService {

    public <T> T obtenerEntidadPorId(Optional<T> optional, String mensajeError) {
        return optional.orElseThrow(() -> new RuntimeException(mensajeError));
    }

    public void actualizarIncidencia(Incidencia incidencia, String estatusClave, Empleado revisor, EstatusIncidenciaRepo estatusIncidenciaRepo) {
        incidencia.setEstatus(estatusIncidenciaRepo.findByClave(estatusClave)
                .orElseThrow(() -> new RuntimeException("No se encontró estatus de incidencia con esa clave")));
        incidencia.setFechaModificacion(OffsetDateTime.now());
        incidencia.setEmpladoRevisa(revisor);
    }

    public void actualizarSolicitud(SolicitudBase solicitud, String estatusClave, Empleado revisor, EstatusSolicitudRepo estatusSolicitudRepo) {
        solicitud.setEstatusSolicitud(estatusSolicitudRepo.buscarPorClave(estatusClave)
                .orElseThrow(() -> new RuntimeException("No se encontró estatus de solicitud con esa clave")));
        solicitud.setFechaModificacion(OffsetDateTime.now());
        solicitud.setEmpleadoRevisor(revisor);
    }
}
