package com.ferbo.sgp.api.repository;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ferbo.sgp.api.model.RegistroAsistencia;

public interface RegistroAsistenciaRepo extends CrudRepository<RegistroAsistencia, Integer>{
	
	@Query("SELECT r FROM RegistroAsistencia r WHERE r.empleado.numeroEmpleado = :numeroEmpleado AND r.fechaEntrada BETWEEN :fechaInicio AND :fechaFin")
	public abstract Optional<RegistroAsistencia> buscarPorPeriodo(String numeroEmpleado, OffsetDateTime fechaInicio, OffsetDateTime fechaFin);
	
	
	
}
