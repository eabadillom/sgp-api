package com.ferbo.sgp.api.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ferbo.sgp.api.model.Empleado;
import java.time.OffsetDateTime;
import java.util.Optional;

public interface EmpleadoRepo extends CrudRepository<Empleado,Integer>{

    public abstract Optional<Empleado> findByNumeroEmpleado(String numeroEmpleado);
    
    @Query("SELECT e FROM Empleado e WHERE e.activo = 1")
    public abstract List<Empleado> findByActivo();
    
    @Query(value = "SELECT e.* " +
    " FROM det_empleado e " +
    " INNER JOIN inf_empleado_empresa iee ON iee.id_empleado_empresa = e.id_empleado_empresa " +
    " INNER JOIN cat_empresa emp          ON emp.id_empresa = iee.id_empresa " +
    " INNER JOIN cat_planta  pl           ON pl.id_planta  = iee.id_planta " +
    " INNER JOIN det_registro r ON r.id_empleado = e.id_empleado " +
    "       AND r.fecha_entrada >= :fechaIni " +
    "       AND r.fecha_entrada <=  :fechaFin " +
    "       AND r.fecha_entrada >= iee.fh_ingreso " +
    "       AND (iee.fh_baja IS NULL OR r.fecha_entrada <= iee.fh_baja) " +
    " WHERE iee.fh_ingreso < :fechaFin " +
    "     AND (iee.fh_baja IS NULL OR iee.fh_baja >= :fechaIni) " +
    "     AND (:idEmpresa IS NULL OR iee.id_empresa = :idEmpresa) " +
    "     AND (:idPlanta  IS NULL OR iee.id_planta  = :idPlanta) " +
    " GROUP BY e.id_empleado " +
    " ORDER BY e.id_empleado", nativeQuery = true)
    public abstract List<Empleado> findByParametros(OffsetDateTime fechaIni, OffsetDateTime fechaFin, Integer idPlanta, Integer idEmpresa);
    
}
