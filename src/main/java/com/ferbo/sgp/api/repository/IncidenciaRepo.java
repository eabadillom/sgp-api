package com.ferbo.sgp.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ferbo.sgp.api.model.Incidencia;
import java.time.OffsetDateTime;

public interface IncidenciaRepo extends CrudRepository <Incidencia, Integer>{

    @Query("SELECT i FROM Incidencia i WHERE i.tipo.clave = :claveTipo AND i.estatus.clave <> 'C' AND (i.fechaCaptura BETWEEN :fechaInicio AND :fechaFin)")
    public abstract List<Incidencia> findByTipoEnPeriodo(String claveTipo, OffsetDateTime fechaInicio, OffsetDateTime fechaFin);

}
