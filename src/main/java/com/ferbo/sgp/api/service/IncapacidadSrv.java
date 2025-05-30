package com.ferbo.sgp.api.service;

import com.ferbo.sgp.api.dto.EmpleadoIncDTO;
import com.ferbo.sgp.api.dto.IncapacidadDTO;
import com.ferbo.sgp.api.dto.IncapacidadDetalleDTO;
import com.ferbo.sgp.api.dto.TipoIncapacidadDTO;
import com.ferbo.sgp.api.mapper.IncapacidadDetalleMapper;
import com.ferbo.sgp.api.mapper.IncapacidadMapper;
import com.ferbo.sgp.api.mapper.TipoIncapacidadMapper;
import com.ferbo.sgp.api.model.Empleado;
import com.ferbo.sgp.api.model.Incapacidad;
import com.ferbo.sgp.api.model.TipoIncapacidad;
import com.ferbo.sgp.api.repository.EmpleadoRepo;
import com.ferbo.sgp.api.repository.IncapacidadRepo;
import com.ferbo.sgp.api.repository.TipoIncapacidadRepo;
import com.ferbo.sgp.api.tool.DateUtil;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ferbo.sgp.api.mapper.EmpleadoIncMapper;
import com.ferbo.sgp.api.model.ControlIncapacidad;
import com.ferbo.sgp.api.model.RiesgoTrabajo;
import com.ferbo.sgp.api.model.TipoRiesgo;
import com.ferbo.sgp.api.repository.ControlIncapacidadRepo;
import com.ferbo.sgp.api.repository.RiesgoTrabajoRepo;
import com.ferbo.sgp.api.repository.TipoRiesgoRepo;

/**
 *
 * @author alberto
 */
@Service
public class IncapacidadSrv 
{
    private static final Logger log = LogManager.getLogger(IncapacidadSrv.class);
    
    @Autowired
    private IncapacidadRepo incapacidadRepo;
    
    @Autowired
    private EmpleadoRepo empleadoRepo;
    
    @Autowired
    private TipoIncapacidadRepo tipoIncapacidadRepo;
    
    @Autowired
    private ControlIncapacidadRepo controlIncapacidadRepo;
    
    @Autowired
    private RiesgoTrabajoRepo riesgoTrabajoRepo;
    
    @Autowired
    private TipoRiesgoRepo tipoRiesgoRepo;
    
    @Autowired
    private IncapacidadMapper incapacidadMapper;
    
    @Autowired
    private IncapacidadDetalleMapper incapacidadDetalleMapper;
    
    @Autowired
    private EmpleadoIncMapper empleadoMapper;
    
    @Autowired
    private TipoIncapacidadMapper tipoIncapacidadMapper;
    
    public List<IncapacidadDTO> obtenerIncapacidadPorPeriodo(String fechaInicial, String fechaFinal) throws RuntimeException
    {
        OffsetDateTime fechaInicio = DateUtil.stringToOffSetTime(fechaInicial);
        OffsetDateTime fechaFin = DateUtil.stringToOffSetTime(fechaFinal);
        
        fechaInicio = DateUtil.resetOffSetTime(fechaInicio);
        fechaFin = DateUtil.resetOffSetTime(fechaFin);

        if (fechaInicio.isAfter(fechaFin)) {
            OffsetDateTime fechaAux = fechaFin;
            fechaFin = fechaInicio;
            fechaInicio = fechaAux;
        }
        
        List<IncapacidadDTO> incapacidadesDTO = incapacidadRepo.findByPeriodo(fechaInicio, fechaFin).stream()
                .map(this::convertir).collect(Collectors.toList());
        
        log.info("Num. de incapacidades: {}", incapacidadesDTO.size());
        /*if(incapacidadesDTO.isEmpty()){
            throw new RuntimeException("No hay registros de incapacidades");
        }*/
        
        return incapacidadesDTO;
    }
    
    public IncapacidadDetalleDTO obtenerIncapacidadPorId(Integer id)
    {
        Incapacidad incapacidad = incapacidadRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("No se encontro registro de incidencia"));
        
        IncapacidadDetalleDTO incapacidadDetalleDTO = this.convertirDetalle(incapacidad);
        
        return incapacidadDetalleDTO;
    }
    
    public List<EmpleadoIncDTO> obtenerEmpleados()
    {
        List<EmpleadoIncDTO> listEmpleadosDTO = empleadoRepo.findByActivo().stream()
                .map(this::convertirEmpleadoIncToDTO).collect(Collectors.toList());
        
        log.info("Num. de empleados: {}", listEmpleadosDTO.size());
        
        return listEmpleadosDTO;
    }
    
    public List<TipoIncapacidadDTO> obtenerTipoIncapacidad()
    {
        List<TipoIncapacidadDTO> listTiposIncapacidadesDTO = tipoIncapacidadRepo.findAll().stream()
                .map(this::convertirTipoIncapacidadToDTO).collect(Collectors.toList());
        
        log.info("Num. de tipos de incapacidades: {}", listTiposIncapacidadesDTO.size());
        
        return listTiposIncapacidadesDTO;
    }
    
    public List<ControlIncapacidad> obtenerControlIncapacidad()
    {
        List<ControlIncapacidad> listControlIncapacidad = controlIncapacidadRepo.findAll();
        
        log.info("Num. de control de incapacidades: {}", listControlIncapacidad.size());
        
        return listControlIncapacidad;
    }
    
    public List<RiesgoTrabajo> obtenerRiesgoTrabajo()
    {
        List<RiesgoTrabajo> listRiesgoTrabajo = riesgoTrabajoRepo.findAll();
        
        log.info("Num. de riesgos de trabajo: {}", listRiesgoTrabajo.size());
        
        return listRiesgoTrabajo;
    }
    
    public List<TipoRiesgo> obtenerTipoRiesgo()
    {
        List<TipoRiesgo> listTipoRiesgo = tipoRiesgoRepo.findAll();
        
        log.info("Num. de tipos de riesgos: {}", listTipoRiesgo.size());
        
        return listTipoRiesgo;
    }
    
    public IncapacidadDTO convertir(Incapacidad incapacidad) {
        return incapacidadMapper.toDTO(incapacidad);
    }
    
    public IncapacidadDetalleDTO convertirDetalle(Incapacidad incapacidad) {
        return incapacidadDetalleMapper.toDTO(incapacidad);
    }
    
    public EmpleadoIncDTO convertirEmpleadoIncToDTO(Empleado empleado)
    {
        return empleadoMapper.toDTO(empleado);
    }
    
    public TipoIncapacidadDTO convertirTipoIncapacidadToDTO(TipoIncapacidad tipoIncapacidad)
    {
        return tipoIncapacidadMapper.toDTO(tipoIncapacidad);
    }
    
}
