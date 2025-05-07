package com.ferbo.sgp.api.repository;

import com.ferbo.sgp.api.model.TipoSolicitud;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author alberto
 */
public interface TipoSolicitudRepo extends CrudRepository<TipoSolicitud, Integer>
{
    @Query("SELECT ts FROM TipoSolicitud ts WHERE ts.clave = :clave AND ts.activo = 1")
    public abstract TipoSolicitud buscarPorClave(String clave);
    
    @Query("SELECT ts FROM TipoSolicitud ts")
    public abstract List<TipoSolicitud> buscarTodos();
    
    @Query("SELECT ts FROM TipoSolicitud ts WHERE ts.activo = 1")
    public abstract List<TipoSolicitud> buscarActivos();
    
}
