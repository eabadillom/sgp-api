package com.ferbo.sgp.api.repository;

import com.ferbo.sgp.api.model.Incapacidad;
import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author alberto
 */
public interface IncapacidadRepo extends CrudRepository <Incapacidad, Integer>
{
    @Query("SELECT i FROM Incapacidad i WHERE ((:fechaInicio = i.fechaInicio AND :fechaFin = i.fechaFin) OR ((:fechaInicio = i.fechaFin) AND (:fechaFin = i.fechaInicio)) OR ((:fechaInicio BETWEEN i.fechaInicio AND i.fechaFin) OR (:fechaFin BETWEEN i.fechaInicio AND i.fechaFin)) OR ((i.fechaInicio BETWEEN :fechaInicio AND :fechaFin) OR (i.fechaFin BETWEEN :fechaInicio AND :fechaFin)))")
    public abstract List<Incapacidad> findByPeriodo(OffsetDateTime fechaInicio, OffsetDateTime fechaFin);
    
    @Query("SELECT i FROM Incapacidad i WHERE i.idEmpleadoInc.idEmpleado = :idEmpleado AND (:fechaInicio BETWEEN i.fechaInicio AND i.fechaFin) ORDER BY i.fechaInicio DESC, i.idIncapacidad DESC")
    public abstract Incapacidad buscarPorEmpleadoUltimoPeriodo(Integer idEmpleado, OffsetDateTime fechaInicio);
    
    @Query("SELECT i FROM Incapacidad i WHERE i.idEmpleadoInc.idEmpleado = :idEmpleado AND ((:fechaInicio = i.fechaInicio AND :fechaFin = i.fechaFin) OR ((:fechaInicio = i.fechaFin) AND (:fechaFin = i.fechaInicio)) OR ((:fechaInicio BETWEEN i.fechaInicio AND i.fechaFin) OR (:fechaFin BETWEEN i.fechaInicio AND i.fechaFin)) OR ((i.fechaInicio BETWEEN :fechaInicio AND :fechaFin) OR (i.fechaFin BETWEEN :fechaInicio AND :fechaFin))) AND i.estatusSolicitud.clave = :clave ORDER BY i.fechaInicio DESC, i.idIncapacidad DESC")
    public abstract List<Incapacidad> findByParametros(Integer idEmpleado,OffsetDateTime fechaInicio, OffsetDateTime fechaFin, String clave);
    
}
