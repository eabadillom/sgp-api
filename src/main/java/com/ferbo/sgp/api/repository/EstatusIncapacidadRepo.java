package com.ferbo.sgp.api.repository;

import com.ferbo.sgp.api.model.EstatusIncapacidad;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author alberto
 */
public interface EstatusIncapacidadRepo extends CrudRepository <EstatusIncapacidad, Integer>
{
    @Override
    @Query("SELECT et FROM EstatusIncapacidad et")
    public abstract List<EstatusIncapacidad> findAll();
}
