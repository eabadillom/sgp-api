package com.ferbo.sgp.api.service;

import com.ferbo.sgp.api.model.EstatusSolicitud;
import com.ferbo.sgp.api.repository.EstatusSolicitudRepo;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author alberto
 */
@Service
public class EstatusSolicitudSrv 
{
    private static Logger log = LogManager.getLogger(EstatusSolicitudSrv.class);
    
    @Autowired
    private EstatusSolicitudRepo estatusSolicitudRepo;
        
    public List<EstatusSolicitud> buscarTodos()
    {
        return (List<EstatusSolicitud>) estatusSolicitudRepo.buscarTodos();
    }
    
    public Optional<EstatusSolicitud> buscarPorClave(String clave)
    {
        return (Optional<EstatusSolicitud>) estatusSolicitudRepo.buscarPorClave(clave);
    }
    
}
