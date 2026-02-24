package com.ferbo.sgp.api.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ferbo.sgp.api.model.Biometrico;
import com.ferbo.sgp.api.model.Fotografia;

public interface FotografiaRepo extends CrudRepository<Biometrico, Integer>{
	
	@Query("SELECT f FROM Fotografia f WHERE f.empleado.numeroEmpleado = :numeroEmpleado and f.empleado.activo = 1 and ( (f.empleado.informacionEmpresa.fechaIngreso <= :fecha and f.empleado.informacionEmpresa.fechaBaja is null) or ( f.empleado.informacionEmpresa.fechaIngreso <= :fecha and f.empleado.informacionEmpresa.fechaBaja >= :fecha  ) )")
	public abstract Fotografia findByNumeroEmpleado(String numeroEmpleado, LocalDate fecha);

}
