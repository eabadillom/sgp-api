package com.ferbo.sgp.api.repository;

import com.ferbo.sgp.api.model.DiaNoLaboral;
import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author alberto
 */
public interface DiaNoLaboralRepo extends CrudRepository <DiaNoLaboral, Integer> 
{
    @Query("SELECT dnl FROM DiaNoLaboral dnl WHERE dnl.pais.clave = :clavePais AND (dnl.fecha >= :fechaInicio AND dnl.fecha <= :fechaFin) ORDER BY dnl.fecha ASC")
    public abstract List<DiaNoLaboral> buscarPorPeriodo(String clavePais, OffsetDateTime fechaInicio, OffsetDateTime fechaFin);
}
