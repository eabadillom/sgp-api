package com.ferbo.sgp.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferbo.sgp.api.config.RutaImagenProperties;
import com.ferbo.sgp.api.dto.SolicitudArticuloDTO;
import com.ferbo.sgp.api.dto.SolicitudPrendaDTO;
import com.ferbo.sgp.api.mapper.SolicitudArticuloMapper;
import com.ferbo.sgp.api.mapper.SolicitudPrendaMapper;
import com.ferbo.sgp.api.model.Empleado;
import com.ferbo.sgp.api.model.Incidencia;
import com.ferbo.sgp.api.model.SolicitudArticulo;
import com.ferbo.sgp.api.model.SolicitudPrenda;
import com.ferbo.sgp.api.repository.EmpleadoRepo;
import com.ferbo.sgp.api.repository.EstatusIncidenciaRepo;
import com.ferbo.sgp.api.repository.EstatusSolicitudRepo;
import com.ferbo.sgp.api.repository.IncidenciaRepo;
import com.ferbo.sgp.api.repository.SolicitudArticulodRepo;
import com.ferbo.sgp.api.repository.SolicitudPrendaRepo;
import com.ferbo.sgp.api.service.helper.SolicitudHelperService;

@Service
public class SolicitudSrv {

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

    @Autowired
    private SolicitudHelperService helper;

    public SolicitudArticuloDTO obtenerSolicitudArticulo(Long id) {
        SolicitudArticulo solicitud = helper.obtenerEntidadPorId(
                solicitudArticuloRepo.findById(id),
                "No existe solicitud de articulo con el id: " + id);

        SolicitudArticuloDTO dto = solicitudArticuloMapper.toDTO(solicitud);
        dto.setRutaImagen(rutaImagenProperties.getArticulo());
        return dto;
    }

    public SolicitudPrendaDTO obtenerSolicitudPrenda(Long id) {
        SolicitudPrenda solicitud = helper.obtenerEntidadPorId(solicitudPrendaRepo.findById(id),
                "No existe solicitud de uniforme con el id: " + id);

        SolicitudPrendaDTO dto = solicitudPrendaMapper.toDTO(solicitud);
        dto.setRutaImagen(rutaImagenProperties.getUniforme());

        return dto;
    }

    public SolicitudArticuloDTO actualizarSolicitudArticulo(SolicitudArticuloDTO body) {
        SolicitudArticulo solicitud = helper.obtenerEntidadPorId(
                solicitudArticuloRepo.findById(body.getId()),
                "No existe la solicitud de articulo con el id: " + body.getId());

        Incidencia incidencia = helper.obtenerEntidadPorId(
                incidenciaRepo.findByIdSolicitudArticulo(solicitud.getId()),
                "No existe la incidencia con id: " + solicitud.getId());

        Empleado revisor = empleadoRepo.findByNumeroEmpleado(body.getNumeroRevisor());

        helper.actualizarIncidencia(incidencia, body.getEstatusSolicitud(), revisor, estatusIncidenciaRepo);
        helper.actualizarSolicitud(solicitud, body.getEstatusSolicitud(), revisor, estatusSolicitudRepo);

        incidenciaRepo.save(incidencia);
        solicitudArticuloRepo.save(solicitud);

        return solicitudArticuloMapper.toDTO(solicitud);
    }

    public SolicitudPrendaDTO actualizarSolicitudPrenda(SolicitudPrendaDTO body) {
        SolicitudPrenda solicitud = helper.obtenerEntidadPorId(
                solicitudPrendaRepo.findById(body.getId()),
                "No existe la solicitud de uniforme con el id: " + body.getId());

        Incidencia incidencia = helper.obtenerEntidadPorId(
                incidenciaRepo.findByIdSolicitudArticulo(solicitud.getId()),
                "No existe la incidencia con id: " + solicitud.getId());
                
        Empleado revisor = empleadoRepo.findByNumeroEmpleado(body.getNumeroRevisor());

        helper.actualizarIncidencia(incidencia, body.getEstatusSolicitud(), revisor, estatusIncidenciaRepo);
        helper.actualizarSolicitud(solicitud, body.getEstatusSolicitud(), revisor, estatusSolicitudRepo);

        incidenciaRepo.save(incidencia);
        solicitudPrendaRepo.save(solicitud);

        return solicitudPrendaMapper.toDTO(solicitud);
    }

}
