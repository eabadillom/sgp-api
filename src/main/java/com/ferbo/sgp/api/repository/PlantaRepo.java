package com.ferbo.sgp.api.repository;

import com.ferbo.sgp.api.model.Planta;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PlantaRepo extends CrudRepository<Planta, Integer>
{
    @Query("SELECT p FROM Planta p WHERE p.activo = 1")
    public abstract List<Planta> findByActivo();
}
