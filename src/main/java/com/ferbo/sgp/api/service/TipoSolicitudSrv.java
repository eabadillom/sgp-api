package com.ferbo.sgp.api.service;

import com.ferbo.sgp.api.model.TipoSolicitud;
import com.ferbo.sgp.api.repository.TipoSolicitudRepo;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author alberto
 */
@Service
public class TipoSolicitudSrv 
{
    private static Logger log = LogManager.getLogger(TipoSolicitudSrv.class);
    
    @Autowired
    private TipoSolicitudRepo tipoSolicitudRepo;
    
    public TipoSolicitud buscarPorClave(String clave)
    {
        return (TipoSolicitud) tipoSolicitudRepo.buscarPorClave(clave);
    }
    
    public List<TipoSolicitud> buscarTodos()
    {
        return (List<TipoSolicitud>) tipoSolicitudRepo.buscarTodos();
    }
    
    public List<TipoSolicitud> buscarActivos()
    {
        return (List<TipoSolicitud>) tipoSolicitudRepo.buscarActivos();
    }
    
}
