package com.ferbo.sgp.api.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ferbo.sgp.api.model.Biometrico;
import com.ferbo.sgp.api.model.Fotografia;

public interface FotografiaRepo extends CrudRepository<Biometrico, Integer>{
	
	@Query("SELECT f FROM Fotografia f WHERE f.empleado.numeroEmpleado = :numeroEmpleado")
	public abstract Fotografia findByNumeroEmpleado(String numeroEmpleado);

}
