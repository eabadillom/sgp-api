package com.ferbo.sgp.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ferbo.sgp.api.model.EstatusIncidencia;

public interface EstatusIncidenciaRepo extends CrudRepository{

    @Query("SELECT i FROM INCIDENCIA i WHERE i.clave = :clave")
    public abstract Optional<EstatusIncidencia> findByClave(String clave);
}
