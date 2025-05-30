package com.ferbo.sgp.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ferbo.sgp.api.model.Incidencia;
import java.time.OffsetDateTime;

public interface IncidenciaRepo extends CrudRepository <Incidencia, Integer>{

    @Query("SELECT i FROM Incidencia i WHERE i.tipo.clave = :claveTipo AND i.estatus.clave <> 'C' AND (i.fechaCaptura BETWEEN :fechaInicio AND :fechaFin)")
    public abstract List<Incidencia> findByTipoEnPeriodo(String claveTipo, OffsetDateTime fechaInicio, OffsetDateTime fechaFin);

    @Query("SELECT i FROM Incidencia i WHERE i.solicitudPrenda.id = :idSolicitud")
    public abstract Optional<Incidencia> findByIdSolicitudPrenda(Long idSolicitud);

    @Query("SELECT i FROM Incidencia i WHERE i.solicitudArticulo.id = :idSolicitud")
    public abstract Optional<Incidencia> findByIdSolicitudArticulo(Long idSolicitud);

}
