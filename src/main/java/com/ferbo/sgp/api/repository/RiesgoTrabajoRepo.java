package com.ferbo.sgp.api.repository;

import com.ferbo.sgp.api.model.RiesgoTrabajo;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author alberto
 */
public interface RiesgoTrabajoRepo extends CrudRepository <RiesgoTrabajo, Integer>
{
    @Override
    @Query("SELECT rt FROM RiesgoTrabajo rt")
    public abstract List<RiesgoTrabajo> findAll();
    
    @Query("SELECT rt FROM RiesgoTrabajo rt WHERE rt.clave = :codigo")
    public abstract RiesgoTrabajo findByCodigo(String codigo);
}
