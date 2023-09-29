package com.ferbo.sgp.api.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ferbo.sgp.api.models.DetEmpleadoModel;

@Repository
public interface DetEmpleadoRepository extends CrudRepository<DetEmpleadoModel,Integer>{

    public abstract DetEmpleadoModel findByNumEmpleado(String numEmpleado);
    
}
