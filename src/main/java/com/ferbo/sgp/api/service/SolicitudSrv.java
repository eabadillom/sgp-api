package com.ferbo.sgp.api.service;

import java.time.OffsetDateTime;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferbo.sgp.api.config.RutaImagenProperties;
import com.ferbo.sgp.api.dto.IncidenciaDTO;
import com.ferbo.sgp.api.dto.SolicitudArticuloDTO;
import com.ferbo.sgp.api.dto.SolicitudPrendaDTO;
import com.ferbo.sgp.api.mapper.SolicitudArticuloMapper;
import com.ferbo.sgp.api.mapper.SolicitudPrendaMapper;
import com.ferbo.sgp.api.model.Empleado;
import com.ferbo.sgp.api.model.EstatusIncidencia;
import com.ferbo.sgp.api.model.EstatusSolicitud;
import com.ferbo.sgp.api.model.Incidencia;
import com.ferbo.sgp.api.model.SolicitudArticulo;
import com.ferbo.sgp.api.model.SolicitudPrenda;
import com.ferbo.sgp.api.repository.EmpleadoRepo;
import com.ferbo.sgp.api.repository.EstatusIncidenciaRepo;
import com.ferbo.sgp.api.repository.EstatusSolicitudRepo;
import com.ferbo.sgp.api.repository.IncidenciaRepo;
import com.ferbo.sgp.api.repository.SolicitudArticulodRepo;
import com.ferbo.sgp.api.repository.SolicitudPrendaRepo;
import org.apache.logging.log4j.LogManager;

@Service
public class SolicitudSrv {

        private static Logger log = LogManager.getLogger(SolicitudSrv.class);

        @Autowired
        private SolicitudArticulodRepo solicitudArticuloRepo;

        @Autowired
        private SolicitudPrendaRepo solicitudPrendaRepo;

        @Autowired
        private SolicitudArticuloMapper solicitudArticuloMapper;

        @Autowired
        private SolicitudPrendaMapper solicitudPrendaMapper;

        @Autowired
        private EstatusSolicitudRepo estatusSolicitudRepo;

        @Autowired
        private EstatusIncidenciaRepo estatusIncidenciaRepo;

        @Autowired
        private IncidenciaRepo incidenciaRepo;

        @Autowired
        private EmpleadoRepo empleadoRepo;

        @Autowired
        private RutaImagenProperties rutaImagenProperties;

        /*@Autowired
        private SolicitudHelperService helper;*/

        public SolicitudArticuloDTO obtenerSolicitudArticulo(Long id) {
                SolicitudArticulo solicitud = solicitudArticuloRepo.findById(id).orElseThrow(() -> new RuntimeException("No existe solicitud de articulo con el id: " + id));

                SolicitudArticuloDTO dto = solicitudArticuloMapper.toDTO(solicitud);
                dto.setRutaImagen(rutaImagenProperties.getArticulo());
                return dto;
        }

        public SolicitudPrendaDTO obtenerSolicitudPrenda(Long id) {
                SolicitudPrenda solicitud = solicitudPrendaRepo.findById(id).orElseThrow(() -> new RuntimeException("No existe solicitud de uniforme con el id: " + id));

                SolicitudPrendaDTO dto = solicitudPrendaMapper.toDTO(solicitud);
                dto.setRutaImagen(rutaImagenProperties.getUniforme());

                return dto;
        }


        public void actualizarSolicitud(Integer id, IncidenciaDTO body) {

                Incidencia incidencia = incidenciaRepo.findById(id)
                                .orElseThrow(() -> new RuntimeException(
                                                "No existe la incidencia con id: " + body.getIdIncidencia()));

                EstatusSolicitud estatusSolicitud = estatusSolicitudRepo
                                .buscarPorClave(body.getCodigoEstadoIncidencia())
                                .orElseThrow(() -> new RuntimeException("No existe estatus de solicitud con clave"));
                
                Empleado empleadoRevisor = empleadoRepo.findByNumeroEmpleado(body.getNumeroRevisor())
                        .orElseThrow(() -> new RuntimeException("No existe empleado con ese numero de identificador"));

                EstatusIncidencia estatusIncidencia = estatusIncidenciaRepo
                                .findByClave(body.getCodigoEstadoIncidencia())
                                .orElseThrow(() -> new RuntimeException("No exite estatus de incidencia con clave"
                                                + body.getCodigoEstadoIncidencia()));

                String motivoRechazo = body.getMotivoRechazo();

                SolicitudArticulo solicitudArticulo = null;
                SolicitudPrenda solicitudPrenda = null;

                switch (incidencia.getTipo().getClave()) {
                        case "A":
                                solicitudArticulo = solicitudArticuloRepo
                                                .findById(incidencia.getSolicitudArticulo().getId())
                                                .orElseThrow(() -> new RuntimeException(
                                                                "No existe solicitud de articulo con id: " + incidencia
                                                                                .getSolicitudArticulo().getId()));

                                solicitudArticulo.setEstatusSolicitud(estatusSolicitud);
                                solicitudArticulo.setEmpleadoRevisor(empleadoRevisor);
                                solicitudArticulo.setDescripcionRechazo(motivoRechazo);
                                solicitudArticulo.setFechaModificacion(OffsetDateTime.now());
                                solicitudArticuloRepo.save(solicitudArticulo);
                                break;

                        case "PR":
                                solicitudPrenda = solicitudPrendaRepo.findById(incidencia.getSolicitudPrenda().getId())
                                                .orElseThrow(() -> new RuntimeException(
                                                                "No existe solicitud de articulo con id: " + incidencia
                                                                                .getSolicitudPrenda().getId()));

                                solicitudPrenda.setEstatusSolicitud(estatusSolicitud);
                                solicitudPrenda.setEmpleadoRevisor(empleadoRevisor);
                                solicitudPrenda.setDescripcionRechazo(motivoRechazo);
                                solicitudPrenda.setFechaModificacion(OffsetDateTime.now());
                                solicitudPrendaRepo.save(solicitudPrenda);
                                break;
                }

                incidencia.setEmpleadoRevisa(empleadoRevisor);
                incidencia.setEstatus(estatusIncidencia);
                incidencia.setFechaModificacion(OffsetDateTime.now());
                incidenciaRepo.save(incidencia);
        }

}
