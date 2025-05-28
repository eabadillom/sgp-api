package com.ferbo.sgp.api.repository;

import com.ferbo.sgp.api.model.TipoIncapacidad;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author alberto
 */
public interface TipoIncapacidadRepo extends CrudRepository <TipoIncapacidad, Integer>
{
    @Override
    @Query("SELECT ti FROM TipoIncapacidad ti")
    public abstract List<TipoIncapacidad> findAll();
}
