package com.ferbo.sgp.api.repository;

import com.ferbo.sgp.api.model.TipoRiesgo;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author alberto
 */
public interface TipoRiesgoRepo extends CrudRepository <TipoRiesgo, Integer>
{
    @Override
    @Query("SELECT tr FROM TipoRiesgo tr")
    public abstract List<TipoRiesgo> findAll();
}
