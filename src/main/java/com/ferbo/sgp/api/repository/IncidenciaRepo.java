package com.ferbo.sgp.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ferbo.sgp.api.model.Incidencia;

public interface IncidenciaRepo extends CrudRepository <Incidencia, Integer>{

    @Query("SELECT i FROM INCIDENCIA i WHERE i.tipo.clave = :claveTipo and i.estatus.clave = :claveEstatus AND (i.fechaCaptura BETWEEN :fechaInicio AND :fechaFin")
    public abstract List<Incidencia> findByTipoEstatusEnPeriodo(String claveTipo, String claveEstatus, String fechaInicio, String fechaFin);

}
