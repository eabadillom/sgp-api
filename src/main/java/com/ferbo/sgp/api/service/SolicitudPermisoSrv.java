package com.ferbo.sgp.api.service;

import com.ferbo.sgp.api.dto.SolicitudPermisoDTO;
import com.ferbo.sgp.api.mapper.SolicitudPermisoMapper;
import com.ferbo.sgp.api.model.SolicitudPermiso;
import com.ferbo.sgp.api.repository.SolicitudPermisoRepo;
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
    private SolicitudPermisoMapper solicitudPermisoMapper;
    
    public SolicitudPermisoDTO obtenerSolicitudPorId(Integer id) throws Exception
    {
        SolicitudPermiso solicitudPermiso = solicitudPermisoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro registro de solicitud permiso"));
        
        SolicitudPermisoDTO solicitudPermisoDTO = this.convertirToDTO(solicitudPermiso);
        
        return solicitudPermisoDTO;
    }
    
    public SolicitudPermisoDTO convertirToDTO(SolicitudPermiso solicitudPermiso)
    {
        return solicitudPermisoMapper.toDTO(solicitudPermiso);
    }
    
}
