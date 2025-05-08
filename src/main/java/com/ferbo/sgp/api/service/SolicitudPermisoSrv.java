package com.ferbo.sgp.api.service;

import com.ferbo.sgp.api.dto.SolicitudPermisoDTO;
import com.ferbo.sgp.api.mapper.SolicitudPermisoMapper;
import com.ferbo.sgp.api.model.EstatusSolicitud;
import com.ferbo.sgp.api.model.SolicitudPermiso;
import com.ferbo.sgp.api.repository.EstatusSolicitudRepo;
import com.ferbo.sgp.api.repository.SolicitudPermisoRepo;
import java.math.BigDecimal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author alberto
 */
@Service
public class SolicitudPermisoSrv 
{
    private static Logger log = LogManager.getLogger(SolicitudPermisoSrv.class);
    
    @Autowired
    private SolicitudPermisoRepo solicitudPermisoRepo;
    
    @Autowired
    private EstatusSolicitudRepo estatusSoliciturRepo;
    
    @Autowired
    SolicitudPermisoMapper solicitudPermisoMapper;
    
    public SolicitudPermisoDTO obtenerSolicitudPorId(Integer id) throws Exception
    {
        SolicitudPermiso solicitudPermiso = solicitudPermisoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro registro de solicitud permiso"));
        
        SolicitudPermisoDTO solicitudPermisoDTO = this.convertirToDTO(solicitudPermiso);
        
        return solicitudPermisoDTO;
    }
    
    public SolicitudPermisoDTO actualizarSolicitudPermiso(Integer id, SolicitudPermisoDTO dto) throws Exception
    {
        SolicitudPermiso solicitudPermiso = solicitudPermisoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro registro de solicitud permiso"));
        
        EstatusSolicitud estatusSolicitud = estatusSoliciturRepo.buscarPorClave(dto.getClaveEstatus())
                .orElseThrow(() -> new RuntimeException("No se encontro registro de estatus solicitud"));
        
        BigDecimal valor = null;
        String sGoceSueldo = "100.0"; 
        if(solicitudPermiso.getEmpleadoRev().getEmpleadoConfiguracion().getGoceSueldo() == false)
        {
            valor = BigDecimal.ZERO;
        }else{
            if (sGoceSueldo != null && !sGoceSueldo.isEmpty()) 
            {
                valor = new BigDecimal(sGoceSueldo);
            }
        }
        
        solicitudPermiso.setGoceSueldo(valor);
        solicitudPermiso.setDescripcionRechazo(dto.getDescripcionRechazo());
        solicitudPermiso.setEstatusSolicitud(estatusSolicitud);
        solicitudPermisoRepo.save(solicitudPermiso);
        
        return convertirToDTO(solicitudPermiso);
    }
    
    public SolicitudPermisoDTO convertirToDTO(SolicitudPermiso solicitudPermiso)
    {
        return solicitudPermisoMapper.toDTO(solicitudPermiso);
    }
    
}
