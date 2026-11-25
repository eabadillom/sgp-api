package com.ferbo.sgp.api.repository;

import com.ferbo.sgp.api.model.Empresa;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface EmpresaRepo extends CrudRepository<Empresa, Integer>
{
    @Query("SELECT e FROM Empresa e WHERE e.activo = 1")
    public abstract List<Empresa> findByActivo();
}
