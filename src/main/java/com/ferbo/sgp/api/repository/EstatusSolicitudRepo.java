package com.ferbo.sgp.api.repository;

import com.ferbo.sgp.api.model.EstatusSolicitud;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author alberto
 */
public interface EstatusSolicitudRepo extends CrudRepository<EstatusSolicitud, Integer>
{
    @Query("SELECT es FROM EstatusSolicitud es WHERE es.clave = :clave")
    public abstract Optional<EstatusSolicitud> buscarPorClave(String clave);
    
    @Query("SELECT es FROM EstatusSolicitud es")
    public abstract List<EstatusSolicitud> buscarTodos();
    
}
