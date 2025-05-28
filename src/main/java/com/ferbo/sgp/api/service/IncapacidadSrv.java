package com.ferbo.sgp.api.service;

import com.ferbo.sgp.api.dto.IncapacidadDTO;
import com.ferbo.sgp.api.dto.IncapacidadDetalleDTO;
import com.ferbo.sgp.api.dto.TipoIncapacidadDTO;
import com.ferbo.sgp.api.mapper.IncapacidadDetalleMapper;
import com.ferbo.sgp.api.mapper.IncapacidadMapper;
import com.ferbo.sgp.api.mapper.TipoIncapacidadMapper;
import com.ferbo.sgp.api.model.Incapacidad;
import com.ferbo.sgp.api.model.TipoIncapacidad;
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
    private TipoIncapacidadRepo tipoIncapacidadRepo;
    
    @Autowired
    private IncapacidadMapper incapacidadMapper;
    
    @Autowired
    private IncapacidadDetalleMapper incapacidadDetalleMapper;
    
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
    
    public List<TipoIncapacidadDTO> obtenerTipoIncapacidad()
    {
        List<TipoIncapacidadDTO> tiposIncapacidadesDTO = tipoIncapacidadRepo.findAll().stream()
                .map(this::convertirTipoIncapacidadDTO).collect(Collectors.toList());
        
        return tiposIncapacidadesDTO;
    }
    
    public IncapacidadDTO convertir(Incapacidad incapacidad) {
        return incapacidadMapper.toDTO(incapacidad);
    }
    
    public IncapacidadDetalleDTO convertirDetalle(Incapacidad incapacidad) {
        return incapacidadDetalleMapper.toDTO(incapacidad);
    }
    
    public TipoIncapacidadDTO convertirTipoIncapacidadDTO(TipoIncapacidad tipoIncapacidad)
    {
        return tipoIncapacidadMapper.toDTO(tipoIncapacidad);
    }
    
}
