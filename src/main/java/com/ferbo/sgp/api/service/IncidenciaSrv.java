package com.ferbo.sgp.api.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferbo.sgp.api.dto.IncidenciaDTO;
import com.ferbo.sgp.api.dto.IncidenciaPermisoDTO;
import com.ferbo.sgp.api.mapper.IncidenciaMapper;
import com.ferbo.sgp.api.mapper.IncidenciaPermisoMapper;
import com.ferbo.sgp.api.model.Empleado;
import com.ferbo.sgp.api.model.EstatusSolicitud;
import com.ferbo.sgp.api.model.Incidencia;
import com.ferbo.sgp.api.model.SolicitudPermiso;
import com.ferbo.sgp.api.repository.EmpleadoRepo;
import com.ferbo.sgp.api.repository.EstatusIncidenciaRepo;
import com.ferbo.sgp.api.repository.EstatusSolicitudRepo;
import com.ferbo.sgp.api.repository.IncidenciaRepo;
import com.ferbo.sgp.api.repository.SolicitudPermisoRepo;
import com.ferbo.sgp.api.tool.DateUtil;
import java.math.BigDecimal;

@Service
public class IncidenciaSrv {

    @Autowired
    IncidenciaRepo incidenciaRepo;

    @Autowired
    IncidenciaMapper incidenciaMapper;
    
    @Autowired
    IncidenciaPermisoMapper incidenciaPermisoMapper;
    
    @Autowired
    EstatusIncidenciaRepo estatusIncidenciaRepo;
    
    @Autowired
    private SolicitudPermisoRepo solicitudPermisoRepo;
    
    @Autowired
    private EstatusSolicitudRepo estatusSoliciturRepo;
    
    @Autowired
    private EmpleadoRepo empleadoRepo;
    
    public IncidenciaPermisoDTO obtenerIncidenciaPorID(Integer id)
    {
        Incidencia incidencia = incidenciaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro registro de incidencia"));
        
        IncidenciaPermisoDTO incidenciaPermisoDTO = this.convertirPermiso(incidencia);
        
        return incidenciaPermisoDTO;
    }

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
                .findByTipoEstatusEnPeriodo(claveTipo, claveEstatus, fechaInicio, fechaFin).stream()
                .map(this::convertir).collect(Collectors.toList());

        if (incidenciasDTO.isEmpty()) {
            throw new RuntimeException("No existen registros");
        }

        return incidenciasDTO;
    }

    public IncidenciaPermisoDTO actualizarEstatusIncidencia(Integer id, IncidenciaPermisoDTO body) {
        
        String numeroEmpleado = "0030";
        Empleado empleadoRevision = empleadoRepo.findByNumeroEmpleado(numeroEmpleado);
        
        Incidencia incidencia = incidenciaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe incidencia con ese identificador"));
        
        SolicitudPermiso solicitudPermiso = solicitudPermisoRepo.findById(incidencia.getSolicitudPermiso().getIdSolicitud())
                .orElseThrow(() -> new RuntimeException("No se encontro registro de solicitud permiso"));
        
        BigDecimal valor = null;
        String sGoceSueldo = "100.0"; 
        if(solicitudPermiso.getEmpleadoSol().getEmpleadoConfiguracion().getGoceSueldo() == false)
        {
            valor = BigDecimal.ZERO;
        }else{
            if (sGoceSueldo != null && !sGoceSueldo.isEmpty()) 
            {
                valor = new BigDecimal(sGoceSueldo);
            }
        }
        
        if(body.getCodigoEstado().matches("R"))
        {
            solicitudPermiso.setDescripcionRechazo(body.getDescripcionRechazo());
        }
        solicitudPermiso.setGoceSueldo(valor);
        solicitudPermiso.setFechaMod(OffsetDateTime.now());
        solicitudPermiso.setEmpleadoRev(empleadoRevision);
        solicitudPermiso.setEstatusSolicitud(estatusSoliciturRepo.buscarPorClave(body.getCodigoEstado())
                .orElseThrow(() -> new RuntimeException("No se encontro registro de estatus solicitud")));
        solicitudPermisoRepo.save(solicitudPermiso);

        incidencia.setFechaModificacion(OffsetDateTime.now());
        incidencia.setEmpladoRevisa(empleadoRevision);
        incidencia.setEstatus(estatusIncidenciaRepo.findByClave(body.getCodigoEstado()).orElseThrow(
                () -> new RuntimeException("No existe estus con esa clave: " + body.getCodigoEstado())));

        incidenciaRepo.save(incidencia);

        return incidenciaPermisoMapper.toDTO(incidencia);
    }

    public IncidenciaDTO convertir(Incidencia incidencia) {
        return incidenciaMapper.toDTO(incidencia);
    }
    
    public IncidenciaPermisoDTO convertirPermiso(Incidencia incidencia){
        return incidenciaPermisoMapper.toDTO(incidencia);
    }
    
}
