package com.ferbo.sgp.api.service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.ferbo.sgp.api.dto.EmpleadoAsisDTO;
import com.ferbo.sgp.api.dto.RegistroAsistenciaDTO;
import com.ferbo.sgp.api.mapper.EmpleadoAsisMapper;
import com.ferbo.sgp.api.mapper.RegistroAsistenciaMapper;
import com.ferbo.sgp.api.model.Empleado;
import com.ferbo.sgp.api.model.RegistroAsistencia;
import com.ferbo.sgp.api.repository.EmpleadoRepo;
import com.ferbo.sgp.api.repository.RegistroAsistenciaRepo;
import com.ferbo.sgp.api.tool.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class IncidenciaEmpleadoSrv 
{
    private static final Logger log = LogManager.getLogger(IncidenciaEmpleadoSrv.class);
    
    @Autowired
    private RegistroAsistenciaRepo asistenciaRepo;
    
    @Autowired
    private EmpleadoRepo empleadoRepo;
    
    @Autowired
    private EmpleadoAsisMapper empleadoAsisMapper;
    
    @Autowired
    private RegistroAsistenciaMapper registroAsistenciaMapper;
    
    public List<EmpleadoAsisDTO> buscarPorParametros(String fechaIni, String fechaFin, Integer idPlanta, Integer idEmpresa) 
    {
        OffsetDateTime fechaInicio = DateUtil.stringToOffSetTime(fechaIni);
        OffsetDateTime fechaFinal = DateUtil.stringToOffSetTime(fechaFin);

        fechaInicio = DateUtil.resetOffSetTime(fechaInicio);
        fechaFinal = DateUtil.setToEndOfDay(fechaFinal);
        
        return empleadoRepo.findByParametros(fechaInicio, fechaFinal, idPlanta, idEmpresa).stream().map(this::convertirEmpleadoAsis).collect(Collectors.toList());
    }
    
    public EmpleadoAsisDTO obtenerRegistrosPorEmpleado(String fechaIni, String fechaFin, Integer idEmpleado) 
    {
        OffsetDateTime fechaInicio = DateUtil.stringToOffSetTime(fechaIni);
        OffsetDateTime fechaFinal = DateUtil.stringToOffSetTime(fechaFin);
        
        fechaInicio = DateUtil.resetOffSetTime(fechaInicio);
        fechaFinal = DateUtil.setToEndOfDay(fechaFinal);
        
        Empleado empleado = empleadoRepo.findById(idEmpleado).orElseThrow(()-> new RuntimeException("No se encontro al empleado con el identificador dado"));
        List<RegistroAsistencia> listAsistencia = asistenciaRepo.buscarPorPeriodoEmpleado(fechaInicio, fechaFinal, idEmpleado);
        
        EmpleadoAsisDTO empleadoDTO = new EmpleadoAsisDTO();
        empleadoDTO.setId(idEmpleado);
        empleadoDTO.setNombre(empleado.getNombre());
        empleadoDTO.setPrimerAp(empleado.getPrimeroAp());
        empleadoDTO.setSegundoAp(empleado.getSegundoAp());
        
        List<RegistroAsistenciaDTO> listAsistenciaDTO = new ArrayList();
        for(RegistroAsistencia registro : listAsistencia) {
            RegistroAsistenciaDTO asistenciaDTO = new RegistroAsistenciaDTO();
            asistenciaDTO.setId(registro.getId());
            asistenciaDTO.setFechaEntrada(registro.getFechaEntrada());
            asistenciaDTO.setCodigoRegistro(registro.getStatus().getCodigo());
            listAsistenciaDTO.add(asistenciaDTO);
        }
        empleadoDTO.setAsistencia(listAsistenciaDTO);
        
        return empleadoDTO;
    }
    
    public EmpleadoAsisDTO convertirEmpleadoAsis(Empleado empleado) {
        return empleadoAsisMapper.toDTO(empleado);
    }
    
    public RegistroAsistenciaDTO convertirRegistroAsistencia(RegistroAsistencia registro) {
        return registroAsistenciaMapper.toDTO(registro);
    }
    
}
