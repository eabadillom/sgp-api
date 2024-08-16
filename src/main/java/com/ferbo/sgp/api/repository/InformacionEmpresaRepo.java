package com.ferbo.sgp.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ferbo.sgp.api.model.InformacionEmpresa;

public interface InformacionEmpresaRepo extends CrudRepository<InformacionEmpresa, Integer> {

//	public abstract Optional<InformacionEmpresa> findById(Integer id);
	
	@Query("SELECT i FROM InformacionEmpresa i WHERE i.planta.id = :idPlanta AND i.empleado IS NOT NULL AND i.empleado.biometrico IS NOT NULL")
	public abstract List<InformacionEmpresa> findByIdPlanta(Integer idPlanta);
}
