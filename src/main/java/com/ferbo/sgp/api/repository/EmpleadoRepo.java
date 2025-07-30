package com.ferbo.sgp.api.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ferbo.sgp.api.model.Empleado;
import java.util.Optional;

public interface EmpleadoRepo extends CrudRepository<Empleado,Integer>{

    public abstract Optional<Empleado> findByNumeroEmpleado(String numeroEmpleado);
    
    @Query("SELECT e FROM Empleado e WHERE e.activo = 1")
    public abstract List<Empleado> findByActivo();
}
