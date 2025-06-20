package com.ferbo.sgp.api.repository;

import com.ferbo.sgp.api.model.ControlIncapacidad;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author alberto
 */
public interface ControlIncapacidadRepo extends CrudRepository <ControlIncapacidad, Integer>
{
    @Override
    @Query("SELECT ci FROM ControlIncapacidad ci")
    public abstract List<ControlIncapacidad> findAll();
}
