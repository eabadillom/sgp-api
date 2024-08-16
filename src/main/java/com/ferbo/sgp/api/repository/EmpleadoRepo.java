package com.ferbo.sgp.api.repository;


import org.springframework.data.repository.CrudRepository;

import com.ferbo.sgp.api.model.Empleado;

public interface EmpleadoRepo extends CrudRepository<Empleado,Integer>{

    public abstract Empleado findByNumeroEmpleado(String numeroEmpleado);
    
}
